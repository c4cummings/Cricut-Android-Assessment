package com.cricut.androidassessment.ui

import com.cricut.androidassessment.data.Question

data class AssessmentUiState(
    val currentQuestion: Question<*, *>? = null,
    val questionCount: Int = 0,
    val currentPosition: Int = 0,
    val isQuizOver: Boolean = false
)
