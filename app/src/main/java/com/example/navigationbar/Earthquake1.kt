package com.example.navigationbar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Earthquake1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earthquake1)

        val button_back=findViewById<Button>(R.id.button_back)
        val button_123 = findViewById<Button>(R.id.button_123)

        button_123.setOnClickListener {
            val url = "https://www.safekorea.go.kr/idsiSFK/neo/ext/img/prevent/220117%20%EB%B0%98%EB%A0%A4%EB%8F%99%EB%AC%BC%20%EA%B0%80%EC%A1%B1%EC%9D%84%20%EC%9C%84%ED%95%9C%20%EC%9E%AC%EB%82%9C%20%EB%8C%80%EC%9D%91%20%EA%B0%80%EC%9D%B4%EB%93%9C%EB%9D%BC%EC%9D%B8(%EA%B5%AD%EB%AF%BC%EC%9A%A9).pdf"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        fun moveToAnotherPage(className: Class<*>) {
            val intent = Intent(this, className)
            startActivity(intent)
        }
        button_back.setOnClickListener {
            moveToAnotherPage(KnowledgeSelect::class.java)
        }
    }
}
