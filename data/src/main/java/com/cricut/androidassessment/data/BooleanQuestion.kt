package com.cricut.androidassessment.data

/**
 * True/false, yes/no, one/other question.
 */
data class BooleanQuestion(
    override val statement: String,
    override val answer: Boolean,
    val affirmative: String,
    val negative: String,
    override var submission: Boolean? = null
) : Question<Boolean, Boolean>() {
    override fun check(): Boolean {
        return submission == answer
    }
}
