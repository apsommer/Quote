package com.sommerengineering.quote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sommerengineering.quote.ui.theme.QuoteTheme

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = MainViewModel()

        setContent {
            QuoteTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val quoteState = viewModel.quoteState.collectAsState().value

                    val quote = when (quoteState) {
                        is QuoteState.Loading -> ""
                        is QuoteState.Success -> quoteState.quote.q
                        is QuoteState.Error -> quoteState.message
                    }

                    Text(
                        text = quote,
                        modifier = Modifier
                            .padding(64.dp)
                    )
                }
            }
        }
    }
}
