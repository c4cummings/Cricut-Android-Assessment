package com.cricut.androidassessment.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BooleanQuestionTests {
    @ParameterizedTest
    @CsvSource(
        "true, true, true",
        "true, false, false",
        "false, true, false",
        "false, false, true",
    )
    fun `Given an answer and a submission, expect result`(answer: Boolean, submission: Boolean, expected: Boolean) {
        val question = BooleanQuestion(
            "What is...",
            answer
        )

        Assertions.assertEquals(expected, question.check(submission))
    }
}
