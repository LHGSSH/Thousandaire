package com.example.thousandaire

import android.app.Activity
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.thousandaire.models.Game

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_GO_ON = 0
    private val REQUEST_CODE_PLAY_OVER = 0

    private lateinit var game: Game

    private lateinit var questionTextView: TextView
    private lateinit var answerButton1: Button
    private lateinit var answerButton2: Button
    private lateinit var answerButton3: Button
    private lateinit var answerButton4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = Game()
        initializeGame(game)

        questionTextView = findViewById(R.id.question_text_view)
        answerButton1 = findViewById(R.id.answer_button_1)
        answerButton2 = findViewById(R.id.answer_button_2)
        answerButton3 = findViewById(R.id.answer_button_3)
        answerButton4 = findViewById(R.id.answer_button_4)

        setText()

        answerButton1.setOnClickListener {
            if (isAnswerCorrect(game, answerButton1)) {
                if (game.isFinalQuestion()) {
                    score()
                }
                else {
                    proceed()
                }
            }
            else {
                gameOver()
            }
        }

        answerButton2.setOnClickListener {
            if (isAnswerCorrect(game, answerButton2)) {
                if (game.isFinalQuestion()) {
                    score()
                }
                else {
                    proceed()
                }
            }
            else {
                gameOver()
            }
        }

        answerButton3.setOnClickListener {
            if (isAnswerCorrect(game, answerButton3)) {
                if (game.isFinalQuestion()) {
                    score()
                }
                else {
                    proceed()
                }
            }
            else {
                gameOver()
            }
        }

        answerButton4.setOnClickListener {
            if (isAnswerCorrect(game, answerButton4)) {
                if (game.isFinalQuestion()) {
                    score()
                }
                else {
                    proceed()
                }
            }
            else {
                gameOver()
            }
        }
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

    fun setText() {
        questionTextView.setText(game.currentQuestionText)
        answerButton1.setText(game.currentQuestionChoices[0])
        answerButton2.setText(game.currentQuestionChoices[1])
        answerButton3.setText(game.currentQuestionChoices[2])
        answerButton4.setText(game.currentQuestionChoices[3])
    }

    fun isAnswerCorrect(game: Game, answerButton: Button): Boolean {
        var answer = getString(game.currentQuestionAnswer)

        if (answerButton.text.equals(answer)) {
            return true
        }
        return false
    }

    fun gameOver() {
        val intent = Intent(this, GameOverActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            return
        }

        if (requestCode == REQUEST_CODE_GO_ON) {
            game.didUserGoOn =
                data?.getBooleanExtra(EXTRA_GO_ON, false) ?: false
            checkIfUserGoesOn()
        }
    }

    fun proceed() {
        val intent = ProceedActivity.newIntent(this@MainActivity,
            game.currentQuestionAmount, game.nextQuestionAmount)
        startActivityForResult(intent, REQUEST_CODE_GO_ON)
    }

    fun score() {
        val intent = ScoreActivity.newIntent(this@MainActivity,
            game.currentQuestionAmount)
        startActivityForResult(intent, REQUEST_CODE_GO_ON)
    }

    fun checkIfUserGoesOn() {
        if (!game.didUserGoOn) {
            //reset game
            game = Game()
            initializeGame(game)
            setText()
        }
        else {
            game.proceedToNextQuestion()
            setText()
            game.didUserGoOn = false
        }
    }
}
