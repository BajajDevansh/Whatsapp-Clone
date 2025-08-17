package com.dbajaj.whatsappclone.datamodels

import androidx.compose.ui.graphics.painter.Painter

data class ChatListItem(
    val name: String?=null,
    val phoneNumber:String?=null,
    val image: Int?=null,
    val userId:String?=null,
    val time: String?=null,
    val message: String?=null,
    val profileName:String?=null
){
    constructor():this(null,null,null,null,null,null,null)
}
