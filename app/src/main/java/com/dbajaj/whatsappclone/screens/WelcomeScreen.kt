package com.dbajaj.whatsappclone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dbajaj.whatsappclone.R
import com.dbajaj.whatsappclone.navigation.Routes

@Composable
fun WelcomeScreen(navController: NavController){

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.whatsapp_sticker),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
        Text("Welcome to WhatsApp",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp)
        Spacer(Modifier.height(24.dp))
        Row {
            Text("Read our " , color = Color.Gray)
            Text("Privacy Policy. ",
                color = colorResource(R.color.light_green))
            Text("Tap 'Agree and Continue' to", color = Color.Gray)
        }
        Row {
            Text("accept the ", color = Color.Gray)
            Text("Terms of services",
                color = colorResource(R.color.light_green))
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = {
            navController.navigate(Routes.UserRegistration)
        },
            modifier = Modifier.size(280.dp,43.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_green)
            )) {
            Text("Agree and Continue", fontSize = 16.sp)
        }
    }
}