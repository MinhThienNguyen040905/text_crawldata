package com.example.doctruyentemple.Activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doctruyentemple.Domain.StoryItemModel
import com.example.doctruyentemple.R

@Composable
fun HorizontalStoryList (isStoriesLoading:Boolean, storyList: List<StoryItemModel>)
{
    Column(
        modifier = Modifier.fillMaxSize()
    )
    {
        SectionTitle("Gợi ý cho bạn")
        if (isStoriesLoading)
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(storyList) { item ->
                    StoryCard(item, onClick = {})
                }
            }
        }

    }

}

@Composable
fun TwoLineHorizontalStoryList(
    stories: List<StoryItemModel> = emptyList() ,
    isStoriesLoading: Boolean=true,
    onStoryClick: (StoryItemModel) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        SectionTitle("Truyện mới")
        if (isStoriesLoading)
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        else {
            // Chia list thành các cặp 2 story (nếu lẻ thì item cuối chỉ có 1 story)
            val pairedStories = stories.chunked(2)

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(pairedStories) { pair ->
                    Column(
                        modifier = Modifier.wrapContentSize(), // Chiều rộng cố định cho mỗi cột
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Card trên
                        StoryCard2(
                            story = pair[0],
                            modifier = Modifier.wrapContentSize(),
                            onStoryClick = { onStoryClick(pair[0]) }
                        )

                        // Card dưới (nếu có)
                        if (pair.size > 1) {
                            StoryCard2(
                                story = pair[1],
                                modifier = Modifier.wrapContentSize(),
                                onStoryClick = { onStoryClick(pair[1]) }
                            )
                        } else {
                            // Nếu không có story thứ 2, để khoảng trống
                            Spacer(modifier = Modifier.height(132.dp)) // = height card + spacing
                        }
                    }
                }
            }
        }
    }


}


@Composable
fun TopStoriesRanking(
    stories: List<StoryItemModel>,
    isStoriesLoading: Boolean

) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween, // Đẩy các thành phần ra hai đầu
            verticalAlignment = Alignment.CenterVertically
        )
        {
            SectionTitle("Top Ranking")
            TextButton(
                onClick = {},
                modifier = Modifier.padding(end=10.dp)
            ) {
                Text(
                    text = "Show full list >",
                    color = Color.White,
                    fontSize = 8.sp
                )
            }
        }
        if (isStoriesLoading)
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        else {
            val top5Stories = stories.take(5) // Chỉ lấy 5 item đầu

            LazyColumn(
                modifier = Modifier.height(1200.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(top5Stories.size) { index ->
                    val story = top5Stories[index]

                    Box(modifier = Modifier.fillMaxWidth()) {
                        // Sử dụng lại card của bạn
                        StoryCard3(
                            story = story,
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Thêm huy chương cho top 3
                        if (index < 3) {
                            val iconRes = when (index) {
                                0 -> (R.drawable.goldmedal) // Vàng
                                1 -> (R.drawable.silvermedal) // Bạc
                                else -> (R.drawable.bronzemedal) // Đồng
                            }

                            Icon(
                                painter = painterResource(iconRes),
                                contentDescription = "Rank ${index + 1}",
                                tint = Color.Unspecified,

                                modifier = Modifier
                                    .size(70.dp)
                                    .align(Alignment.TopStart)
                                    .padding(top = 8.dp, end = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }


}
