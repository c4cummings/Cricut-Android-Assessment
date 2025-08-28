package com.cricut.androidassessment.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cricut.androidassessment.R
import com.cricut.androidassessment.data.BooleanQuestion
import com.cricut.androidassessment.data.MultipleSelectionQuestion
import com.cricut.androidassessment.ui.AssessmentViewModel
import com.cricut.androidassessment.ui.component.BooleanQuestion
import com.cricut.androidassessment.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun AssessmentScreen(
    modifier: Modifier = Modifier,
    viewModel: AssessmentViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { uiState.questionCount })

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        HorizontalPager(
            state = pagerState,
            modifier= Modifier.fillMaxWidth()) { page ->
            when(val question = uiState.currentQuestion) {
                is BooleanQuestion -> BooleanQuestion(
                    statement = question.statement,
                    affirmative = question.affirmative,
                    negative = question.negative,
                    validate = { submission ->
                        viewModel.checkAnswer(submission)
                    }
                )
                //is MultipleSelectionQuestion -> MultipleSelectionQuestion(question = question) { submission ->
                null -> {
                    Text(text = "Question $page")
                }
                else -> {
                    Text(text = "Question $page")
                }
            }
        }

        val coroutineScope = rememberCoroutineScope()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            }) {
                Text(text = stringResource(R.string.btn_previous))
            }

            Button(onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }) {
                Text(text = stringResource(R.string.btn_next))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAssessmentScreen() {
    AppTheme {
        AssessmentScreen()
    }
}
