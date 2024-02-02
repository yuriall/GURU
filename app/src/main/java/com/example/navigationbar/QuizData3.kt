package com.example.navigationbar

object QuizData3 {
    fun getQuestion() : ArrayList<Question>{
        val queList : ArrayList<Question> = arrayListOf()

        val q1 = Question(
            1,
            "지진에 대한 설명으로 알맞지 않은 것은?",
            "여진은 지진의 여파로 인해 발생하는 지진의 뒷 일을 뜻한다",
            "우리나라는 여진 등의 위험에 방지하기 위하여 인근 지역에 거주하는 국민들에게도 지진 재난 문자를 발송하고 있다 ",
            "우리나라는 지진의 규모에 상관없이 전국에 지진 재난 문자를 발송한다",
            3
        )

        val q2 = Question(
            2,
            "재해구조법 3조에서 대피 대상으로 규정된 사람은 누구인가? (Hint. 지진)",
            "이재민, 일시대피자만 해당",
            "이재민, 일시대피자, 대통령령으로 정해진 사람",
            "모든 시민",
            2
        )

        val q3 = Question(
            3,
            "민방공 경보에 대한 설명으로 알맞지 않은 것은?",
            "경계경보는 적의 공격이 예상될 때 울리는 민방공 경보이다",
            "화생방경보는 민방공 경보에 포함되지 않는다",
            "공습경보는 적의 공격이 긴박하거나 공습 중일 때 울리는 민방공 경보이다",
            2
        )

        queList.add(q1)
        queList.add(q2)
        queList.add(q3)

        return queList
    }
}