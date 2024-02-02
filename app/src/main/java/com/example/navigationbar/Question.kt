package com.example.navigationbar

//한 액티비티에서 질문 여러개를 보여주기 위해 data class를 작성함
data class Question(
    var id: Int,
    var question: String,
    var option_one: String,
    var option_two: String,
    var option_three: String,
    var correct_answer: Int
)
