package com.example.navigationbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.navigationbar.databinding.ActivityMainBinding


private const val TAG_MYPAGE = "mypage_fragment"
private const val TAG_SHELTER = "shelter_fragment"
private const val TAG_QUIZ = "quiz_fragment"

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setFragment(TAG_MYPAGE ,MypageFragment())


        // 네비게이션 아이콘 클릭 시 해당 프래그먼트로 이동
        binding.navigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.mypageFragment -> setFragment(TAG_MYPAGE, MypageFragment())
                R.id.shelterFragment -> setFragment(TAG_SHELTER, MapFragment())
                R.id.quizFragment-> setFragment(TAG_QUIZ, QuizFragment())
            }
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        // 해당 프래그먼트 없는 경우
        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        // 각 태그에 해당하는 프래그먼트 숨김 처리
        val calender = manager.findFragmentByTag(TAG_MYPAGE)
        val home = manager.findFragmentByTag(TAG_SHELTER)
        val myPage = manager.findFragmentByTag(TAG_QUIZ)

        if (calender != null){
            fragTransaction.hide(calender)
        }

        if (home != null){
            fragTransaction.hide(home)
        }

        if (myPage != null) {
            fragTransaction.hide(myPage)
        }


        // 선택된 태그에 해당하는 프래그먼트 보여주기
        if (tag == TAG_MYPAGE) {
            if (calender!=null){
                fragTransaction.show(calender)
            }
        }
        else if (tag == TAG_SHELTER) {
            if (home != null) {
                fragTransaction.show(home)
            }
        }

        else if (tag == TAG_QUIZ){
            if (myPage != null){
                fragTransaction.show(myPage)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }
}