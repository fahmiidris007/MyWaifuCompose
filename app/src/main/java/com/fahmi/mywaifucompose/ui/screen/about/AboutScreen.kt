package com.fahmi.mywaifucompose.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fahmi.mywaifucompose.R

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    val profileImage = painterResource(id = R.drawable.profile_image)
    val name = stringResource(id = R.string.myname)
    val email = stringResource(id = R.string.myemail)

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(id = R.string.menu_about),
                        style = MaterialTheme.typography.h1,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
    ) {
        Column(modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = profileImage,
                contentDescription = stringResource(R.string.profile_image),
                modifier = Modifier.size(200.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.h2,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = email,
                style = MaterialTheme.typography.h3,
                fontSize = 18.sp
            )
        }
    }
}