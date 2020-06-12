package com.example.thousandaire

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.thousandaire.ProceedActivity.Companion.newIntent
import com.example.thousandaire.models.Game

private const val EXTRA_CURRENT_AMOUNT = "com.example.thousandaire.current_amount"
private const val EXTRA_NEXT_AMOUNT = "com.example.thousandaire.next_amount"
const val EXTRA_GO_ON = "com.example.thousandaire.go_on"

class ProceedActivity : AppCompatActivity() {
    override fun onBackPressed(): Unit {
    }

    private val REQUEST_CODE_PLAY_OVER = 0
    private lateinit var proceedTextView: TextView
    private lateinit var goOnButton: Button
    private lateinit var quitGameButton: Button
    private var currentAmount = 0
    private var nextAmount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proceed)

        currentAmount = intent.getIntExtra(EXTRA_CURRENT_AMOUNT, 0)
        nextAmount = intent.getIntExtra(EXTRA_NEXT_AMOUNT, 0)

        goOnButton = findViewById(R.id.go_on_button)
        quitGameButton = findViewById(R.id.quit_game_button_proceed)
        proceedTextView = findViewById(R.id.proceed_text_view)

        proceedTextView.setText("Correct! You have earned $$currentAmount. " +
                "Would you care to try for $$nextAmount?")

        goOnButton.setOnClickListener {
            setGoOnResult(true)
            finish()
        }

        quitGameButton.setOnClickListener {
            val intent = ScoreActivity.newIntent(this@ProceedActivity, currentAmount)
            startActivity(intent)
            setGoOnResult(false)
            finish()
        }
    }

    companion object {
        fun newIntent(packageContext: Context, currentQuestionAmount: Int, nextQuestionAmount: Int): Intent {
            return Intent(packageContext, ProceedActivity::class.java).apply {
                putExtra(EXTRA_CURRENT_AMOUNT, currentQuestionAmount)
                putExtra(EXTRA_NEXT_AMOUNT, nextQuestionAmount)
            }
        }
    }

    private fun setGoOnResult(didUserGoOn: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_GO_ON, didUserGoOn)
        }
        setResult(Activity.RESULT_OK, data)
    }
}
