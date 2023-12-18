package com.graph.apollo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.graph.apollo.R
import com.graph.apollo.domain.Messages
import com.graph.apollo.presentation.CharactersListViewModel
import com.graph.apollo.ui.components.CharacterPreview
import com.graph.apollo.ui.components.SearchField
import kotlinx.coroutines.channels.consumeEach

class CharactersListScreen : Screen {

    @Composable
    override fun Content() {

        val charactersViewModel = hiltViewModel<CharactersListViewModel>()

        val charactersPagingFlow = charactersViewModel.charactersPagingFlow.collectAsLazyPagingItems()

        val navigator = LocalNavigator.currentOrThrow

        val context = LocalContext.current

        LaunchedEffect(key1 = Unit){
            charactersViewModel.messagesChannel.consumeEach { message ->
                when(message){
                    Messages.ERROR_SAME_QUERY -> Toast.makeText(context, context.getString(R.string.error_same_search_query), Toast.LENGTH_SHORT).show()
                }
            }
        }

        Column (modifier = Modifier.fillMaxSize()) {

            SearchField(
                label = stringResource(id = R.string.hint_search_character),
                initialSearchQuery = charactersViewModel.getSearchQuery(),
                onSearchButtonClick = charactersViewModel::updateSearchQuery
            )

            Spacer(modifier = Modifier.height(25.dp))


            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .weight(1f)){


                items(count = charactersPagingFlow.itemCount, key = charactersPagingFlow.itemKey {
                    it.id
                }){ index ->
                    charactersPagingFlow[index]?.let {
                        CharacterPreview(character = it,
                            onCharacterClick = { selectedCharacterId ->
                                navigator.push(CharacterDescriptionScreen(selectedCharacterId))
                            })
                        Spacer(modifier = Modifier.height(25.dp))
                    }
                }
            }
        }
    }
}