package com.example.thousandaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.thousandaire.models.Game

class MainActivity : AppCompatActivity() {
    private lateinit var game: Game

    private lateinit var questionTextView: TextView
    private lateinit var answerButton1: Button
    private lateinit var answerButton2: Button
    private lateinit var answerButton3: Button
    private lateinit var answerButton4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.question_text_view)
        answerButton1 = findViewById(R.id.answer_button_1)
        answerButton2 = findViewById(R.id.answer_button_2)
        answerButton3 = findViewById(R.id.answer_button_3)
        answerButton4 = findViewById(R.id.answer_button_4)
    }

    fun initializeGame(game: Game) {
        lateinit var choices: MutableList<Int>

        //Question 1
        choices = mutableListOf<Int>(R.string.mickey_answer1, R.string.mickey_answer2,
            R.string.mickey_answer3, R.string.mickey_answer4)
        game.addQuestion(R.string.mickey_question, R.string.mickey_answer1, choices, 100)

        //Question 2
        choices = mutableListOf<Int>(R.string.solar_answer1, R.string.solar_answer2,
            R.string.solar_answer3, R.string.solar_answer4)
        game.addQuestion(R.string.solar_question, R.string.solar_answer3, choices, 200)

        //Question 3
        choices = mutableListOf<Int>(R.string.gilligan_answer1, R.string.gilligan_answer2,
            R.string.gilligan_answer3, R.string.gilligan_answer4)
        game.addQuestion(R.string.gilligan_question, R.string.gilligan_answer3, choices, 300)

        //Question 4
        choices = mutableListOf<Int>(R.string.periodic_answer1, R.string.periodic_answer2,
            R.string.periodic_answer3, R.string.periodic_answer4)
        game.addQuestion(R.string.periodic_question, R.string.periodic_answer4, choices, 400)

        //Question 5
        choices = mutableListOf<Int>(R.string.valletta_answer1, R.string.valletta_answer2,
            R.string.valletta_answer3, R.string.valletta_answer4)
        game.addQuestion(R.string.valletta_question, R.string.valletta_answer4, choices, 500)

        //Question 6
        choices = mutableListOf<Int>(R.string.sun_answer1, R.string.sun_answer2,
            R.string.sun_answer3, R.string.sun_answer4)
        game.addQuestion(R.string.sun_question, R.string.sun_answer3, choices, 1000)
    }
}
