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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.dbajaj.whatsappclone.datamodels.CommunityListItem

@Composable
@Preview(showSystemUi = true)
fun CommunitiesScreen(){
    Scaffold(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(
                WindowInsets.navigationBars
            ),
        topBar = {
            Column(Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(
                        "Communities",
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
                        IconButton(onClick = {}) {
                            Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = null,
                            modifier = Modifier
                                .size(26.dp)

                        )}

                        IconButton(onClick = {}){
                            Icon(
                                painter = painterResource(R.drawable.more),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(26.dp)
                            )
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
        val communityData= listOf<CommunityListItem>(
            CommunityListItem(R.drawable.neat_roots,"Neat Roots","12 members"),
            CommunityListItem(R.drawable.neat_roots,"Neat Roots","12 members"),
            CommunityListItem(R.drawable.neat_roots,"Neat Roots","12 members")
        )
        LazyColumn(modifier = Modifier.padding(it)) {
            item{
                Button(onClick = {},
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.light_green),
                    )){
                    Text("Start a new Community", fontSize = 17.sp)

                }
                Spacer(modifier = Modifier.height(32.dp))
                Text("Your communities",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 16.dp, bottom = 10.dp))
            }
            items(communityData.size){
                val communityItem=communityData[it]
                CommunityItem(communityItem.image,communityItem.name,communityItem.memberCount)
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun CommunityItem(image: Int, name:String, memberCount:String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(start = 16.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(memberCount, color = Color.DarkGray)
            }
        }
    }
}