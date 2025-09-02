package com.cricut.androidassessment.data

/**
 * Base functionality of a question.
 *
 * @param T The type of the answer
 * @param R The type of the result, or feedback, when the submission is checked
 * @property statement The statement of the question
 * @property answer The correct answer to the question
 * @property submission The answer submitted to be verified against the correct answer
 */
abstract class Question<T, out R> {
    abstract val statement: String
    abstract val answer: T
    abstract var submission: T?

    /**
     * Checks if the [submission] is correct.
     *
     * @return A metric of correctness
     */
    abstract fun check(): R
}
