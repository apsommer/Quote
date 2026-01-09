package com.sommerengineering.quote

import retrofit2.Response
import retrofit2.http.GET

// required for response with base as array without name
class Quotes : ArrayList<Quote>()

data class Quote (
    val q: String,
    val a: String,
    val i: String?,
    val c: String?,
    val h: String?
)

sealed class QuoteState {
    object Loading : QuoteState()
    data class Error(val message: String): QuoteState()
    data class Success(val quote: Quote) : QuoteState()
}

interface QuoteService {

    @GET("random")
    suspend fun getQuotes(): Response<Quotes>
}