package com.example.navigationbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.math.E

class Earthquake : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earthquake)

        val button_back=findViewById<Button>(R.id.button_back)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)

        fun moveToAnotherPage(className: Class<*>) {
            val intent = Intent(this, className)
            startActivity(intent)
        }

        button_back.setOnClickListener {
            moveToAnotherPage(KnowledgeSelect::class.java)
        }

        button5.setOnClickListener {
            moveToAnotherPage(Earthquake1::class.java)
        }

        button6.setOnClickListener {
            moveToAnotherPage(Earthquake2::class.java)
        }

        button7.setOnClickListener {
            moveToAnotherPage(Earthquake3::class.java)
        }

        button8.setOnClickListener {
            moveToAnotherPage(Earthquake4::class.java)
        }
    }
}