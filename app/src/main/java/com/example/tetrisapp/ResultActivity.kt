package com.example.tetrisapp

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.tetrisapp.databinding.ActivityResultBinding
import kotlinx.android.synthetic.main.activity_result.*
import kotlin.random.Random

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding


    private lateinit var imageURL: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        binding = ActivityResultBinding.inflate(layoutInflater).also{setContentView(it.root)}

        val username = intent.getStringExtra(Constants.USER_NAME)
        tv_name.text = username.toString()
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        total_question.text = "Your score is $totalQuestions"
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
        total_answers.text = "You've answered $correctAnswers of $totalQuestions"


        var percentCorrect : Int = (correctAnswers/totalQuestions)*100
            imageURL =
                when(percentCorrect){
                    in 0..20 -> imagesList[0]
                    in 21..40 -> imagesList[1]
                    in 41..60 -> imagesList[2]
                    in 61..80 -> imagesList[3]
                    in 81..100 -> imagesList[4]
                    else -> imagesList[0]
                }
        Glide.with(this)
            .load(imageURL)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .error(R.drawable.error_connection)
            .centerCrop()
            .into(binding.resultImageview)

        btn_start.setOnClickListener{
            val intent = Intent(this, HomeActivity :: class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_IMAGE_URL,imageURL)
    }
    companion object{
        const val KEY_IMAGE_URL = "KEY_IMAGE_URL"
        val imagesList = listOf(
            "https://c.tenor.com/gGJQ7kdMRnwAAAAM/%D1%81%D0%BA%D0%B0%D0%BB%D0%B0%D0%B4%D0%B6%D0%BE%D0%BD%D1%81%D0%BE%D0%BD.gif",
            "https://medialeaks.ru/wp-content/uploads/2021/07/1_779a625e-600x338.jpg",
            "https://fs.kinomania.ru/file/film_frame/9/bb/9bb30a604b56c2f69fce6129a845b79b.jpeg",
            "https://v1.popcornnews.ru/k2/news/1200/upload/3LpWMI.jpg",
            "https://i.playground.ru/p/H82xiTeOK6kYNWiDERGhQg.jpeg"
        )
    }
}