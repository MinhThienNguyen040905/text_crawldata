package com.example.doctruyentemple.Activity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.doctruyentemple.Domain.StoryItemModel
import com.example.doctruyentemple.R

@Composable
fun ReadListScreen(listName: String, storyItems: List<StoryItemModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2F1B24))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { /* Handle back click */ }) {
                Text(
                    text = "< Back",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = listName,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(id = R.drawable.setting_ic),
                contentDescription = "Settings",
                tint = Color.White,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { /* Xử lý click */ }
            )
        }

        // Danh sách cuộn (LazyColumn)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(storyItems) { story ->
                StoryCard3(story = story)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun StoryListScrennPreview()
{
    ReadListScreen("Litname",
        ExampleList)
}