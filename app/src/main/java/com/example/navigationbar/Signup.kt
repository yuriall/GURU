package com.example.navigationbar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirebaseID {
    companion object {
        const val documentId = "documentId"
        const val email = "email"
        const val password = "password"
        const val name = "name"
    }
}

class Signup : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private lateinit var back: TextView
    private lateinit var signup: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // 이전 버튼
        back = findViewById(R.id.btn_back)
        // 회원가입 버튼
        signup = findViewById(R.id.btn_signup)
        // 회원정보 editText
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_pwd)
        etName = findViewById(R.id.et_name)

        // 로그인 화면으로 돌아가기
        back.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        // 회원가입 버튼 클릭 시 회원가입 처리 함수 호출
        signup.setOnClickListener {
            handleSignupClick()
        }
    }

    private fun handleSignupClick() {
        val inputEmail = etEmail.text.toString().trim()
        val inputPassword = etPassword.text.toString().trim()
        val inputName = etName.text.toString().trim()

        //이메일, 비밀번호, 이름 비어있지 않으면 작업 진행
        if (inputEmail.isNotEmpty() && inputPassword.isNotEmpty() && inputName.isNotEmpty()) {
            mAuth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user: FirebaseUser? = mAuth.currentUser
                        // Firestore에 넣을 데이터 구조화
                        val userMap: MutableMap<String, Any> = HashMap()
                        userMap[FirebaseID.documentId] = user?.uid ?: ""
                        userMap[FirebaseID.email] = inputEmail
                        userMap[FirebaseID.password] = inputPassword
                        userMap[FirebaseID.name] = inputName

                        // Firestore에 데이터 추가
                        db.collection("users").document(user?.uid ?: "")
                            .set(userMap, SetOptions.merge())
                            .addOnSuccessListener {
                                val intent = Intent(this@Signup, Login::class.java)
                                startActivity(intent)
                            }
                            .addOnFailureListener { exception ->
                                //잘못된 오류사항에 대해서 토스트 메시지 띄우기
                                val errorMessage = "Firestore 데이터 추가 실패: ${exception.message}"
                                Toast.makeText(this@Signup, errorMessage, Toast.LENGTH_SHORT).show()
                                Log.e("Firestore", errorMessage, exception)
                            }

                    } else {
                        //오류시 토스트 메시지 띄우기
                        Toast.makeText(this@Signup, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
