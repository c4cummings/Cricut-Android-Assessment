package com.cricut.androidassessment.ui

import android.util.Log
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
import kotlinx.coroutines.flow.update
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
        )
    }

    fun getQuestion(index: Int): Question<*, *> {
        return _questions[index]
    }

    fun nextQuestion() {
        val nextQuestionIndex = _uiState.value.currentPosition.inc()
        if (nextQuestionIndex < _questions.size) {
            _uiState.update { currentState ->
                currentState.copy(
                    currentQuestion = _questions[nextQuestionIndex],
                    currentPosition = nextQuestionIndex
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isQuizOver = true
                )
            }
        }
    }

    fun previousQuestion() {
        val previousQuestionIndex = _uiState.value.currentPosition.dec()
        if (previousQuestionIndex >= 0) {
            _uiState.update { currentState ->
                currentState.copy(
                    currentQuestion = _questions[previousQuestionIndex],
                    currentPosition = previousQuestionIndex
                )
            }
        }
    }

    fun <T> saveSubmission(index: Int, submission: T) {
        val question = _questions[index]
        when (question) {
            is BooleanQuestion -> {
                if (submission is Boolean) {
                    (question as Question<Boolean, Any>).submission = submission
                } else {
                    Log.e("AssessmentViewModel", "Incorrect submission type for BooleanQuestion. Expected Boolean, got ${submission!!::class.simpleName}")
                }
            }
            is MultipleSelectionQuestion -> {
                if (submission is Set<*>) {
                    if (submission.all { it is String }) {
                        (question as Question<Set<String>, Any>).submission = submission as Set<String>
                    } else {
                        Log.e("AssessmentViewModel", "Incorrect submission type for MultipleSelectionQuestion. Expected Set<String>, but elements are not all Strings.")
                    }
                } else {
                    Log.e("AssessmentViewModel", "Incorrect submission type for MultipleSelectionQuestion. Expected Set<String>, got ${submission!!::class.simpleName}")
                }
            }
            is ShortAnswerQuestion -> {
                if (submission is String) {
                    (question as Question<String, Any>).submission = submission
                } else {
                    Log.e("AssessmentViewModel", "Incorrect submission type for ShortAnswerQuestion. Expected String, got ${submission!!::class.simpleName}")
                }
            }
            is SingleSelectionQuestion -> {
                if (submission is String) {
                    (question as Question<String, Any>).submission = submission
                } else {
                    Log.e("AssessmentViewModel", "Incorrect submission type for SingleSelectionQuestion. Expected String, got ${submission!!::class.simpleName}")
                }
            }
            else -> {
                Log.e("AssessmentViewModel", "Unknown question type at index $index")
            }
        }
    }
}
