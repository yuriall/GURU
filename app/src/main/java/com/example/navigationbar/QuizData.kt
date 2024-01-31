package com.example.navigationbar

object QuizData {
    fun getQuestion() : ArrayList<Question>{
        val queList : ArrayList<Question> = arrayListOf()

        val q1 = Question(
            1,
            "지진에 대한 설명으로 옳지 않은 것은?",
            "지진은 완벽히 예측할 수 없다",
            "지진 대피소에 반려동물과 함께 갈 수 있다",
            "지구 내부는 여러가지 물질로 구성되어있다.",
            2
        )

        val q2 = Question(
            2,
            "생존 배낭에 들어갈 물건이 아닌 것은?",
            "식수",
            "담요",
            "화장품",
            3
        )

        val q3 = Question(
            3,
            "공습경보가 울렸을 때 취해야 할 행동으로 알맞은 것은?",
            "화생방 공격에는 대비하지 않아도 된다",
            "지하대피소 등 안전한 곳으로 빨리 대피해야한다",
            "고층건물은 옥상으로 올라가야한다",
            2
        )

        queList.add(q1)
        queList.add(q2)
        queList.add(q3)

        return queList
    }
}