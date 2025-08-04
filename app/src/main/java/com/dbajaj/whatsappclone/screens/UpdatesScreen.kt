package com.dbajaj.whatsappclone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dbajaj.whatsappclone.R
import com.dbajaj.whatsappclone.datamodels.ChannelListItem
import com.dbajaj.whatsappclone.datamodels.UpdateListItem

@Composable
@Preview(showSystemUi = true)
fun UpdatesScreen(){
    Scaffold(
        topBar = {
            var isSearching by remember {
                mutableStateOf(false)
            }
            var search by remember {
                mutableStateOf("")
            }
            var showMenu by remember {
                mutableStateOf(false)
            }
            Column(Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    if(isSearching){
                        Row(horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()){
                            TextField(
                                value = search,
                                onValueChange = {
                                    search = it
                                },
                                placeholder = { Text("Search") },
                                modifier = Modifier
                                    .padding(start = 12.dp)
                                    .height(50.dp),
                                singleLine = true,
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent
                                )
                            )
                            Icon(
                                painter = painterResource(R.drawable.cross),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        isSearching = false
                                        search = ""
                                    }
                            )
                        }
                    }
                    else{
                        Text(
                            "Updates",
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
                            IconButton(onClick = {}){
                                Icon(
                                    painter = painterResource(R.drawable.camera),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(26.dp)
                                        .clickable {

                                        }
                                )
                            }
                            IconButton(onClick = {isSearching = true}){
                                Icon(
                                    painter = painterResource(R.drawable.search),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(24.dp)

                                )
                            }
                            IconButton(onClick = {
                                showMenu=true
                            }){
                                Icon(
                                    painter = painterResource(R.drawable.more),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                                DropdownMenu(expanded = showMenu, onDismissRequest = {showMenu = false}) {
                                    DropdownMenuItem(text = { Text("Status Privacy") },
                                        onClick = {showMenu = false})
                                    DropdownMenuItem(text = { Text("Create Channel") },
                                        onClick = {showMenu = false})
                                    DropdownMenuItem(text = { Text("Settings") },
                                        onClick = {showMenu = false})

                                }
                            }
                        }
                    }

                }
                HorizontalDivider(thickness = 2.dp, modifier = Modifier
                    .padding(vertical = 5.dp,horizontal = 16.dp))
            }
        },
        bottomBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .windowInsetsPadding(WindowInsets.navigationBars),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.chat_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {

                            }
                            .size(28.dp)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text("Chats", fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.update_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {

                            }
                            .size(28.dp)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text("Updates", fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.communities_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {

                            }
                            .size(28.dp)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text("Communities", fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.outline_phone_24),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {

                            }
                            .size(28.dp)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text("Calls", fontWeight = FontWeight.Bold)
                }

            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {/*TODO*/ },
                containerColor = colorResource(R.color.dark_green),
                contentColor = Color.White,
                modifier = Modifier.size(65.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_photo_camera_24),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

    ) {
        var channelsData= listOf<ChannelListItem>(
            ChannelListItem(R.drawable.neat_roots,"Neat Roots","Follow for tech updates"),
            ChannelListItem(R.drawable.neat_roots,"Neat Roots","Follow for tech updates"),
            ChannelListItem(R.drawable.neat_roots,"Neat Roots","Follow for tech updates"),
            ChannelListItem(R.drawable.neat_roots,"Neat Roots","Follow for tech updates"),
            ChannelListItem(R.drawable.neat_roots,"Neat Roots","Follow for tech updates")
        )
        var updatesData= listOf<UpdateListItem>(
            UpdateListItem(R.drawable.salman_khan,"Salman Khan","Just Now"),
            UpdateListItem(R.drawable.rashmika,"Rashmi","1 hour ago"),
            UpdateListItem(R.drawable.sharadha_kapoor,"Shradhha","10 minutes ago"),
            UpdateListItem(R.drawable.kartik_aaryan,"Kartik Aaryan","Just Now"),
        )
        LazyColumn(modifier = Modifier.padding(it)) {
            item{
                Text("Updates",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 10.dp,bottom = 10.dp))
                Spacer(Modifier.height(10.dp))
                MyStatus()
                Spacer(Modifier.height(10.dp))
            }
            items(updatesData.size){
                val updateItem=updatesData[it]
                UpdateItem(updateItem.image,updateItem.name,updateItem.time)
                Spacer(Modifier.height(10.dp))
            }
            item{
                Spacer(Modifier.height(16.dp))
                HorizontalDivider(color=Color.Gray, modifier = Modifier.padding(horizontal = 16.dp))
                Text(
                    "Channels",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 10.dp,bottom = 10.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text("Stay updated on topics that matter to you. Find channels to follow below",
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp))
                Spacer(Modifier.height(32.dp))
                Text("Find channels to follow",modifier = Modifier.padding(start = 16.dp))
                Spacer(Modifier.height(16.dp))

            }
            items(channelsData.size){
                val channelItem=channelsData[it]
                ChannelItem(channelItem.image,channelItem.name,channelItem.description)
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}
@Composable
fun MyStatus(){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box {
            Image(
                painter = painterResource(R.drawable.bhuvan_bam),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(start = 16.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Icon(
                painter = painterResource(R.drawable.baseline_add_24),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.BottomEnd)
                    .padding(2.dp)
                    .background(
                        color = colorResource(R.color.dark_green),
                        shape = CircleShape
                    )
            )
        }
        Spacer(Modifier.width(16.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Text("My Status", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Tap to add status update", fontSize = 12.sp, color = Color.Gray)
        }
    }
}
@Composable
fun UpdateItem(image: Int, name:String, time:String){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(start = 16.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop)
        Spacer(Modifier.width(16.dp))
        Column() {
            Text(name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(time, fontSize = 12.sp, color = Color.Gray)
        }
    }

}
@Composable
fun ChannelItem(
    image: Int,
    name: String,
    description: String
){
    var isFollowing by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(start = 16.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
             Column {
                Text(name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(description)
            }
            Button(onClick = {isFollowing=!isFollowing},colors = ButtonDefaults.buttonColors(
                containerColor = if(isFollowing) Color.Gray else colorResource(R.color.light_green)
            )){
                Text(if(isFollowing) "Following" else "Follow")
            }

        }

    }
}