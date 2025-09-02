package com.cricut.androidassessment.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cricut.androidassessment.R
import com.cricut.androidassessment.data.BooleanQuestion
import com.cricut.androidassessment.data.MultipleSelectionQuestion
import com.cricut.androidassessment.data.ShortAnswerQuestion
import com.cricut.androidassessment.data.SingleSelectionQuestion
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
        modifier = modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.elevation_medium))
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = modifier
            ) { page ->
                when (val question = viewModel.getQuestion(page)) {
                    is BooleanQuestion -> BooleanQuestion(
                        statement = question.statement,
                        affirmative = question.affirmative,
                        negative = question.negative,
                        onSelectedChange = { submission ->
                            viewModel.saveSubmission(page, submission)
                        }
                    )

                    is ShortAnswerQuestion -> com.cricut.androidassessment.ui.component.ShortAnswerQuestion(
                        statement = question.statement,
                        onAnswerChange = { submission ->
                            viewModel.saveSubmission(page, submission)
                        }
                    )

                    is SingleSelectionQuestion -> com.cricut.androidassessment.ui.component.SingleSelectionQuestion(
                        statement = question.statement,
                        options = question.options,
                        onSelectedChange = { submission ->
                            viewModel.saveSubmission(page, submission)
                        }
                    )

                    is MultipleSelectionQuestion -> com.cricut.androidassessment.ui.component.MultipleSelectionQuestion(
                        statement = question.statement,
                        options = question.options,
                        onSelectedChange = { submission ->
                            viewModel.saveSubmission(page, submission)
                        }
                    )

                    else -> {
                        Text(text = "Question $page")
                    }
                }
            }
        }

        val coroutineScope = rememberCoroutineScope()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                },
                enabled = pagerState.currentPage > 0
            ) {
                Text(text = stringResource(R.string.btn_previous))
            }

            Text(
                text = stringResource(
                    R.string.lbl_question_index_by_count,
                    pagerState.currentPage + 1,
                    uiState.questionCount
                )
            )

            Button(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
                enabled = pagerState.currentPage < uiState.questionCount - 1
            ) {
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
