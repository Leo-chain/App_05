package com.example.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class SecondActivity : AppCompatActivity() {
    val TAG : String = MainActivity::class.java.simpleName
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        handler = Handler(Looper.getMainLooper())

        val btnBacktoFirst = findViewById<Button>(R.id.btnBacktoFirst)
        val textView = findViewById<TextView>(R.id.result_textView)
        val result_button = findViewById<Button>(R.id.reset_button)
        val editText = findViewById<EditText>(R.id.editText)
        val guess_button = findViewById<Button>(R.id.guess_button)
        var validate_num:Int
        var start:Int = 1
        var end:Int = 100

        var secret : Int = Random().nextInt(100)+1

        guess_button.setOnClickListener {
            Toast.makeText(this, editText.text, Toast.LENGTH_LONG).show()
            textView.text=editText.text
            validate_num=editText.text.toString().toInt()-secret
            var ans_str:String="你猜對了喔"

            if(validate_num>0){
                end=editText.text.toString().toInt()
                var strend=end.toInt().toString()
                var strstart=start.toInt().toString()
                ans_str = strstart+" ~ "+strend
            }else if(validate_num<0){
                start=editText.text.toString().toInt()
                var strend=end.toInt().toString()
                var strstart=start.toInt().toString()
                ans_str = strstart+" ~ "+strend
            }
            textView.text=ans_str
            if(textView.text=="你猜對了喔"){
                handler.postDelayed(
                    {Toast.makeText(this, "6秒後重置結果!", Toast.LENGTH_SHORT).show()}, 6000
                )
                secret=Random().nextInt(10)+1
                start=1
                end=100
                textView.text="我們再猜一次"
            }
        }

        result_button.setOnClickListener {
            secret=Random().nextInt(10)+1
            start=1
            end=100
            textView.text="我們再猜一次"
        }

        btnBacktoFirst.setOnClickListener{
            finish()
            /*第二種寫法
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)*/
        }
    }

    override fun onDestroy(){
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
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