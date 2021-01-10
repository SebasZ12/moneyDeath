package com.example.moneydeath

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.findNavController

@SuppressLint("ViewConstructor")
class GameOptionComponent(val fragment: GameFragment, context: Context, attrs: AttributeSet? = null) :
    ConstraintLayout(context, attrs) {

    private lateinit var buttonOptionText: TextView

    fun setData(responseOption: ResponseOption) {
        val constraintSet = ConstraintSet()

        setButtonOption(responseOption)

        constraintSet.clone(this)
        constraintSet.setHorizontalBias(buttonOptionText.id, 0.25F)
        constraintSet.connect(
            buttonOptionText.id,
            this.id,
            ConstraintSet.START,
            this.id,
            ConstraintSet.END
        )
        constraintSet.connect(
            buttonOptionText.id,
            this.id,
            ConstraintSet.END,
            this.id,
            ConstraintSet.START
        )

        constraintSet.applyTo(this)
    }

    private fun setButtonOption(responseOption: ResponseOption) {
        buttonOptionText = TextView(context)
        buttonOptionText.id = View.generateViewId()
        buttonOptionText.background = ContextCompat.getDrawable(context, R.drawable.game_button_option_background)
        buttonOptionText.text = responseOption.optionText
        setActionButton(responseOption)
        addView(buttonOptionText)
    }

    private fun setActionButton(responseOption: ResponseOption) {
        val bundle = bundleOf(WinFragment.AMOUNT_POINTS_GAINED to 100)
        buttonOptionText.setOnClickListener {
            if(responseOption.optionAction == ResponseOption.WIN_ACTION) {

                if (fragment.questionIndex < fragment.numQuestions - 1) {
                    fragment.questionIndex++
                    fragment.setOptions()
                    fragment.binding.invalidateAll()
                } else {
                    fragment.view?.findNavController()?.navigate(R.id.action_gameFragment_to_winFragment, bundle)
                }
            } else {
                fragment.view?.findNavController()?.navigate(R.id.action_gameFragment_to_deathFragment)
            }
        }
    }

}