package com.dbajaj.whatsappclone.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.dbajaj.whatsappclone.R
import com.dbajaj.whatsappclone.navigation.Routes
import com.dbajaj.whatsappclone.viewmodels.PhoneAuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun UserProfileScreen(viewModel: PhoneAuthViewModel=hiltViewModel(),navController: NavController){
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
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(Modifier
            .size(128.dp)
            .clip(CircleShape)
            .border(2.dp, color = Color.Gray, shape = CircleShape)
            .clickable {
                imagePickerLauncher.launch("image/*")
            }){
            if (bitmapImage != null){
                Image(
                    bitmap = bitmapImage!!.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }else if(profilePicUri!=null){
                Image(
                    painter = rememberImagePainter(profilePicUri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            else{
                Image(
                    painter = painterResource(R.drawable.outline_account_circle_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(phoneNumber)
        Spacer(Modifier.height(16.dp))
        TextField(
            value = name,
            onValueChange = {name=it},
            placeholder = {Text("Enter Name")},
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = colorResource(R.color.light_green),

            )
        )
        Spacer(Modifier.height(16.dp))
        TextField(
            value = status,
            onValueChange = {status=it},
            placeholder = {Text("Enter Status")},
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = colorResource(R.color.light_green)
            )
        )
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {
                viewModel.saveUserProfile(userId,name,status,bitmapImage)
                navController.navigate(Routes.Home){

                }
            }, colors = ButtonDefaults.buttonColors(
                colorResource(R.color.dark_green)
            )
        ) {
            Text("Save")
        }
    }
}