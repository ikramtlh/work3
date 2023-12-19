package com.example.work3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(15.dp)
    ) {
        DeliveryInfo()
        Recommendation()
        Menu ()
    }
}
@Composable
fun DeliveryInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text("DELIVERY",
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 8.sp
                    )
                )
                Text("Bangalore,India ﹀ ",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .clip(CircleShape)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_profilepic),
                    contentDescription = "profile pic",
                    modifier = Modifier.clip(CircleShape)
                )
            }
        }
    }
}

@Composable
fun Recommendation() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Text("Recommended",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text("Based on your purchase history",
                style = TextStyle(
                    color = Color(0xFFCECECE),
                    fontSize = 8.sp
                )
            )
        }
        Box(
            contentAlignment = Alignment.TopEnd
        ){
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
            ){
                Text(text = "View all",
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Cards(painter = painterResource(id = R.drawable.wrap), contentDescription = "wrap", title = "Wrap poulet")
}

@Composable
fun Cards(
    painter: Painter,
    contentDescription: String,
    title: String,
){
    val items1 = listOf("Small ", "Medium ", "Large ")
    val scrollState = rememberScrollState()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 475.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(
                1.dp,
                Color(0xFFCECECE)
            ),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(5.dp))
            RatingBar(
                initialRating = 4f,
                maxRating = 5,
                onRatingChanged = { newRating ->
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow1(items = items1)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(Color(0xFFCECECE)),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Customize & Add",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Add to cart",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
fun RatingBar(initialRating: Float, maxRating: Int, onRatingChanged: (Float) -> Unit)
{
    var rating by remember { mutableStateOf(initialRating) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(maxRating) { index ->
            Icon(
                painter = painterResource(id = R.drawable.star1),
                contentDescription = null,
                tint = if (index < rating) Color.Red else Color.Gray,
                modifier = Modifier
                    .clickable { onRatingChanged(index + 1f) }
                    .size(15.dp)
            )
        }
    }
}

@Composable
fun LazyRow1(items: List<String>)
{
    var selectedItem by remember { mutableStateOf<String?>(null) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                val isSelected = selectedItem == item
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .border(
                            1.dp,
                            if (isSelected) Color.Red else Color(0xFFCECECE)
                        )
                        .clickable {
                            selectedItem = if (isSelected) null else item
                        }
                        .width(100.dp)
                        .height(40.dp)
                ) {
                    Text(
                        text = item,
                        color = if (isSelected) Color.Black else Color(0xFF9D9D9D),
                        modifier = Modifier
                            .padding(8.dp),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun Menu() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Text("Menu",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text("what's on our menu",
                style = TextStyle(
                    color = Color(0xFFCECECE),
                    fontSize = 8.sp
                )
            )
        }
        Box(
            contentAlignment = Alignment.TopEnd
        ){
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(text = "View all",
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ImageCard(
                painterResource(id = R.drawable.veg),
                "veg",
                "Veg Pizza",
                modifier = Modifier.weight(1f)
            )
            ImageCard(
                painterResource(id = R.drawable.pizz),
                "meat pizza",
                "Meat Delights",
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ImageCard(
                painterResource(id = R.drawable.neg),
                "nuggets",
                "Nuggets",
                modifier = Modifier.weight(1f)
            )
            ImageCard(
                painterResource(id = R.drawable.beg),
                "burgers",
                "Burgers",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.Transparent)

    ){
        Box(modifier = Modifier
            .background(color = Color.Transparent)
            .height(150.dp)
            .clip(
                RoundedCornerShape(18.dp)
            )
        ){
            Image(
                painter= painter,
                contentDescription= contentDescription,
                contentScale= ContentScale.Crop,
            )
        }
        Box(
            modifier= Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Box(contentAlignment = Alignment.BottomStart){
                Text(title,
                    style= TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                )
            }
        }
    }

}


