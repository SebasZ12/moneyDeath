package com.example.moneydeath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.moneydeath.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    data class Question(
        val text: String,
        val options: List<ResponseOption>)

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (or better yet, not define the questions in code...)
    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "Do you really need this?",
            options = listOf(ResponseOption("yes", ResponseOption.WIN_ACTION), ResponseOption("no", ResponseOption.LOSE_ACTION))),
        Question(text = "for real?",
            options = listOf(ResponseOption("yeah", ResponseOption.WIN_ACTION),ResponseOption("nah", ResponseOption.LOSE_ACTION))),
        Question(text = "k g, so can you afford it?",
            options = listOf(ResponseOption("fo sure", ResponseOption.WIN_ACTION),ResponseOption("nop", ResponseOption.LOSE_ACTION))),
        Question(text = "will you get in a ton of debt for this?",
            options = listOf(ResponseOption("nop", ResponseOption.WIN_ACTION), ResponseOption("yes.", ResponseOption.LOSE_ACTION))),
        Question(text = "It will pay itself with time?",
            options = listOf(ResponseOption("yass", ResponseOption.WIN_ACTION), ResponseOption("nou", ResponseOption.LOSE_ACTION))),
        Question(text = "again.. it is necessary?",
            options = listOf(ResponseOption("YESSS", ResponseOption.WIN_ACTION), ResponseOption("k no", ResponseOption.LOSE_ACTION)))
    )

    lateinit var currentQuestion: Question
    lateinit var options: MutableList<ResponseOption>
    lateinit var binding: FragmentGameBinding
    var questionIndex = 0
    val numQuestions = questions.size

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false)
        setOptions()
        binding.game = this
        return binding.root
    }

    fun setOptions() {
        currentQuestion = questions[questionIndex]
        options = currentQuestion.options.toMutableList()
        binding.gameOptionsContainer.removeAllViews()
        createResponseOptionButtonComponents(currentQuestion)
        (activity as AppCompatActivity).supportActionBar?.title = "game nibba"
    }

    private fun createResponseOptionButtonComponents(question: Question) {
        for (option: ResponseOption in question.options) {
            val optionComponent = context?.let { GameOptionComponent(this, it) }
            optionComponent?.setData(option)
            binding.gameOptionsContainer.addView(optionComponent)
        }
    }

}