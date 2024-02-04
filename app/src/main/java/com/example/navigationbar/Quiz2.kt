package com.example.navigationbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
//퀴즈 stage 선택 화면
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
            // back 버튼, 누르면 이전 화면인 퀴즈 시작 화면으로 이동
            val intent = Intent(this@Quiz2, Quiz::class.java)
            startActivity(intent)
        }

        buttonNavigate1.setOnClickListener {
            // stage1 버튼, 누르면 stage1 시작 화면으로 이동
            val intent = Intent(this@Quiz2, Quiz3::class.java)
            startActivity(intent)
        }

        buttonNavigate2.setOnClickListener {
            // stage2 버튼, 누르면 stage2 시작 화면으로 이동
            val intent = Intent(this@Quiz2, Quiz4::class.java)
            startActivity(intent)
        }

        buttonNavigate3.setOnClickListener {
            // stage3 버튼, 누르면 stage3 시작 화면으로 이동
            val intent = Intent(this@Quiz2, Quiz5::class.java)
            startActivity(intent)
        }

        buttonNavigate4.setOnClickListener {
            // stage4 버튼, 누르면 stage4 시작 화면으로 이동
            val intent = Intent(this@Quiz2, Quiz6::class.java)
            startActivity(intent)
        }

        buttonNavigate5.setOnClickListener {
            // stage5 버튼, 누르면 stage5 시작 화면으로 이동
            val intent = Intent(this@Quiz2, Quiz7::class.java)
            startActivity(intent)
        }

    }
}