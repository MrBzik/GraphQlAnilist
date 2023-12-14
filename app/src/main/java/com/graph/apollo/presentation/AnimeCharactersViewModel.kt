package com.graph.apollo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graph.apollo.data.AnimeCharactersClient
import com.graph.apollo.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeCharactersViewModel @Inject constructor(
    private val charactersClient: AnimeCharactersClient
) : ViewModel() {

    private val _selectedCharacter = MutableStateFlow("")
    val selectedCharacter = _selectedCharacter.asStateFlow()
    fun getNewCharacter(query: String) = viewModelScope.launch {

        charactersClient.testLaunch()

    }


}