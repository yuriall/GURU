package com.example.navigationbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.navigationbar.databinding.ActivityQuiz8Binding

class Quiz8 : AppCompatActivity() {
    private lateinit var binding:ActivityQuiz8Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuiz8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val score = intent.getIntExtra("Score", 0)
        val totalSize = intent.getIntExtra("Total", 0)

        //점수 보여주기
        binding.scoreText.text = getString(R.string.count_label, score,totalSize)

        //다시하기버튼
        binding.resetBtn.setOnClickListener(){
            val intent = Intent(this@Quiz8, Quiz::class.java)
            startActivity(intent)
        }
    }
}