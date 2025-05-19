package com.cricut.androidassessment.data

/**
 * Multiple-selection, multiple-choice question.
 */
data class MultipleSelectionQuestion(
    override val statement: String,
    val options: Set<String>,
    override val answer: Set<String>
) : Question<Set<String>, Double>() {
    override fun check(submission: Set<String>): Double {
        if (submission.isEmpty()) {
            return 0.0
        }

        return submission.count {
            answer.contains(it)
        }.toDouble() / answer.count()
    }
}
