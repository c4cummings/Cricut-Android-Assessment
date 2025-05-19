package com.cricut.androidassessment.data

/**
 * Short answer question.
 * @param statement The statement of the question.
 * @param answer The answer to the question. Space separated keywords.
 */
data class ShortAnswerQuestion(
    override val statement: String,
    override val answer: String
) : Question<String, Double>() {
    override fun check(submission: String): Double {
        val answerKeywords = answer.split(" ")
        val possibleScore = answerKeywords.count().toDouble()
        var score = 0
        for (keyword in answerKeywords) {
            if (submission.contains(keyword)) {
                score += 1
            }
        }
        return (score / possibleScore)
    }
}
