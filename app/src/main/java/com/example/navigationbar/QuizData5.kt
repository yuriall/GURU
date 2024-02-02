package com.example.navigationbar

object QuizData5 {
    fun getQuestion() : ArrayList<Question>{
        val queList : ArrayList<Question> = arrayListOf()

        val q1 = Question(
            1,
            "어떤 지진 규모 이상에서는 전국으로 재난 문자가 송출되도록 되어있나?",
            "규모 3.0",
            "규모 3.5",
            "규모 4.0",
            3
        )

        val q2 = Question(
            2,
            "지진 발생 후 발생 가능한 여진에 대한 위험을 방지하기 위해 어떤 조치가 취해지고 있는가?",
            "여진을 막기",
            "지진 발생 시 인근 지역에 재난 문자 송출",
            "전국민에게 지진 예방 교육 실시",
            2
        )

        val q3 = Question(
            3,
            "생존 가방에 대한 설명으로 알맞지 않은 것은?",
            "생존 가방은 미리 싸놓으면 도움이 된다",
            "생존 가방엔 식수나 비상식량이 없어도 괜찮다",
            "화학 공격을 대비해 우의를 넣어놓아야 한다",
            2
        )

        queList.add(q1)
        queList.add(q2)
        queList.add(q3)

        return queList
    }
}