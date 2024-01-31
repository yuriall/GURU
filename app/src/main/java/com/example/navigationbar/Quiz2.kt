package com.example.navigationbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Quiz2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz2)

        val buttonNavigate:Button = findViewById(R.id.button2)
        val buttonNavigate1:Button = findViewById(R.id.button3)
        val buttonNavigate2:Button = findViewById(R.id.button4)
        val buttonNavigate3:Button = findViewById(R.id.button5)
        val buttonNavigate4:Button = findViewById(R.id.button6)
        val buttonNavigate5:Button = findViewById(R.id.button7)

        buttonNavigate.setOnClickListener {
            // back
            val intent = Intent(this@Quiz2, Quiz::class.java)
            startActivity(intent)
        }

        buttonNavigate1.setOnClickListener {
            // 스테이지 1
            val intent = Intent(this@Quiz2, Quiz3::class.java)
            startActivity(intent)
        }

        buttonNavigate2.setOnClickListener {
            // 스테이지 2
            val intent = Intent(this@Quiz2, Quiz4::class.java)
            startActivity(intent)
        }

        buttonNavigate3.setOnClickListener {
            // 스테이지 3
            val intent = Intent(this@Quiz2, Quiz5::class.java)
            startActivity(intent)
        }

        buttonNavigate4.setOnClickListener {
            // 스테이지 4
            val intent = Intent(this@Quiz2, Quiz6::class.java)
            startActivity(intent)
        }

        buttonNavigate5.setOnClickListener {
            // 스테이지 5
            val intent = Intent(this@Quiz2, Quiz7::class.java)
            startActivity(intent)
        }

    }
}