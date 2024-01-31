package com.example.navigationbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Quiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val buttonNavigate: Button = findViewById(R.id.button)
        buttonNavigate.setOnClickListener {
            // 클릭 시 다음 액티비티로 전환
            val intent = Intent(this@Quiz, Quiz2::class.java)
            startActivity(intent)
        }
    }
}