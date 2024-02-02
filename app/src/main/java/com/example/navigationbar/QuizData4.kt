package com.example.navigationbar

object QuizData4 {
    fun getQuestion() : ArrayList<Question>{
        val queList : ArrayList<Question> = arrayListOf()

        val q1 = Question(
            1,
            "공습경보 발생 시 안전한 곳으로 즉시 대피해야 하는데, 고층건물에서는 어디로 대피해야 할까?",
            "지붕",
            "지하실 또는 아랫층",
            "계단",
            2
        )

        val q2 = Question(
            2,
            "재난 문자를 받았을 때 국민들이 취해야 할 중요한 행동은 무엇인가?",
            "현재 상황에 대한 경각심을 갖기",
            "대피소로 바로 이동하기",
            "문자 무시하고 일상 생활 유지하기",
            1
        )

        val q3 = Question(
            3,
            "차량을 이용 중에 공습경보가 울리면 어떻게 행동해야 할까?",
            "차량 안에 남아 대기하기",
            "도로 왼쪽에 차량을 세우고 대피하기",
            "차량 창문을 열어 외부 공기를 마시기",
            2
        )

        queList.add(q1)
        queList.add(q2)
        queList.add(q3)

        return queList
    }
}