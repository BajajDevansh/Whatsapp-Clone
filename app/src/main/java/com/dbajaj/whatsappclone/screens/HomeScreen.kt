package com.dbajaj.whatsappclone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dbajaj.whatsappclone.R
import com.dbajaj.whatsappclone.datamodels.ChatListItem

@Composable
@Preview(showSystemUi = true)
fun HomeScreen(){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {/*TODO*/ },
                containerColor = colorResource(R.color.dark_green),
                contentColor = Color.White,
                modifier = Modifier.size(65.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_chat_icon),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        topBar = {
            Column(Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(
                        "WhatsApp",
                        color = colorResource(R.color.light_green),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 90.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier
                                .size(26.dp)
                                .clickable {

                                }
                        )
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = null,
                            modifier = Modifier
                                .size(26.dp)
                                .clickable {

                                }
                        )
                        Icon(
                            painter = painterResource(R.drawable.more),
                            contentDescription = null,
                            modifier = Modifier
                                .size(26.dp)
                                .clickable {

                                }
                        )
                    }

                }
                HorizontalDivider(thickness = 2.dp, modifier = Modifier
                    .padding(vertical = 5.dp,horizontal = 16.dp))
            }
        },
        bottomBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp,end = 16.dp).windowInsetsPadding(WindowInsets.navigationBars),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.chat_icon),
                        contentDescription = null,
                        modifier = Modifier.clickable {

                        }.size(28.dp)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text("Chats", fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.update_icon),
                        contentDescription = null,
                        modifier = Modifier.clickable {

                        }.size(28.dp)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text("Updates", fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.communities_icon),
                        contentDescription = null,
                        modifier = Modifier.clickable {

                        }.size(28.dp)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text("Communities", fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.outline_phone_24),
                        contentDescription = null,
                        modifier = Modifier.clickable {

                        }.size(28.dp)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text("Calls", fontWeight = FontWeight.Bold)
                }

            }
        }
    ) {
        val chatData= listOf<ChatListItem>()
        LazyColumn(Modifier.padding(it)){
            items(chatData) {
                ChatView(it.image,it.name,it.time,it.message)
            }
        }
    }
}

@Composable
fun ChatView(image:Int?, name:String?, time:String?,message:String?){
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(image?:R.drawable.outline_account_circle_24),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(start = 16.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(name!!, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(time!!, fontSize = 12.sp, color = Color.Gray,modifier = Modifier.padding(end = 16.dp))
                }
                Text(message!!)
            }

        }
        HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(vertical = 5.dp, horizontal =16.dp))
    }
}