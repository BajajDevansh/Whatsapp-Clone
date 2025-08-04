package com.dbajaj.whatsappclone.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dbajaj.whatsappclone.R
import java.nio.file.WatchEvent

@Composable
@Preview(showSystemUi = true)
fun UserRegistrationScreen(){
    var expanded by remember {
        mutableStateOf(false)
    }
    var country by remember {
        mutableStateOf("India")
    }
    var countryCode by remember {
        mutableStateOf("+91")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Enter Your Phone Number",
            color = colorResource(R.color.dark_green),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(Modifier.height(24.dp))
        Row {
            Text("Whatsapp will need to verify your phone number. ")
            Text("What's", color = colorResource(R.color.light_green))
        }
        Text("my number?", color = colorResource(R.color.light_green))
        Spacer(Modifier.height(16.dp))
        TextButton(onClick = {expanded=true},
            modifier = Modifier.fillMaxWidth()) {
            Box(Modifier.width(230.dp)){
                Text(country,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    tint = colorResource(R.color.dark_green)
                )
            }

        }
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 66.dp),
            thickness=2.dp,
            color = colorResource(R.color.light_green)
        )
        DropdownMenu(expanded = expanded, onDismissRequest = {
            expanded=false
        }) {
            listOf("India", "Japan", "Canada", "USA").forEach {
                DropdownMenuItem(
                    text = {
                        Text(it)
                    },
                    onClick = {
                        country=it
                        expanded=false
                    }
                )
            }

        }
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                TextField(value = countryCode, onValueChange = {
                    countryCode=it
                }, modifier = Modifier.width(70.dp),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 18.sp
                    ), colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = colorResource(R.color.light_green),
                        focusedIndicatorColor = colorResource(R.color.light_green)
                    )
                )
                Spacer(Modifier.width(6.dp))
                TextField(value = phoneNumber, onValueChange = {
                    phoneNumber=it
                }, placeholder = {
                    Text("Enter Your Phone Number")
                },
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 18.sp
                    ), colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = colorResource(R.color.light_green),
                        focusedIndicatorColor = colorResource(R.color.light_green)
                    )
                )
            }
            Spacer(Modifier.height(16.dp))
            Text("Carrier Charges may Apply",color=Color.Gray,
                fontSize = 12.sp)
            Spacer(Modifier.height(26.dp))
            Button(
                onClick = {},
                shape= RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.dark_green)
                )
            ) {
                Text("Next", fontSize = 16.sp)
            }
        }

    }
}