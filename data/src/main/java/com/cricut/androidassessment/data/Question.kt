package com.cricut.androidassessment.data

/**
 * Base functionality of a question.
 *
 * @param T The type of the answer
 * @param R The type of the result, or feedback, when the submission is checked
 * @property statement The statement of the question
 * @property answer The correct answer to the question
 */
abstract class Question<T, out R> {
    abstract val statement: String
    abstract val answer: T

    /**
     * Checks if the answer is correct.
     *
     * @param submission The answer submitted to be verified against the correct answer
     * @return A metric of correctness
     */
    abstract fun check(submission: T): R
}
