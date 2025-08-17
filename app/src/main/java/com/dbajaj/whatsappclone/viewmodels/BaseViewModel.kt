package com.dbajaj.whatsappclone.viewmodels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.lifecycle.ViewModel
import com.dbajaj.whatsappclone.datamodels.ChatListItem
import com.dbajaj.whatsappclone.datamodels.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.ByteArrayInputStream
import java.io.IOException

class BaseViewModel: ViewModel() {
    fun searchUserByPhoneNumber(phoneNumber: String,callback:
    (ChatListItem?)-> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            callback(null)
            return
        }else{
            val db = FirebaseDatabase.getInstance().getReference("users")
            db.orderByChild("phoneNumber").equalTo(phoneNumber)
                .addListenerForSingleValueEvent(object:ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            val user=snapshot.children.first().getValue(ChatListItem::class.java)
                            callback(user)
                        }else{
                            callback(null)
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        callback(null)
                    }
                })
        }
    }
    fun getChatForUser(
        userId: String,
        callback: (List<ChatListItem>) -> Unit
    ){
        val chatRef=FirebaseDatabase.getInstance().getReference("users/$userId/chats")
        chatRef.orderByChild("userId").equalTo(userId)
            .addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val chatList= mutableListOf<ChatListItem>()
                    for(snap in snapshot.children) {
                        val chat = snap.getValue(ChatListItem::class.java)
                        chat?.let {
                            chatList.add(it)
                        }
                    }
                    callback(chatList)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(emptyList())
                }
            }
        )
    }
    private val _chatList= MutableStateFlow<List<ChatListItem>>(emptyList())
    val chatList=_chatList.asStateFlow()
    init {
        loadChatData()
    }
    private fun loadChatData(){
        val currentUser=FirebaseAuth.getInstance().currentUser?.uid
        if(currentUser!=null) {
            val db = FirebaseDatabase.getInstance().getReference("chats")
            db.orderByChild("userId").equalTo(currentUser)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val chatList=mutableListOf<ChatListItem>()
                        for(snap in snapshot.children) {
                            val chat = snap.getValue(ChatListItem::class.java)
                            chat?.let {
                                chatList.add(it)
                            }
                        }
                        _chatList.value=chatList
                    }

                    override fun onCancelled(error: DatabaseError) {
                         
                    }
                }
            )
        }

    }
    fun addChat(newChat: ChatListItem){
        val currentUser=FirebaseAuth.getInstance().currentUser?.uid
        if(currentUser!=null) {
            val db = FirebaseDatabase.getInstance().getReference("chats").push()
            val chatWithUser=newChat.copy(userId = currentUser)
            db.setValue(chatWithUser)

        }
    }
    private val databaseRef=FirebaseDatabase.getInstance().reference
    fun sendMessage(senderPhoneNumber:String,receiverPhoneNumber:String,message:String){
        val messageId=databaseRef.child("messages").push().key?:return
        val msg=Message(senderPhoneNumber,message,System.currentTimeMillis())
        databaseRef
            .child("messages")
            .child(senderPhoneNumber)
            .child(receiverPhoneNumber)
            .child(messageId)
            .setValue(msg)
        databaseRef
            .child("messages")
            .child(receiverPhoneNumber)
            .child(senderPhoneNumber)
            .child(messageId)
            .setValue(msg)
    }
    fun getMessages(
        senderPhoneNumber: String,
        receiverPhoneNumber: String,
        onNewMessage:(Message)->Unit
    ){
        val msgRef=databaseRef.child("messages").child(senderPhoneNumber).child(receiverPhoneNumber)
        msgRef.addChildEventListener(object:ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val msg=snapshot.getValue(Message::class.java)
                if(msg!=null){
                    onNewMessage(msg)
                }
            }

            override fun onChildChanged(
                snapshot: DataSnapshot,
                previousChildName: String?
            ) {
                
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                 
            }

            override fun onChildMoved(
                snapshot: DataSnapshot,
                previousChildName: String?
            ) {
                 
            }

            override fun onCancelled(error: DatabaseError) {
                 
            }
        })
    }
    fun fetchLastMessageForChat(
        senderPhoneNumber: String,
        receiverPhoneNumber: String,
        onLastMessageFetched:(String, String)->Unit
    ){
        val chatRef=FirebaseDatabase.getInstance().reference
            .child("messages")
            .child("senderPhoneNumber")
            .child("receiverPhoneNumber")

        chatRef.orderByChild("timestamp").limitToLast(1)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val lastMessage=snapshot.children.firstOrNull()?.child("message")?.value as? String
                    val timestamp=snapshot.children.firstOrNull()?.child("timestamp")?.value as? String
                    onLastMessageFetched(lastMessage?:"No message",timestamp?:"--:--")

                }
            })
    }
    fun loadChatList(
        currentUserPhoneNumber: String,
        onChatListLoaded:(List<ChatListItem>)->Unit
    ){
        val chatList=mutableListOf<ChatListItem>()
        val chatRef= FirebaseDatabase.getInstance().reference
            .child("chats")
            .child(currentUserPhoneNumber)
        chatRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    snapshot.children.forEach {
                        val phoneNumber=it.key?:return@forEach
                        val name=it.child("name").value as? String?:"Unknown"
                        val image=it.child("image").value as? String
                        val profileImageBitmap=image?.let {img->
                            decodeBase64ToBitmap(img)

                        }
                        fetchLastMessageForChat(currentUserPhoneNumber,phoneNumber){lastMessage,time->
                            chatList.add(
                                ChatListItem(
                                    name=name,
                                    image = profileImageBitmap as Int?,
                                    message = lastMessage,
                                    time = time
                                )
                            )
                            if(chatList.size==snapshot.childrenCount.toInt()){
                                onChatListLoaded(chatList)
                            }
                        }
                    }
                }else{
                    onChatListLoaded(emptyList())
                }
            }


        })
    }
    private fun decodeBase64ToBitmap(image:String): Bitmap? {
        return try{
            val decodeByte=Base64.decode(image,Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodeByte,0,decodeByte.size)
        }catch (e:IOException){
            null
        }
    }
    fun base64ToBitmap(string:String):Bitmap?{
        return try{
            val decodeByte=Base64.decode(string,Base64.DEFAULT)
            val inputStream= ByteArrayInputStream(decodeByte)
            BitmapFactory.decodeStream(inputStream)
        }catch (e:IOException){
            null
        }
    }
}