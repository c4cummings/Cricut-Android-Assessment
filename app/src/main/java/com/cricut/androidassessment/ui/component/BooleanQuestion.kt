package com.cricut.androidassessment.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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

@Composable
fun BooleanQuestion(
    statement: String,
    affirmative: String,
    negative: String,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    submission: Boolean? = null,
) {
    val selectedOption = remember { mutableStateOf(submission) }

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            SelectButton(
                selected = selectedOption.value == true, //submission?.or(false) ?: false,
                onSelectedChange = {
                    selectedOption.value = true
                    onSelectedChange(true)
                }) {
                Text(text = affirmative)
            }

            //Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))

            SelectButton(
                selected = selectedOption.value == false, //submission?.not() ?: false,
                onSelectedChange = {
                    selectedOption.value = false
                    onSelectedChange(false)
                }) {
                Text(text = negative)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BooleanQuestionPreview(@PreviewParameter(BooleanSubmissionProvider::class) submission: Boolean) {
    AppTheme {
        BooleanQuestion(
            statement = "Is this a preview?",
            affirmative = "Yes",
            negative = "No",
            onSelectedChange = { true },
            submission = submission
        )
    }
}

private class BooleanSubmissionProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}
