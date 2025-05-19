package com.cricut.androidassessment.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SingleSelectionQuestionTests {
    @ParameterizedTest
    @CsvSource(
        "a b c d, c, c, true",
        "a b c d, c, a, false",
    )
    fun `Given an answer and a submission, expect result`(options: String, answer: String, submission: String, expected: Boolean) {
        val question = SingleSelectionQuestion(
            "What is...",
            options.split(" ").toSet(),
            answer
        )

        Assertions.assertEquals(expected, question.check(submission))
    }
}
