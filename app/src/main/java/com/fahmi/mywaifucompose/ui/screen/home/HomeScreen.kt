package com.fahmi.mywaifucompose.ui.screen.home

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fahmi.mywaifucompose.data.WaifusRepository
import com.fahmi.mywaifucompose.ui.component.CharacterHeader
import com.fahmi.mywaifucompose.ui.component.ScrollToTopButton
import com.fahmi.mywaifucompose.ui.component.SearchBar
import com.fahmi.mywaifucompose.ui.component.WaifuListItem
import com.fahmi.mywaifucompose.ui.theme.MyWaifuComposeTheme
import com.fahmi.mywaifucompose.viewmodel.HomeViewModel
import com.fahmi.mywaifucompose.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(
        WaifusRepository())),
    navigateToDetail: (String) -> Unit,
) {
    val groupedWaifus by viewModel.groupedWaifus.collectAsState()
    val query by viewModel.query
    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    modifier = Modifier.background(MaterialTheme.colors.primary)
                )
            }
            groupedWaifus.forEach { (initial, waifus) ->
                stickyHeader {
                    CharacterHeader(initial)
                }
                items(waifus, key = { it.id }) { waifu ->
                    WaifuListItem(
                        id = waifu.id,
                        name = waifu.name,
                        photoUrl = waifu.photoUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 100)),
                        navigateToDetail = navigateToDetail
                    )

                }
            }
        }

        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyWaifuComposeTheme {
        HomeScreen(navigateToDetail = {})
    }
}