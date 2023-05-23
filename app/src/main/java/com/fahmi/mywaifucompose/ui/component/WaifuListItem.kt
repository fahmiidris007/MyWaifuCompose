package com.fahmi.mywaifucompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun WaifuListItem(
    id: String,
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable {
                navigateToDetail(id)
            }
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = "Waifu Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 16.dp)
            )
        }
    }
}

