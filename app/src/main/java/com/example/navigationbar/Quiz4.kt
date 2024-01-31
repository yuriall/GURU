package com.example.navigationbar

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.navigationbar.databinding.ActivityQuiz4Binding

class Quiz4 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityQuiz4Binding

    private var currentPosition: Int = 1 //질문위치
    private var selectedOption: Int = 0 //선택 답변 값
    private var score: Int = 0 //점수

    private lateinit var questionList: ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuiz4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //질문리스트 가져오기
        questionList = QuizData2.getQuestion()
        //화면 셋팅
        getQuestionData()

        binding.stage1option1.setOnClickListener(this)
        binding.stage1option2.setOnClickListener(this)
        binding.stage1option3.setOnClickListener(this)

        binding.btnSubmit.setOnClickListener {
            if (selectedOption != 0) {
                val question = questionList[currentPosition - 1]
                // 정답 체크(선택 답변과 정답 비교)
                if (selectedOption == question.correct_answer) {
                    // 정답 처리
                    score++
                }
            }

            currentPosition++

            if (currentPosition <= questionList.size) {
                // 다음 문제 셋팅
                getQuestionData()
                if (currentPosition == questionList.size) {
                    binding.btnSubmit.text = getString(R.string.submit, "결과 확인하기")
                } else {
                    binding.btnSubmit.text = getString(R.string.submit, "다음")
                }
            } else {
                // 결과 액티비티로 넘어가는 코드
                val intent = Intent(this@Quiz4, Quiz8::class.java)
                intent.putExtra("Score", score)
                intent.putExtra("Total", questionList.size)
                startActivity(intent)
                finish()
            }
        }

    }//onCreate

    private fun getQuestionData(){
        //답변 설정 초기화
        setOptionStyle()
        //질문 변수에 담기
        val question = questionList[currentPosition-1]

        binding.progressBar.progress = currentPosition //상태바 위치
        binding.progressBar.max = questionList.size //상태바 최대값
        binding.progressText.text = getString(R.string.count_label, currentPosition, questionList.size) //상태바 현재위치 표시
        binding.questionText.text = question.question //질문 표시
        //답변 표시
        binding.stage1option1.text = question.option_one
        binding.stage1option2.text = question.option_two
        binding.stage1option3.text = question.option_three

        setSubmitBtn("다음")
    }

    //제출 버튼 텍스트 설정
    private fun setSubmitBtn(name: String){
        binding.btnSubmit.text = getString(R.string.submit, name)
    }

    private fun setOptionStyle(){
        var optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(0, binding.stage1option1)
        optionList.add(0, binding.stage1option2)
        optionList.add(0, binding.stage1option3)

        //답변 텍스트뷰 설정
        for(op in optionList){
            op.setTextColor(Color.parseColor("#000000"))
            op.background = ContextCompat.getDrawable(this, R.drawable.option_background)
            op.typeface = Typeface.DEFAULT
        }
    }
    //답변 선택 이벤트
    private fun selectedOptionStyle(view: TextView, opt: Int){
        //옵션초기화
        setOptionStyle()
        //위치담기
        selectedOption = opt
        view.setTextColor((Color.parseColor("#4c7380")))
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

