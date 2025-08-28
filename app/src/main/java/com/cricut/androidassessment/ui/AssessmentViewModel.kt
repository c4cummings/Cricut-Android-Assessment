package com.cricut.androidassessment.ui

import androidx.lifecycle.ViewModel
import com.cricut.androidassessment.data.BooleanQuestion
import com.cricut.androidassessment.data.MultipleSelectionQuestion
import com.cricut.androidassessment.data.Question
import com.cricut.androidassessment.data.ShortAnswerQuestion
import com.cricut.androidassessment.data.SingleSelectionQuestion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AssessmentViewModel
@Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AssessmentUiState())
    val uiState: StateFlow<AssessmentUiState> = _uiState.asStateFlow()

    private lateinit var _questions: List<Question<*, *>>

    init {
        loadQuestions()
    }

    fun loadQuestions() {
        _questions = listOf(
            BooleanQuestion(
                statement = "Is this a question?",
                answer = true,
                affirmative = "Yes",
                negative = "No"
            ),
            MultipleSelectionQuestion(
                statement = "Which of these?",
                options = setOf("One", "Two", "Three"),
                answer = setOf("One", "Three")
            ),
            ShortAnswerQuestion(
                statement = "What is the meaning of life?",
                answer = "42"
            ),
            SingleSelectionQuestion(
                statement = "Which one of these?",
                options = setOf("One", "Two", "Three"),
                answer = "Two"
            )
        )

        _uiState.value = AssessmentUiState(
            currentQuestion = _questions[0],
            questionCount = _questions.size,
            currentPosition = 0
        )
    }

    fun <T> checkAnswer(answer: T) {

    }


}
