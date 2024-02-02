package com.example.navigationbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class War3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_war3)

        val button_back=findViewById<Button>(R.id.button_back)

        fun moveToAnotherPage(className: Class<*>) {
            val intent = Intent(this, className)
            startActivity(intent)
        }
        button_back.setOnClickListener {
            moveToAnotherPage(KnowledgeSelect::class.java)
        }

    }
}