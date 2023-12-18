package com.graph.apollo.ui.screens


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.graph.apollo.presentation.CharacterDescriptionViewModel
import com.graph.apollo.utils.Logger

class CharacterDescriptionScreen(
    private val characterId: Int,
    private val sharedContent: @Composable () -> Unit
) : Screen {

    @Composable
    override fun Content() {

        val viewModel = hiltViewModel<CharacterDescriptionViewModel>()

        val characterState = viewModel.selectedCharacter.collectAsState()

        val scrollState = rememberScrollState()

        val density = LocalDensity.current.density

        val navigator = LocalNavigator.current

        var isImageBgVisible by remember {
            mutableStateOf(false)
        }


        LaunchedEffect(key1 = Unit){
            viewModel.getCharacterDescription(characterId = characterId)
        }

        LaunchedEffect(key1 = scrollState.value){


        }

        val request = ImageRequest.Builder(LocalContext.current)
            .data(characterState.value.imageUrl)
            .build()



        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)){


            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.background(Color.LightGray).fillMaxWidth()) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Filled.ArrowBack),
                    contentDescription = null,
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { navigator?.pop() }
                )

                Text(text = characterState.value.nameFull, color = Color.Red)
            }

            Box(modifier = Modifier.fillMaxSize()) {

                this@Column.AnimatedVisibility(
                    visible = scrollState.value / density > 345,
                    enter = fadeIn(animationSpec = tween(durationMillis = 500)),
                    exit = fadeOut(animationSpec = tween(durationMillis = 500))

                ) {

                    AsyncImage(
                        model = request,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.3f),
                        contentScale = ContentScale.Crop)
                }


                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(scrollState)) {

//                    AsyncImage(
//                        model = request,
//                        contentDescription = null,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(345.dp)
//                    )

                    sharedContent()

                    Text(text = characterState.value.description, color = Color.White)
                }
            }
        }
    }
}

