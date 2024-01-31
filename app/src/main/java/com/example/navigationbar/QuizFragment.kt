package com.example.navigationbar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class QuizFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        // 버튼을 찾아옴
        val button1 = view.findViewById<Button>(R.id.button1)
        val button2 = view.findViewById<Button>(R.id.button2)

        // 버튼에 클릭 이벤트 추가
        button1.setOnClickListener {
            // 다음 Activity로 이동
            val intent = Intent(activity, KnowledgeSelect::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            // 다음 Activity로 이동
            val intent = Intent(activity, Quiz::class.java)
            startActivity(intent)
        }

        return view
    }
}
