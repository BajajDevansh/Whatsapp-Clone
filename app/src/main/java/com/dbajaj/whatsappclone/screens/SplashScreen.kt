package com.dbajaj.whatsappclone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dbajaj.whatsappclone.R
import com.dbajaj.whatsappclone.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    LaunchedEffect(Unit){
        delay(1000)
        navController.navigate(Routes.Welcome){
            popUpTo<Routes.Splash>{
                inclusive=true
            }
        }
    }
    Box(modifier= Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.whatsapp_icon),
            contentDescription = null,
            modifier = Modifier.size(80.dp).align(Alignment.Center)
        )
        Column(modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("From", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Row (verticalAlignment = Alignment.CenterVertically){
                Icon(
                    painter = painterResource(R.drawable.meta),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = colorResource(R.color.light_green)
                )
                Spacer(Modifier.width(1.dp))
                Text("Meta", color = colorResource(R.color.light_green), fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}