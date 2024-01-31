package com.example.navigationbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class KnowledgeSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowledgeselect)

        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)

        fun moveToAnotherPage(className: Class<*>) {
            val intent = Intent(this, className)
            startActivity(intent)

        }
        button3.setOnClickListener {
            moveToAnotherPage(Earthquake::class.java)
        }

        button4.setOnClickListener {
            moveToAnotherPage(War::class.java)
        }
    }
}