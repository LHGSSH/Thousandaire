package com.example.thousandaire.models

class Game() {
    var questions: MutableList<Question> = mutableListOf()
        private set
    private var currentQuestionIndex: Int = 0
    var currentQuestionAnswer: Int = 0
        private set
    var currentQuestionText: Int = 0
        private set
    var currentQuestionAmount: Int = 0
        private set
    var nextQuestionAmount: Int = 0
        private set
    var currentQuestionChoices: MutableList<Int> = mutableListOf()
        private set
    var didUserGoOn: Boolean = false

    fun addQuestion(questionText: Int, questionAnswer: Int, choices: MutableList<Int>, amount: Int) {
        var newQuestion = Question(questionText, questionAnswer, choices, amount)
        questions.add(newQuestion)

        //If questions was empty beforehand
        if (questions.size == 1) {
            currentQuestionAnswer = questions[currentQuestionIndex].answerId
            currentQuestionText = questions[currentQuestionIndex].questionTextId
            currentQuestionChoices = questions[currentQuestionIndex].choiceIds
            currentQuestionAmount = questions[currentQuestionIndex].amount
        }
        //If it's the final question, set the next amount to 0
        nextQuestionAmount =
            if (isFinalQuestion()) {
                0
            }
            else {
                questions[currentQuestionIndex + 1].amount
            }
    }
    
    fun isFinalQuestion(): Boolean {
        return currentQuestionIndex == questions.lastIndex
    }

    fun proceedToNextQuestion() {
        currentQuestionIndex++

        currentQuestionAnswer = questions[currentQuestionIndex].answerId
        currentQuestionText = questions[currentQuestionIndex].questionTextId
        currentQuestionAmount = questions[currentQuestionIndex].amount
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