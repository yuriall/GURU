package com.example.navigationbar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    private lateinit var login: Button
    private lateinit var id: EditText
    private lateinit var password: EditText
    private lateinit var signUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 로그인 버튼
        login = findViewById(R.id.btn_login)
        // 아이디, 비밀번호 editText
        id = findViewById(R.id.et_id)
        password = findViewById(R.id.et_pwd)
        // 회원가입 링크
        signUp = findViewById(R.id.tv_signup)

        // 로그인 버튼 클릭 시 로그인 처리 함수 호출
        login.setOnClickListener {
            handleLoginClick()
        }

        // 회원가입 링크 클릭 시 회원가입 화면으로 이동
        signUp.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
    }

    private fun handleLoginClick() {
        val inputId = id.text.toString().trim()
        val inputPassword = password.text.toString().trim()

        if (inputId.isNotEmpty() && inputPassword.isNotEmpty()) {
            mAuth.signInWithEmailAndPassword(inputId, inputPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // 로그인 성공 시 메인 액티비티로 화면 전환
                        val intent = Intent(this@Login, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // 실패한 경우, Firebase에서 발생한 예외 메시지 표시
                        val exception = task.exception
                        Toast.makeText(this@Login, "로그인 실패: ${exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            // 아이디 또는 비밀번호가 입력되지 않은 경우 메시지 표시
            Toast.makeText(this, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}