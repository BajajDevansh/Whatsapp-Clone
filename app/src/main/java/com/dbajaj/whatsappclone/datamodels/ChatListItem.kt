package com.dbajaj.whatsappclone.datamodels

import androidx.compose.ui.graphics.painter.Painter

data class ChatListItem(
    val image:Painter,
    val name: String,
    val time: String,
    val message: String
)
