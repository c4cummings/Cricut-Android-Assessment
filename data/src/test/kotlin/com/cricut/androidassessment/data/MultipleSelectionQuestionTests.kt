package com.cricut.androidassessment.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MultipleSelectionQuestionTests {
    @ParameterizedTest
    @CsvSource(
        "a b c d e, a b c d, a b c d, 1.0",
        "a b c d e, a b c d, a b c, 0.75",
        "a b c d e, a b c d, a b, 0.50",
        "a b c d e, a b c d, a, 0.25",
        "a b c d e, a b c d, e, 0.0",
    )
    fun `Given an answer and a submission, expect result`(options: String, answer: String, submission: String, expected: Double) {
        val question = MultipleSelectionQuestion(
            "What is...",
            options.split(" ").toSet(),
            answer.split(" ").toSet()
        )

        Assertions.assertEquals(expected, question.check(submission.split(" ").toSet()))
    }
}
