package com.example.navigationbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class KnowledgeSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowledgeselect)

        //사용할 버튼을 정의
        val button_back=findViewById<Button>(R.id.button_back)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)

        //다른 페이지로 이동할 수 있도록 Intent로 정의
        fun moveToAnotherPage(className: Class<*>) {
            val intent = Intent(this, className)
            startActivity(intent)
        }

        //버튼이 눌리는 것을 감지하여 버튼이 눌리면 해당 페이지로 이동
        button3.setOnClickListener {
            moveToAnotherPage(Earthquake::class.java)
        }
        button4.setOnClickListener {
            moveToAnotherPage(War::class.java)
        }
        button_back.setOnClickListener {
            moveToAnotherPage(KnowledgeSelect::class.java)
        }
    }
}