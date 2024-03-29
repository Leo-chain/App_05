package com.example.game

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ImageView
import java.util.*

class ThirdActivity : AppCompatActivity() {
    private lateinit var txtResult: TextView
    private lateinit var btnScissors: ImageButton
    private lateinit var btnRock: ImageButton
    private lateinit var btnPaper: ImageButton
    private lateinit var imageView: ImageView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val btnBacktoFirst = findViewById<Button>(R.id.btnBacktoFirst2)
        txtResult = findViewById(R.id.txtResult)
        btnScissors = findViewById(R.id.imbtnScissor)
        btnRock = findViewById(R.id.imbtnRock)
        btnPaper = findViewById(R.id.imbtnPaper)
        imageView = findViewById(R.id.imageView)

        btnScissors.setOnClickListener {
            playGame(Choice.SCISSORS)
        }
        btnRock.setOnClickListener {
            playGame(Choice.ROCK)
        }
        btnPaper.setOnClickListener {
            playGame(Choice.PAPER)
        }

        btnBacktoFirst.setOnClickListener{
            finish()
            /*第二種寫法
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)*/
        }
    }
    enum class Choice{
        SCISSORS, ROCK, PAPER
    }
    private fun playGame(playerChoice: Choice){
        val choices = Choice.values()
        val computerChoice = choices[Random().nextInt(choices.size)]

        when{
            computerChoice == Choice.SCISSORS -> {
                imageView.setImageResource(R.drawable.scissor)
            }
            computerChoice == Choice.ROCK -> {
                imageView.setImageResource(R.drawable.rock)
            }
            computerChoice == Choice.PAPER -> {
                imageView.setImageResource(R.drawable.paper)
            }
        }

        when{
            playerChoice == computerChoice -> {
                txtResult.setText(R.string.draw)
            }
            (playerChoice == Choice.SCISSORS && computerChoice == Choice.PAPER) ||
                    (playerChoice == Choice.ROCK && computerChoice == Choice.SCISSORS) ||
                    (playerChoice == Choice.PAPER && computerChoice ==Choice.ROCK)  -> {
                txtResult.setText(R.string.win)
            }
            else -> {
                txtResult.setText(R.string.lose)
            }
        }
    }
    private fun getChoiceString(choice: Choice): Int{
        return when(choice){
            Choice.SCISSORS -> R.string.scissor
            Choice.ROCK -> R.string.rock
            Choice.PAPER -> R.string.paper
        }
    }

    var lastTime: Long = 0
    override fun finish() {
        val currentTime = System.currentTimeMillis()
        if(currentTime - lastTime > 3 * 1000){
            lastTime = currentTime
            Toast.makeText(this, "再按一下離開", Toast.LENGTH_SHORT).show()
        }else{
            super.finish()
        }
    }
}