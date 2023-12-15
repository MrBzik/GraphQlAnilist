package com.graph.apollo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graph.apollo.data.AnimeCharactersClient
import com.graph.apollo.domain.models.AnimeCharacterDescription
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDescriptionViewModel @Inject constructor (
    private val charactersClient: AnimeCharactersClient
) : ViewModel() {


    private val _selectedCharacter = MutableStateFlow(AnimeCharacterDescription())
    val selectedCharacter = _selectedCharacter.asStateFlow()

    fun getCharacterDescription(characterId: Int) = viewModelScope.launch {
        charactersClient.getCharacterById(characterId)?.let {
            _selectedCharacter.value = it
        }
    }

}