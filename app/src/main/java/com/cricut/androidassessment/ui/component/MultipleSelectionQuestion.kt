package com.cricut.androidassessment.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.cricut.androidassessment.R
import com.cricut.androidassessment.ui.theme.AppTheme

private const val MAX_ROW_SIZE = 4

@Composable
fun MultipleSelectionQuestion(
    statement: String,
    options: Set<String>,
    onSelectedChange: (Set<String>) -> Unit,
    modifier: Modifier = Modifier,
    submission: Set<String> = emptySet(),
    maxRowSize: Int = MAX_ROW_SIZE
) {
    val selectedOptions = remember { mutableStateSetOf(*submission.toTypedArray()) }

    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_small)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = statement,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
        )

        val isCompact = options.size <= maxRowSize
        FlowRow(
            modifier = modifier,
            horizontalArrangement = if (isCompact) Arrangement.Center else Arrangement.Start,
            maxItemsInEachRow = if (isCompact) 1 else maxRowSize
        ) {
            options.forEach { option ->
                SelectButton(
                    selected = selectedOptions.contains(option),
                    onSelectedChange = { selected ->
                        if (selected) {
                            selectedOptions.add(option)
                        } else {
                            selectedOptions.remove(option)
                        }
                        onSelectedChange(selectedOptions)
                    }
                ) {
                    Text(text = option)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MultipleSelectionQuestionPreview(@PreviewParameter(MultiOptionsProvider::class) options: Set<String>) {
    AppTheme {
        MultipleSelectionQuestion(
            statement = "Which of these?",
            options = options,
            onSelectedChange = { },
            submission = setOf("One", "Three")
        )
    }
}

private class MultiOptionsProvider : PreviewParameterProvider<Set<String>> {
    override val values = sequenceOf(
        setOf("One", "Two", "Three"),
        setOf("One", "Two", "Three", "Four"),
        setOf("One", "Two", "Three", "Four", "Five"),
        setOf("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"),
    )
}
