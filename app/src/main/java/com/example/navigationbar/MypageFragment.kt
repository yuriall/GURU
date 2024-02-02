package com.example.navigationbar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MypageFragment : Fragment(), View.OnClickListener {

    private lateinit var logoutButton: Button
    private lateinit var tv_name: TextView
    private lateinit var tv_email: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)

        // 로그아웃 버튼
        logoutButton = view.findViewById(R.id.btn_logout)
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            // LoginActivity로 화면 전환
            val intent = Intent(activity, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        // 사용자 정보 표시
        tv_name = view.findViewById(R.id.tv_name)
        tv_email = view.findViewById(R.id.tv_email)

        // Firebase에서 사용자 정보 가져와서 표시
        val user = FirebaseAuth.getInstance().currentUser
        val db = Firebase.firestore
        if (user != null) {
            // 사용자 정보가 있으면 Firestore에서 해당 사용자 정보를 가져와 표시
            val userId = user.uid
            val userRef = db.collection("users").document(userId)

            userRef.get().addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // Firestore 문서가 존재하면 해당 데이터를 가져와 표시
                    val userName = document.getString("name")
                    val userEmail = document.getString("email")

                    tv_name.text = "$userName"
                    tv_email.text = "$userEmail"
                } else {
                    // Firestore 문서가 존재하지 않을 경우 예외 처리
                    Log.d("MypageFragment", "No such document")
                }
            }.addOnFailureListener { exception ->
                // Firestore에서 데이터를 가져오는 중에 예외 발생 시 예외 처리
                Log.d("MypageFragment", "get failed with ", exception)
            }
        }

        return view
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_back -> {
                val intent = Intent(activity, Login::class.java)
                startActivity(intent)
            }
        }
    }
}
