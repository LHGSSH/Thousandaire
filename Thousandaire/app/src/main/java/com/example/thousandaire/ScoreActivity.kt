package com.example.thousandaire

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

private const val EXTRA_CURRENT_AMOUNT = "com.example.thousandaire.current_amount"

class ScoreActivity : AppCompatActivity() {

    private var currentAmount = 0
    private lateinit var scoreTextView: TextView
    private lateinit var playOverButton: Button
    private lateinit var quitGameButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        currentAmount = intent.getIntExtra(EXTRA_CURRENT_AMOUNT, 0)

        playOverButton = findViewById(R.id.play_over_button_score)
        quitGameButton = findViewById(R.id.quit_game_button_score)
        scoreTextView = findViewById(R.id.score_text_view)

        scoreTextView.setText("Congratulations!\n You earned $$currentAmount.")

        playOverButton.setOnClickListener {
            setGoOnResult(false)
            finish()
        }
        quitGameButton.setOnClickListener {
            finishAffinity()
        }
    }

    companion object {
        fun newIntent(packageContext: Context, currentQuestionAmount: Int): Intent {
            return Intent(packageContext, ScoreActivity::class.java).apply {
                putExtra(EXTRA_CURRENT_AMOUNT, currentQuestionAmount)
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
