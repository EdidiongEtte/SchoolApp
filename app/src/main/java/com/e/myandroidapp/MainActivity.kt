package com.e.myandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.e.myandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var rightAnswer : String? =null
    private var rightAnswerCount= 0
    private var quizCount = 1
    private val QUIZ_COUNT = 5

    private val quizData = mutableListOf(
        // country, capital, choice1, choice2, choice3
    mutableListOf("China", "Beijing", "Manila", "Stockholm", "Jakarta"),
        mutableListOf("Brazil", "Brasilia", "Havana", "Kingston", "Bangkok"),
        mutableListOf("France", "Paris", "Ottawa", "London", "Taipei"),
        mutableListOf("Germany", "Berlin", "Copenhagen", "Stockholm", "Santiago"),
        mutableListOf("Spain", "Madrid", "San Jose", "Tokyo", "London"),
        mutableListOf("Cuba", "Havana", "Porto", "StockPort", "Mexico City"),
        mutableListOf("Italy", "Rome", "Paris", "Athens", "Jersey")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding =ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // shiffle around the quiz
        quizData.shuffle()

        showNextQuiz()

    }
    fun showNextQuiz() {
        //CountLabel Update
        binding.countLabel.text = getString(R.string.count_Label, quizCount)

      // Pick one Quiz from the set
        val quiz = quizData[0]

        //set the question and the correct answer
        binding.questionLabel.text = quiz[0]
        rightAnswer = quiz[1]

        //Remove "Country" from quiz
        quiz.removeAt(0)

        // shuffle
        quiz.shuffle()

        // set the choices
        binding.answerBtn1.text = quiz[0]
        binding.answerBtn2.text = quiz[1]
        binding.answerBtn3.text = quiz[2]
        binding.answerBtn4.text = quiz[3]

        //remove the quiz from quizData
        quizData.removeAt(0)
    }
    fun checkAnswer(view: View) {
         val answerBtn: Button = findViewById(view.id)
        val btnText = answerBtn.text.toString()

        val alertTitle: String
        if(btnText == rightAnswer){
            // Correct
            alertTitle = "Correct :-)"
            rightAnswerCount++
        } else{
            // Incorrect
            alertTitle ="Incorrect :-("
        }
         // Create Dialog.
       AlertDialog.Builder(this)
           .setTitle(alertTitle)
           .setMessage("Answer: $rightAnswer" )
           .setPositiveButton("OK") {dialogInterface,i ->
               checkQuizCount()
           }
           .setCancelable(false)
           .show()

    }
    fun checkQuizCount() {
      if(quizCount == QUIZ_COUNT){
          //give the results
      } else {
          quizCount++
          showNextQuiz()
      }
    }


}