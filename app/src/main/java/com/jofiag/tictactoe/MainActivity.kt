package com.jofiag.tictactoe

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var box1: Button
    private lateinit var box2: Button
    private lateinit var box3: Button
    private lateinit var box4: Button
    private lateinit var box5: Button
    private lateinit var box6: Button
    private lateinit var box7: Button
    private lateinit var box8: Button
    private lateinit var box9: Button

    private lateinit var score1TextView: TextView
    private lateinit var score2TextView: TextView
    private lateinit var countdownTimerText: TextView

    private val player1 = "X"
    private val player2 = "O"

    private var isPlayerOne = true
    private var scorePlayer1 = 0
    private var scorePlayer2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setLayoutReferences()
        handleAllBoxClick()
        restartAfter3minutes()
    }

    private fun setLayoutReferences() {
        box1 = findViewById(R.id.box_1)
        box2 = findViewById(R.id.box_2)
        box3 = findViewById(R.id.box_3)
        box4 = findViewById(R.id.box_4)
        box5 = findViewById(R.id.box_5)
        box6 = findViewById(R.id.box_6)
        box7 = findViewById(R.id.box_7)
        box8 = findViewById(R.id.box_8)
        box9 = findViewById(R.id.box_9)

        score1TextView = findViewById(R.id.player_1_score)
        score2TextView = findViewById(R.id.player_2_score)

        countdownTimerText = findViewById(R.id.count_down_text_view)
    }

    private fun handleABoxClick(box: Button) {
        box.setOnClickListener {
            if (!boxWasClicked(box)) {
                if (isPlayerOne) {
                    box.text = "X"
                    restartWhenAPlayerWin()
                }
                else {
                    box.text = "O"
                    restartWhenAPlayerWin()
                }

                isPlayerOne = !isPlayerOne
            }

        }
    }

    private fun restartWhenAPlayerWin() {
        if (winner() == player1) {
            scorePlayer1++
            showScore()
            restart()
        }

        if (winner() == player2) {
            scorePlayer2++
            showScore()
            restart()
        }
    }

    private fun restartAfter3minutes() {
        val timer = object: CountDownTimer(18_0000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                val ms = String.format("%02d:%02d",
                    (TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))),
                    (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))))

                countdownTimerText.text = ms
            }

            override fun onFinish() {restart()}
        }
        timer.start()
    }

    private fun restart() {
        box1.text = ""
        box2.text = ""
        box3.text = ""
        box4.text = ""
        box5.text = ""
        box6.text = ""
        box7.text = ""
        box8.text = ""
        box9.text = ""
    }

    private fun handleAllBoxClick() {
        handleABoxClick(box1)
        handleABoxClick(box2)
        handleABoxClick(box3)
        handleABoxClick(box4)
        handleABoxClick(box5)
        handleABoxClick(box6)
        handleABoxClick(box7)
        handleABoxClick(box8)
        handleABoxClick(box9)
    }

    private fun boxWasClicked(box: Button) = box.text != ""

    private fun winner(): String {
        var winner = ""

        if (checkWinner(player1))
            winner = player1

        if (checkWinner(player2))
            winner = player2

        return winner
    }

    private fun checkWinner(player: String): Boolean {
        var won = false

        if (box1.text == box2.text && box2.text == box3.text && box3.text == player)
            won = true

        if (box4.text == box5.text && box5.text == box6.text && box6.text == player)
            won = true

        if (box7.text == box8.text && box8.text == box9.text && box9.text == player)
            won = true

        if (box1.text == box5.text && box5.text == box9.text && box9.text == player)
            won = true

        if (box3.text == box5.text && box5.text == box7.text && box7.text == player)
            won = true

        if (box3.text == box6.text && box6.text == box9.text && box9.text == player)
            won = true

        if (box2.text == box5.text && box5.text == box8.text && box8.text == player)
            won = true

        if (box1.text == box4.text && box4.text == box7.text && box7.text == player)
            won = true

        return won
    }

    private fun showScore() {
        score1TextView.text = "Player 1: $scorePlayer1"
        score2TextView.text = "Player 2: $scorePlayer2"
    }
}