package com.example.thousandaire

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GameOverActivity : AppCompatActivity() {
    override fun onBackPressed(): Unit {
    }

    private lateinit var quitGameButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        quitGameButton = findViewById(R.id.quit_game_button)

        quitGameButton.setOnClickListener {
            finishAffinity()
        }
    }
}
