package com.example.navigationbar

object QuizData4 {
    fun getQuestion() : ArrayList<Question>{
        val queList : ArrayList<Question> = arrayListOf()

        val q1 = Question(
            1,
            "",
            "",
            "",
            "",
            3
        )

        val q2 = Question(
            2,
            "",
            "",
            "",
            "",
            1
        )

        val q3 = Question(
            3,
            "",
            "",
            "",
            "",
            2
        )

        queList.add(q1)
        queList.add(q2)
        queList.add(q3)

        return queList
    }
}