package com.example.thousandaire.models

class Game() {
    var questions: MutableList<Question> = mutableListOf()
        private set
    private var currentQuestionIndex: Int = 0
        private set
    var currentQuestionAnswer: Int = questions[currentQuestionIndex].answerId
        private set
    var currentQuestionText: Int = questions[currentQuestionIndex].questionTextId
        private set
    var nextQuestionAmount: Int = 0
        private set
    var currentQuestionChoices: MutableList<Int> = questions[currentQuestionIndex].choiceIds
        private set

    fun addQuestion(questionText: Int, questionAnswer: Int, choices: MutableList<Int>, amount: Int) {
        var newQuestion = Question(questionText, questionAnswer, choices, amount)
        questions.add(newQuestion)

        //We must update the next question amount just in case there was only one question beforehand
        nextQuestionAmount =
            if (!isFinalQuestion()) {
                questions[currentQuestionIndex + 1].amount
            }
            else {
                0
            }
    }
    
    fun isFinalQuestion(): Boolean {
        return currentQuestionIndex == (questions.size - 1)
    }

    fun proceedToNextQuestion() {
        currentQuestionIndex++

        currentQuestionAnswer = questions[currentQuestionIndex].answerId
        currentQuestionText = questions[currentQuestionIndex].questionTextId
        nextQuestionAmount =
            if (!isFinalQuestion()) {
                questions[currentQuestionIndex + 1].amount
            }
            else {
                0
            }
        currentQuestionChoices = questions[currentQuestionIndex].choiceIds
    }
}