package com.graph.apollo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.graph.apollo.presentation.AnimeCharactersViewModel
import com.graph.apollo.ui.components.SearchField
import com.graph.apollo.ui.theme.ApolloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val charactersViewModel = hiltViewModel<AnimeCharactersViewModel>()

            val characterState = charactersViewModel.selectedCharacter.collectAsState()


            ApolloTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {



                    Column (modifier = Modifier.fillMaxSize()) {

                        Box (modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()) {

                            AsyncImage(model = characterState.value.imageUrl, contentDescription = "")
                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        SearchField(
                            onSearchButtonClick = (charactersViewModel::getNewCharacter)
                        )
                    }
                }
            }
        }
    }
}





