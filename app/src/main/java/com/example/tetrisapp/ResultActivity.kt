package com.example.tetrisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val username = intent.getStringExtra(Constants.USER_NAME)
        tv_name.text = username.toString()
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        total_question.text = "Your score is $totalQuestions"
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
        total_answers.text = "You've anwered $correctAnswers of $totalQuestions"

        btn_start.setOnClickListener{
            val intent = Intent(this, HomeActivity :: class.java)
            startActivity(intent)
            finish()
        }

    }
}