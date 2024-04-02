package com.lifebetter.simplegymapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.lifebetter.simplegymapp.R
import com.lifebetter.simplegymapp.domain.Error

@Composable
fun ErrorText(error: com.lifebetter.simplegymapp.domain.Error, modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = error.toUiString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun com.lifebetter.simplegymapp.domain.Error.toUiString() = when (this) {
    com.lifebetter.simplegymapp.domain.Error.Connectivity -> stringResource(R.string.connectivity_error)
    is com.lifebetter.simplegymapp.domain.Error.Server -> stringResource(R.string.server_error) + code
    is com.lifebetter.simplegymapp.domain.Error.Unknown -> stringResource(R.string.unknown_error) + message
    is com.lifebetter.simplegymapp.domain.Error.NumberException -> stringResource(R.string.number_exception)
}