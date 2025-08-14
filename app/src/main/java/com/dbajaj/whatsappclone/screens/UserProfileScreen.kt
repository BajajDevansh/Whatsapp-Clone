package com.dbajaj.whatsappclone.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dbajaj.whatsappclone.viewmodels.PhoneAuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun UserProfileScreen(viewModel: PhoneAuthViewModel,navController: NavController){
    var name by remember {
        mutableStateOf("")
    }
    var status by remember {
        mutableStateOf("")
    }
    var profilePicUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var bitmapImage by remember {
        mutableStateOf<Bitmap?>(null)
    }
    var firebaseAuth = Firebase.auth
    var phoneNumber=firebaseAuth.currentUser?.phoneNumber?:""
    var userId=firebaseAuth.currentUser?.uid?:""
    val context= LocalContext.current
    val imagePickerLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            profilePicUri=it
            it?.let {
                bitmapImage=if(Build.VERSION.SDK_INT>28){
                    @Suppress("DEPRECATION")
                    android.provider.MediaStore.Images.Media.getBitmap(context.contentResolver,it)
                }else{
                    val source=ImageDecoder.createSource(context.contentResolver,it)
                    ImageDecoder.decodeBitmap(source)
                }
            }
        }
    )
    Column(
        modifier = Modifier.padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(Modifier.size(128.dp).clip(CircleShape).border(2.dp, color = Color.Gray, shape = CircleShape)
            .clickable{
                
            }){

        }
    }
}