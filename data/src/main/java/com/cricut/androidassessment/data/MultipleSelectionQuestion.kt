package com.cricut.androidassessment.data

/**
 * Multiple-selection, multiple-choice question.
 *
 * TODO: Provide ability to specify grading criteria, i.e. points for each correct answer, or all or none.
 */
data class MultipleSelectionQuestion(
    override val statement: String,
    val options: Set<String>,
    override val answer: Set<String>,
    override var submission: Set<String>? = null
) : Question<Set<String>, Double>() {
    override fun check(): Double {
        return if (submission.isNullOrEmpty()) {
            0.0
        } else {
            submission?.count {
                answer.contains(it)
            }?.toDouble()?.div(answer.count()) ?: 0.0
        }
    }
}
