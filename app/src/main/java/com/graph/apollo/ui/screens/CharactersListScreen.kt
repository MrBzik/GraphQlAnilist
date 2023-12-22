package com.graph.apollo.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
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
import com.graph.apollo.utils.Logger
import com.graph.apollo.utils.TestTags
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow

class CharactersListScreen : Screen {

    @Composable
    override fun Content() {

        val charactersViewModel = hiltViewModel<CharactersListViewModel>()

        val charactersPagingFlow = charactersViewModel.charactersPagingFlow.collectAsLazyPagingItems()

        val navigator = LocalNavigator.currentOrThrow

        val context = LocalContext.current

        val lifecycleOwner = LocalLifecycleOwner.current

        val snackbarHostState  = remember { SnackbarHostState() }

        LaunchedEffect(lifecycleOwner.lifecycle){

            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){

                charactersViewModel.messagesFlow.collectLatest { message ->
                    when(message){
                        Messages.ERROR_SAME_QUERY -> {
                            snackbarHostState.showSnackbar(context.getString(R.string.error_same_search_query))
                        }
                    }
                }
            }
        }

        Scaffold(snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }) { paddingValues ->

            Column (modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            ) {

                SearchField(
                    label = stringResource(id = R.string.hint_search_character),
                    initialSearchQuery = charactersViewModel.getSearchQuery(),
                    onSearchButtonClick = charactersViewModel::updateSearchQuery
                )

                Spacer(modifier = Modifier.height(25.dp))


                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .testTag(TestTags.CHARACTERS_LAZY_COLUMN)
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
}