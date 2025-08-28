package com.cricut.androidassessment.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.cricut.androidassessment.R
import com.cricut.androidassessment.ui.theme.AppTheme

@Composable
fun ShortAnswerQuestion(
    statement: String,
    onAnswerChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    submission: String? = null,
) {
    val answer = remember { mutableStateOf(submission) }

    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small)),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_small),
            alignment = Alignment.CenterVertically
        ),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = statement,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
        )

        OutlinedTextField(
            value = answer.value ?: "",
            onValueChange = {
                answer.value = it
                onAnswerChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(R.string.lbl_short_answer)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShortAnswerQuestionPreview() {
    AppTheme {
        ShortAnswerQuestion(
            statement = "What is the meaning of life?",
            onAnswerChange = { },
            submission = "42"
        )
    }
}
