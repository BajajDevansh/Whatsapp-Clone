package com.dbajaj.whatsappclone.datamodels

data class phoneAuthUser(
    val userId: String="",
    val phoneNumber: String="",
    val name: String="",
    val status: String="",
    val profilePic: String?=null
)
