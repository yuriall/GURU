package com.example.navigationbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
//퀴즈 시작화면
class Quiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //레이아웃 설정
        setContentView(R.layout.activity_quiz)
        //버튼 초기화
        val buttonNavigate: Button = findViewById(R.id.button)
        //클릭리스너 설정
        buttonNavigate.setOnClickListener {
            // 클릭 시 다음 액티비티로 전환
            val intent = Intent(this@Quiz, Quiz2::class.java)
            startActivity(intent)
        }
    }
}