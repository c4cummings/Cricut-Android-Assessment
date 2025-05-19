package com.cricut.androidassessment.data

/**
 * Single-selection, multiple-choice question.
 */
data class SingleSelectionQuestion(
    override val statement: String,
    val options: Set<String>,
    override val answer: String
) : Question<String, Boolean>() {
    override fun check(submission: String): Boolean {
        if (submission.isBlank()) {
            return false
        }

        return submission.equals(answer, ignoreCase = true)
    }
}
