package com.cricut.androidassessment.data

/**
 * True/false, yes/no, one/other question.
 */
data class BooleanQuestion(
    override val statement: String,
    override val answer: Boolean
) : Question<Boolean, Boolean>() {
    override fun check(submission: Boolean): Boolean {
        return submission == answer
    }
}
