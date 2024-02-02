package com.example.navigationbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Earthquake3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earthquake3)

        //사용할 버튼 정의
        val button_back=findViewById<Button>(R.id.button_back)

        //다른 페이지로 이동할 수 있도록 Intent로 정의
        fun moveToAnotherPage(className: Class<*>) {
            val intent = Intent(this, className)
            startActivity(intent)
        }
        //버튼 클릭 시에 이전 페이지로 이동
        button_back.setOnClickListener {
            moveToAnotherPage(Earthquake::class.java)
        }
    }
}