package com.sommerengineering.quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val quoteService = getQuoteService()
    var quoteState = MutableStateFlow<QuoteState>(QuoteState.Loading)

    init {

        viewModelScope.launch {

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
    }
}