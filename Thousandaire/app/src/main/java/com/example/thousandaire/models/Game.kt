package com.example.thousandaire.models

class Game() {
    var questions: MutableList<Question> = mutableListOf()
        private set
    private var currentQuestionIndex: Int = 0
    var currentQuestionAnswer: Int = 0
        private set
    var currentQuestionText: Int = 0
        private set
    var nextQuestionAmount: Int = 0
        private set
    var currentQuestionChoices: MutableList<Int> = mutableListOf()
        private set

    fun addQuestion(questionText: Int, questionAnswer: Int, choices: MutableList<Int>, amount: Int) {
        var newQuestion = Question(questionText, questionAnswer, choices, amount)
        questions.add(newQuestion)

        //If questions was empty beforehand
        if (questions.size == 1) {
            currentQuestionAnswer = questions[currentQuestionIndex].answerId
            currentQuestionText = questions[currentQuestionIndex].questionTextId
            currentQuestionChoices = questions[currentQuestionIndex].choiceIds
        }
        //If there is another question, set the next question amount
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