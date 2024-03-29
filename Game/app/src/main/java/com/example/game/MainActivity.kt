package com.example.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Guess_Number = findViewById<Button>(R.id.Guess_Number)
        val FingerGuessing = findViewById<Button>(R.id.FingerGuessing)

        Guess_Number.setOnClickListener{
            var seconIntent = Intent(this, SecondActivity::class.java)
            startActivity(seconIntent)
        }
        FingerGuessing.setOnClickListener{
            var thirdIntent = Intent(this, ThirdActivity::class.java)
            startActivity(thirdIntent)
        }
    }
}