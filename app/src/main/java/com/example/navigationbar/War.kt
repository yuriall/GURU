package com.example.navigationbar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class War : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_war)

        //사용할 버튼을 정의
        val button_back=findViewById<Button>(R.id.button_back)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)

        //다른 페이지로 이동할 수 있도록 Intent로 정의
        fun moveToAnotherPage(className: Class<*>) {
            val intent = Intent(this, className)
            startActivity(intent)
        }

        //버튼이 눌리는 것을 감지하여 버튼이 눌리면 해당 페이지로 이동
        button_back.setOnClickListener {
            moveToAnotherPage(KnowledgeSelect::class.java)
        }
        
        button1.setOnClickListener {
            moveToAnotherPage(War1::class.java)
        }

        button2.setOnClickListener {
            moveToAnotherPage(War2::class.java)
        }

        button3.setOnClickListener {
            moveToAnotherPage(War3::class.java)
        }

        button4.setOnClickListener {
            moveToAnotherPage(War4::class.java)
        }
    }
}
