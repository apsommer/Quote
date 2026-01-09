package com.sommerengineering.quote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val quoteService = getQuoteService()
    var quoteState = MutableStateFlow<QuoteState>(QuoteState.Loading)

    var _quote by mutableStateOf("")

    init {

        viewModelScope.launch(Dispatchers.IO) {

            quoteState.value = QuoteState.Loading

            try {
                val response = quoteService.getQuotes()

                response.body()?.let {
                    quoteState.value = QuoteState.Success(it.get(0))
                }

            } catch (e: Exception) {
                quoteState.value = QuoteState.Error(e.toString())
            }

        }

        viewModelScope.launch {

            val response = quoteService.getQuotes()

            if (response.isSuccessful) {
                response.body()?.get(0)?.q?.let {
                    _quote = it
                }
            }
        }
    }
}