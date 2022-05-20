package com.example.tetrisapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.tetrisapp.databinding.ActivityQuizQuestionsBinding
import com.example.tetrisapp.databinding.ActivityResultBinding
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityQuizQuestionsBinding

    private var mCurrentPosition: Int = 1
    private var mQuestionList : ArrayList<Question>? = null
    private var mSelectedPosition : Int = 0
    private var mCorrectAnswers :Int = 0
    private var mUserName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater).also{setContentView(it.root)}

        mQuestionList = Constants.getQuestions()
        setQuestion()
        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_sumbit.setOnClickListener(this)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
    }
    private fun setQuestion(){

        val question = mQuestionList!![mCurrentPosition-1]

        defaultOptionsView()
        if(mCurrentPosition == mQuestionList!!.size){
            btn_sumbit.text = "FINISH"
        }else{
            btn_sumbit.text = "SUBMIT"
        }
        progressbar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressbar.max
        ask_question.text = question!!.question
        Glide.with(this)
            .load(question.image)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .error(R.drawable.error_connection)
            .optionalFitCenter()
            .centerCrop()
            .into(binding.ivImage)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one->{
                selectedOptionView(tv_option_one,1)
            }
            R.id.tv_option_two->{
                selectedOptionView(tv_option_two,2)
            }
            R.id.tv_option_three->{
                selectedOptionView(tv_option_three,3)
            }
            R.id.tv_option_four->{
                selectedOptionView(tv_option_four,4)
            }
            R.id.btn_sumbit->{
                if(mSelectedPosition == 0){
                    mCurrentPosition ++
                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }else ->{
                        Toast.makeText(this,
                            "You have successfully completed the Quiz",
                            Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, ResultActivity :: class.java)
                        intent.putExtra(Constants.USER_NAME, mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList!!.size)
                        startActivity(intent)
                        finish()
                        }
                    }
                }
                else{
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedPosition){
                        answerView(mSelectedPosition,R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionList!!.size){
                        btn_sumbit.text = "FINISH"
                    }
                    else{
                        btn_sumbit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedPosition = 0
                }
            }
        }
    }
    private fun answerView(answer:Int, drawableView:Int){
        when(answer){
            1->tv_option_one.background = ContextCompat.getDrawable(
                this, drawableView
            )
            2->tv_option_two.background = ContextCompat.getDrawable(
                this, drawableView
            )
            3->tv_option_three.background = ContextCompat.getDrawable(
                this, drawableView
            )
            4->tv_option_four.background = ContextCompat.getDrawable(
                this, drawableView
            )
        }
    }
    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }
}