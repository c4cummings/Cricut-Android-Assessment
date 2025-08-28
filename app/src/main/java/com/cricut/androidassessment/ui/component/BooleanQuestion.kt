package com.cricut.androidassessment.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.cricut.androidassessment.R
import com.cricut.androidassessment.ui.theme.AppTheme

@Composable
fun BooleanQuestion(
    statement: String,
    affirmative: String,
    negative: String,
    validate: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                fontStyle = FontStyle.Normal
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    validate(true)
                }) {
                    Text(text = affirmative)
                }

                //Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))

                Button(onClick = {
                    validate(false)
                }) {
                    Text(text = negative)
                }
            }
        }
    }
}

@Preview
@Composable
fun BooleanQuestionPreview() {
    AppTheme {
        BooleanQuestion(
            statement = "Is this a preview?",
            affirmative = "Yes",
            negative = "No",
            validate = { true }
        )
    }
}
