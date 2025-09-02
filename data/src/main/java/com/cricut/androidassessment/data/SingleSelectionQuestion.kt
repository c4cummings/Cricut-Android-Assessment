package com.cricut.androidassessment.data

/**
 * Single-selection, multiple-choice question.
 */
data class SingleSelectionQuestion(
    override val statement: String,
    val options: Set<String>,
    override val answer: String,
    override var submission: String? = null
) : Question<String, Boolean>() {
    override fun check(): Boolean {
        return if (submission.isNullOrBlank()) {
            false
        } else {
            submission.equals(answer, ignoreCase = true)
        }
    }
}
