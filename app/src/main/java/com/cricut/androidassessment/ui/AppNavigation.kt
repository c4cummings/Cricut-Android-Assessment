package com.cricut.androidassessment.ui

import kotlinx.serialization.Serializable

object AppDestinations {
    @Serializable
    object Welcome

    @Serializable
    object QuizStart

    @Serializable
    data class QuizQuestion(val id: Int)

    @Serializable
    object QuizEnd

    @Serializable
    object QuizResult
}
