package com.graph.apollo.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import coil.compose.AsyncImage
import com.graph.apollo.presentation.CharacterDescriptionViewModel
class CharacterDescriptionScreen(
    private val characterId: Int
) : Screen {

    @Composable
    override fun Content() {

        val viewModel = hiltViewModel<CharacterDescriptionViewModel>()

        val characterState = viewModel.selectedCharacter.collectAsState()

        LaunchedEffect(key1 = Unit){
            viewModel.getCharacterDescription(characterId = characterId)
        }

        Box(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), contentAlignment = Alignment.TopCenter){
            AsyncImage(
                model = characterState.value.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(230.dp)
                    .height(345.dp),
            )
        }





    }
}