package com.dbajaj.whatsappclone.viewmodels


import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import androidx.browser.trusted.Token
import androidx.lifecycle.ViewModel
import com.dbajaj.whatsappclone.datamodels.phoneAuthUser
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class PhoneAuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val database: FirebaseDatabase
): ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Ideal)
    val authState=_authState.asStateFlow()
    private val userRef=database.reference.child("users")
    fun sendVerificationCode(phoneNumber: String,activity:Activity){
        _authState.value=AuthState.Loading
        val option=object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                _authState.value=AuthState.CodeSent(id)
            }

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithCredential(credential,activity)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e("PhoneAuth","Verification failed ${e.message}")
                _authState.value=AuthState.Error(e.message?:"Verification failed")
            }

        }
        val phoneAuthOptions= PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(option).build()
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }
    private fun signInWithCredential(credential: PhoneAuthCredential,context:Context){
        _authState.value=AuthState.Loading
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                val user=firebaseAuth.currentUser
                val phoneAuthUser= phoneAuthUser(
                    userId = user?.uid?:"",
                    phoneNumber = user?.phoneNumber?:""
                )
                markUserAsSignedIn(context)
                _authState.value=AuthState.Success(phoneAuthUser)
                fetchUserProfile(user?.uid?:"")
            }else{
                _authState.value=AuthState.Error(it.exception?.message?:"Sign in failed")
            }
        }
    }
    fun markUserAsSignedIn(context: Context){
        val sharedPreferences=context.getSharedPreferences("user_prefs",Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isSignedIn",true).apply()
    }
    fun fetchUserProfile(userId:String){
        val userRef=userRef.child(userId)
        userRef.get().addOnSuccessListener {
            if(it.exists()){
                val user=it.getValue(phoneAuthUser::class.java)
                _authState.value=AuthState.Success(user!!)
            }
    }.addOnFailureListener {
        _authState.value=AuthState.Error(it.message?:"Failed to fetch user profile")
        }

    }
    fun verifyCode(code:String,context: Context){
        val currentAuthState=_authState.value
        if(currentAuthState !is AuthState.CodeSent || currentAuthState.verificationId.isEmpty()){
            Log.e("PhoneAuth","Attempting to verify OTP without a valid verification ID")
            _authState.value=AuthState.Error("Invalid verification ID")
            return
        }
        val credential=PhoneAuthProvider.getCredential(currentAuthState.verificationId,code)
        signInWithCredential(credential,context)
    }
    fun saveUserProfile(userId: String,name:String,status:String,profilePic:Bitmap?){
        val database=FirebaseDatabase.getInstance().reference
        val encodedImage=profilePic?.let { convertBitmapToBase64(it)}
        val user=phoneAuthUser(
            userId = userId,
            name = name,
            status = status,
            phoneNumber = Firebase.auth.currentUser?.phoneNumber?:"",
            profilePic = encodedImage
        )
        database.child("users").child(userId).setValue(user)
    }

    private fun convertBitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
    fun resetAuthState(){
        _authState.value=AuthState.Ideal
    }
    fun signOut(context: Context) {
        firebaseAuth.signOut()
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isSignedIn", false).apply()
        _authState.value = AuthState.Ideal
    }

}
sealed class AuthState {
    object Ideal: AuthState()
    object Loading: AuthState()
    data class CodeSent(val verificationId: String): AuthState()
    data class Error(val message: String): AuthState()
    data class Success(val user:phoneAuthUser): AuthState()
}