package com.sommerengineering.quote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getQuoteService() : QuoteService {

    // https://zenquotes.io/api/[mode]/[key]?option1=value&option2=value

    val retrofit = Retrofit.Builder()
        .baseUrl("https://zenquotes.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(QuoteService::class.java)
    return service
}

