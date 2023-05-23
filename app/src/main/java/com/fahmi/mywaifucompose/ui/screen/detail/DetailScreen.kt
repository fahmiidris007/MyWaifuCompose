package com.fahmi.mywaifucompose.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.fahmi.mywaifucompose.R
import com.fahmi.mywaifucompose.data.WaifusRepository
import com.fahmi.mywaifucompose.viewmodel.DetailViewModel
import com.fahmi.mywaifucompose.viewmodel.ViewModelFactory

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    idWaifu: String,
    navigateUp: () -> Unit,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            WaifusRepository(),
        ),
    ),
) {
    val waifuData by viewModel.getWaifuData(idWaifu).collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(
                        onClick = navigateUp
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.detail_title),
                        style = MaterialTheme.typography.h1,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(model = waifuData.photoUrl, contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = waifuData.name,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = waifuData.description,
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

        }
    }
}