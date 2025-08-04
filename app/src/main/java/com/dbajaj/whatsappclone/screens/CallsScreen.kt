
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
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
import com.dbajaj.whatsappclone.datamodels.CallListItem
import com.dbajaj.whatsappclone.datamodels.FavouriteListItem

@Composable
@Preview(showSystemUi = true)
fun CallsScreen(){
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
                        "Calls",
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
                    painter = painterResource(R.drawable.add_call),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)){
            item{
                Spacer(modifier = Modifier.height(16.dp))
                Text("Favourites",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 16.dp, bottom = 10.dp)
                )
                LazyRow {
                    item{
                        FavouriteItem(
                            FavouriteListItem(R.drawable.neat_roots,"Neat Roots")
                        )
                        FavouriteItem(
                            FavouriteListItem(R.drawable.neat_roots,"Neat Roots")
                        )
                        FavouriteItem(
                            FavouriteListItem(R.drawable.neat_roots,"Neat Roots")
                        )
                        FavouriteItem(
                            FavouriteListItem(R.drawable.neat_roots,"Neat Roots")
                        )
                        FavouriteItem(
                            FavouriteListItem(R.drawable.neat_roots,"Neat Roots")
                        )
                        FavouriteItem(
                            FavouriteListItem(R.drawable.neat_roots,"Neat Roots")
                        )
                        FavouriteItem(
                            FavouriteListItem(R.drawable.neat_roots,"Neat Roots")
                        )

                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = {},modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.light_green)
                    )
                    ) {
                    Text("Start a new call", fontSize = 17.sp)
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text("Recent Calls",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 16.dp, bottom = 10.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CallItem(
                        CallListItem(
                            R.drawable.kartik_aaryan,
                            "Kartik",
                            "Yesterday 10:00 AM",
                            "missed"
                        )
                    )
                    Icon(
                        painter = painterResource(R.drawable.outline_phone_24),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    CallItem(
                        CallListItem(
                            R.drawable.rashmika,
                            "Rashmi",
                            "Just now",
                            "Received"
                        )
                    )
                    Icon(
                        painter = painterResource(R.drawable.outline_phone_24),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    CallItem(
                        CallListItem(
                            R.drawable.disha_patani,
                            "Disha",
                            "1 hour ago",
                            "outgoing"
                        )
                    )
                    Icon(
                        painter = painterResource(R.drawable.outline_phone_24),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }

            }
        }
    }
}
@Composable
fun FavouriteItem(favItem: FavouriteListItem){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Image(
            painter = painterResource(favItem.image),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(start = 16.dp)
                .clip(CircleShape)
                , contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(5.dp))
        Text(favItem.name, color = Color.Black, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp))
    }

}
@Composable
fun CallItem(
    callItem: CallListItem
){
    val type=callItem.callType
    val image=callItem.image
    val name=callItem.name
    val time=callItem.time
    Row(
        verticalAlignment = Alignment.CenterVertically){
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
        Column {
            Text(name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Row {
                Icon(
                    painter = painterResource(R.drawable.baseline_call_missed_24),
                    contentDescription = null,
                    tint = if(type=="missed") Color.Red
                    else if(type=="outgoing") colorResource(R.color.light_green)
                    else Color.Gray,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    time,
                    fontSize = 16.sp
                )

            }

        }

    }
}