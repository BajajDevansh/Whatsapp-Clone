package com.dbajaj.whatsappclone.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dbajaj.whatsappclone.R
import com.dbajaj.whatsappclone.navigation.Routes
import com.dbajaj.whatsappclone.viewmodels.AuthState
import com.dbajaj.whatsappclone.viewmodels.PhoneAuthViewModel


@Composable
fun UserRegistrationScreen(
    navController: NavController,
    viewModel: PhoneAuthViewModel=hiltViewModel()
){
    val authState by viewModel.authState.collectAsState()
    val context=LocalContext.current
    val activity=context as Activity
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
    var otp by remember {
        mutableStateOf("")
    }
    var verificationId by remember {
        mutableStateOf<String?>(null)
    }
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(16.dp).windowInsetsPadding(WindowInsets.statusBars),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Enter Your Phone Number",
            color = colorResource(R.color.dark_green),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(Modifier.height(24.dp))
        Row {
            Text("Whatsapp will need to verify your phone number. ")

        }
        Text("What's", color = colorResource(R.color.light_green))
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
        when(authState){
            is AuthState.CodeSent,is AuthState.Ideal,is AuthState.Loading->{

                if(authState is AuthState.CodeSent){
                    verificationId=(authState as AuthState.CodeSent).verificationId
                }
                if(verificationId==null){
                    Spacer(Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),

                    ) {
                        TextField(
                            value=countryCode,
                            onValueChange = {countryCode=it},
                            modifier = Modifier.width(70.dp),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = colorResource(R.color.light_green),
                                unfocusedIndicatorColor = colorResource(R.color.light_green),
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )
                        Spacer(Modifier.width(8.dp))
                        TextField(
                            value = phoneNumber,
                            onValueChange = {phoneNumber=it},
                            placeholder = {Text("Enter Phone Number")},
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                            )
                        )
                        Spacer(Modifier.height(16.dp))

                    }
                    Button(onClick = {
                        if(phoneNumber.isNotEmpty()){
                            val phone="$countryCode$phoneNumber"
                            viewModel.sendVerificationCode(phone,activity)

                        }
                        else{
                            Toast.makeText(context,"Enter Phone Number",Toast.LENGTH_SHORT).show()
                        }
                    }, shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(R.color.dark_green)
                        )){
                        Text("Send OTP")
                    }
                    if(authState is AuthState.Loading){
                        Spacer(Modifier.height(16.dp))
                        CircularProgressIndicator()
                    }

                }else{
                    Spacer(Modifier.height(40.dp))
                    Text(
                        "Enter OTP",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.dark_green)
                    )
                    Spacer(Modifier.height(8.dp))
                    TextField(
                        value = otp,
                        onValueChange = {otp=it},
                        singleLine = true,
                        placeholder = {Text("Enter OTP")},
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                        )

                    )
                    Spacer(Modifier.height(32.dp))
                    Button(onClick = {
                        if(otp.isNotEmpty() && verificationId!=null){
                            viewModel.verifyCode(otp,context)
                        }
                        else{
                            Toast.makeText(context,"Enter OTP",Toast.LENGTH_SHORT).show()
                        }
                    }, shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(R.color.dark_green)
                        )){
                        Text("Verify OTP")
                    }
                    if(authState is AuthState.Loading){
                        Spacer(Modifier.height(16.dp))
                        CircularProgressIndicator()
                    }
                }
            }
            is AuthState.Success->{
                viewModel.resetAuthState()
                navController.navigate(Routes.UserProfile){
                    popUpTo<Routes.UserRegistration>{
                        inclusive=true
                    }

                }
            }
            is AuthState.Error->{
                val message=(authState as AuthState.Error).message
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
            }
        }
    }
}