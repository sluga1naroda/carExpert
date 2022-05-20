package com.example.tetrisapp




object Constants {
    const val USER_NAME:String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"


    fun getQuestions():ArrayList<Question>{
        val questionList = ArrayList<Question>()

        val que1 = Question(1,
            "What volume 1jz-gte has?",
            "https://a.d-cd.net/334ece8s-960.jpg",
            "2.3L",
            "1.5L",
            "2.5L",
            "1.6L", 3)
        val que2 = Question(2,
            "What type of drive does a honda s2000 has?",
            "https://images.drive.ru/i/0/566585b095a65668fc000168.jpg",
            "RWD",
            "AWD",
            "4WD",
            "FWD", 1)
        val que3 = Question(3,
            "Which type of BMW wheels is that?",
            "https://a.d-cd.net/5421044s-960.jpg",
            "127",
            "39",
            "37",
            "21", 4)
        val que4 = Question(4,
            "What car is this?",
            "https://i.pinimg.com/originals/e7/22/aa/e722aa39e4290d3952bd332e52ec78b5.png",
            "Honda",
            "Toyota",
            "BMW",
            "Mercedes", 3)
        val que5 = Question(5,
            "Which car has the greatest power/liter ratio?",
            "https://a.d-cd.net/9KAAAgMqleA-960.jpg",
            "Honda Civic type-R",
            "Ferrari 458",
            "Dodge Challenger SRT",
            "Toyota Supra", 1)
        questionList.add(que1)
        questionList.add(que2)
        questionList.add(que3)
        questionList.add(que4)
        questionList.add(que5)
        return  questionList
    }
}