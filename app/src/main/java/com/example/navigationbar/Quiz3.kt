package com.example.navigationbar

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.navigationbar.databinding.ActivityQuiz3Binding
//stage1 퀴즈 액티비티 화면
class Quiz3 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityQuiz3Binding
    //현재 질문 위치를 나타내는 변수
    private var currentPosition: Int = 1
    //선택된 답변을 나타내는 변수
    private var selectedOption: Int = 0
    //점수
    private var score: Int = 0
    //배열리스트 선언 QuizData를 Question 리스트에 저장할 것임
    private lateinit var questionList: ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuiz3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //QuizData에 있는 문제들을 questionList에 가져옴
        questionList = QuizData.getQuestion()
        //화면 셋팅
        getQuestionData()
        //binding을 사용하여 선택지 버튼 클릭리스너 설정
        binding.stage1option1.setOnClickListener(this)
        binding.stage1option2.setOnClickListener(this)
        binding.stage1option3.setOnClickListener(this)
        //binding을 사용하여 제출 버튼 클릭리스너 설정
        binding.btnSubmit.setOnClickListener {
            if (selectedOption != 0) {
                val question = questionList[currentPosition - 1]
                // 정답 체크(선택 답변과 정답 비교)
                if (selectedOption == question.correct_answer) {
                    // 정답 처리
                    score++
                }
            }
            //다음 질문으로 넘어감
            currentPosition++
            //더 넘어갈 질문이 있는지 확인
            if (currentPosition <= questionList.size) {
                // 다음 문제 셋팅
                getQuestionData()
                if (currentPosition == questionList.size) {
                    binding.btnSubmit.text = getString(R.string.submit, "결과 확인하기")
                } else {
                    binding.btnSubmit.text = getString(R.string.submit, "다음")
                }
            } else {
                //만약 질문이 다 나왔다면, 결과 액티비티로 넘어감
                val intent = Intent(this@Quiz3, Quiz8::class.java)
                intent.putExtra("Score", score)
                intent.putExtra("Total", questionList.size)
                startActivity(intent)
                finish()
            }
        }

    }
    //화면셋팅하는 getQuestionData() 함수
    private fun getQuestionData(){
        //답변 설정 초기화
        setOptionStyle()
        //질문 변수에 담기
        val question = questionList[currentPosition-1]

        binding.progressBar.progress = currentPosition //상태바 위치
        binding.progressBar.max = questionList.size //상태바 최대값
        binding.progressText.text = getString(R.string.count_label, currentPosition, questionList.size) //상태바 현재 위치 표시
        binding.questionText.text = question.question //질문 표시
        //답변 표시
        binding.stage1option1.text = question.option_one
        binding.stage1option2.text = question.option_two
        binding.stage1option3.text = question.option_three
        //다음 버튼 표시
        setSubmitBtn("다음")
    }
    //제출 버튼 텍스트 설정
    private fun setSubmitBtn(name: String){
        binding.btnSubmit.text = getString(R.string.submit, name)
    }
    //답변 설정을 초기화하는 setOptionStyle() 함수
    private fun setOptionStyle(){
        var optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(0, binding.stage1option1)
        optionList.add(0, binding.stage1option2)
        optionList.add(0, binding.stage1option3)

        //답변 텍스트뷰 설정
        for(op in optionList){
            op.setTextColor(ContextCompat.getColor(this, R.color.mindfulBrown_80))
            op.background = ContextCompat.getDrawable(this, R.drawable.option_background)
            op.typeface = Typeface.DEFAULT
        }
    }
    //답변을 선택했을때 디자인이 바뀌게 설정하는 selectedOptionStyle() 함수
    private fun selectedOptionStyle(view: TextView, opt: Int){
        //옵션초기화
        setOptionStyle()
        //선택한 선지 디자인을 바꿈
        selectedOption = opt
        view.setTextColor((ContextCompat.getColor(this, R.color.mindfulBrown_80)))
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_option_background)
        view.typeface = Typeface.DEFAULT_BOLD
    }
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.stage1option1 -> selectedOptionStyle(binding.stage1option1, 1)
            R.id.stage1option2 -> selectedOptionStyle(binding.stage1option2, 2)
            R.id.stage1option3 -> selectedOptionStyle(binding.stage1option3, 3)
        }
    }
}

