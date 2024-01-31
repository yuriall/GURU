package com.example.navigationbar

object QuizData2 {
    fun getQuestion() : ArrayList<Question>{
        val queList : ArrayList<Question> = arrayListOf()

        val q1 = Question(
            1,
            "경계경보가 울렸을 시 취해야 할 행동으로 알맞지 않은 것은?",
            "경계경보가 울리면 대피할 준비를 해야 한다",
            "경계경보가 울렸다면 화생방 공격에 대비해야 한다",
            "외부 가스밸브를 차단할 필요는 없다",
            3
        )

        val q2 = Question(
            2,
            "지진이 발생했을 때 취해야 할 행동으로 알맞지 않은 것은?",
            "흔들리는 동안 빠르게 밖으로 대피한다",
            "흔들림이 멈추면 전기와 가스를 차단한다",
            "대피 장소를 찾을 때 차량을 절대 이용하지 않는다",
            1
        )

        val q3 = Question(
            3,
            "수상한 사람이나 물체를 발견했을 때 어떻게 해야할까?",
            "독성이 강한 가스 냄새는 신고대상이라 신고해야 한다",
            "불발탄이나 지뢰 등으로 보이는 이상한 물체는 신고대상이 아니다",
            "수상한 사람이나 물체가 발견되면 전화 112 또는 113에 신고해야 한다",
            2
        )

        queList.add(q1)
        queList.add(q2)
        queList.add(q3)

        return queList
    }
}