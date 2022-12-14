package com.sgut.android.nationalfootballleague.atheletedetail

import androidx.compose.material3.CircularProgressIndicator

sealed class ProfileUiState

object Loading: ProfileUiState()

object Error: ProfileUiState()

data class ProfileLoaded(
    val name: String,
    val thing: String
): ProfileUiState()

// render updates uiState

data class ListViewState(
    val isLoading: Boolean,
    val items: List<String>
)

private fun render2 (viewState: ListViewState){
    val progressBarIsVisible = viewState.isLoading
    val submitList = viewState.items
}

private fun render(viewState: ProfileUiState) {
    when(viewState){
        Loading -> {
            val loadingProg = 0
        }
        Error -> {
            val loadErrorScreen = 0
        }
        is ProfileLoaded->{
            val compScreenWithParamOpenScreen = 0
        }
    }
}

//fun loadMore() {
//    viewModelScope.launch {
//        state = state.copy(isLoading = true)
//
//        val newWords = getMoreWords()
//
//        state = state.copy(
//            isLoading = false,
//            items = state.items + newWords
//        )
//    }
//}