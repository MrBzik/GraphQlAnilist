package com.graph.apollo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.graph.apollo.presentation.CharactersListViewModel
import com.graph.apollo.ui.components.CharacterPreview
import com.graph.apollo.ui.components.SearchField
import com.graph.apollo.utils.Logger
import com.skydoves.orbital.Orbital

class CharactersListScreen : Screen {
    @Composable
    override fun Content() {


        Orbital {

            val charactersViewModel = hiltViewModel<CharactersListViewModel>()

            val charactersPagingFlow = charactersViewModel.charactersPagingFlow.collectAsLazyPagingItems()

            val navigator = LocalNavigator.currentOrThrow

            var expandedImage : @Composable () -> Unit by remember{ mutableStateOf({}) }

            var isInListMode by remember{ mutableStateOf(true) }


            if(isInListMode){
                Column (modifier = Modifier.fillMaxSize()) {

                    SearchField(
                        initialSearchQuery = charactersViewModel.getSearchQuery(),
                        onSearchButtonClick = charactersViewModel::updateSearchQuery
                    )


                    Spacer(modifier = Modifier.height(25.dp))


                    LazyColumn(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .weight(1f),
                    ){


                        items(count = charactersPagingFlow.itemCount, key = charactersPagingFlow.itemKey {
                            it.id
                        }){ index ->
                            charactersPagingFlow[index]?.let { character ->
                                CharacterPreview(character = character,
//                              onItemClicked = { isItemExpanded ->
//                                  isInListMode = !isItemExpanded
//                              }
                                    shareImage = { image ->
                                        expandedImage = image
                                        isInListMode = false

//                                isScrollEnabled = !isScrollEnabled
//                                sharedImage ->
//                                navigator.push(CharacterDescriptionScreen(
//                                    characterId = character.id,
//                                    sharedContent = sharedImage
//                                ))
                                    }
                                )
                                Spacer(modifier = Modifier.height(25.dp))
                            }
                        }
                    }
                }
            }

            else {

                Box(modifier = Modifier
                    .fillMaxSize()
                    .clickable { isInListMode = !isInListMode }) {
                    expandedImage()
                }
            }
        }
    }
}