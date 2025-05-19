package com.cricut.androidassessment.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ShortAnswerQuestionTests {
    @ParameterizedTest
    @CsvSource(
        "a b c d, a b c d, 1.0",
        "a b c d, a b c, 0.75",
        "a b c d, a b, 0.50",
        "a b c d, a, 0.25",
        "a b c d, e, 0.0",
    )
    fun `Given an answer and a submission, expect result`(answer: String, submission: String, expected: Double) {
        val question = ShortAnswerQuestion(
            "What is...",
            answer
        )

        Assertions.assertEquals(expected, question.check(submission))
    }
}
