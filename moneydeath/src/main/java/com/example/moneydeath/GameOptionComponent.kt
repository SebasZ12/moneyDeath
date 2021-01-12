package com.example.moneydeath

import android.R.attr
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController

@SuppressLint("ViewConstructor")
class GameOptionComponent(val fragment: GameFragment, context: Context, attrs: AttributeSet? = null) :
    ConstraintLayout(context, attrs) {

    private lateinit var buttonOptionText: TextView

    fun setData(responseOption: ResponseOption) {
        val constraintSet = ConstraintSet()

        setButtonOption(responseOption)
        constraintSet.clone(this)

        constraintSet.constrainWidth(buttonOptionText.id, ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(buttonOptionText.id, ConstraintSet.WRAP_CONTENT);
        constraintSet.setHorizontalBias(buttonOptionText.id, 0.25F)
        constraintSet.connect(buttonOptionText.id, ConstraintSet.TOP, this.id, ConstraintSet.TOP, 60);

        constraintSet.applyTo(this)
    }

    private fun setButtonOption(responseOption: ResponseOption) {
        buttonOptionText = TextView(context)
        /*
        val paddingPixel = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                R.dimen.death_option_button_margin_start.toFloat(),
                context.resources.displayMetrics
        ).toInt()
        buttonOptionText.setPadding(paddingPixel, paddingPixel, paddingPixel, paddingPixel)
        val params = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        params.setMargins(R.dimen.death_option_button_margin_start, R.dimen.death_option_button_margin_start, R.dimen.death_option_button_margin_start, R.dimen.death_option_button_margin_start)
        buttonOptionText.layoutParams= params*/
        buttonOptionText.id = View.generateViewId()
        buttonOptionText.background = ContextCompat.getDrawable(context, R.drawable.game_button_option_background)
        buttonOptionText.text = responseOption.optionText
        setActionButton(responseOption)
        addView(buttonOptionText)
    }

    private fun setActionButton(responseOption: ResponseOption) {
        buttonOptionText.setOnClickListener {
            if(responseOption.optionAction == ResponseOption.LOSE_ACTION) {
                if (fragment.questionIndex < fragment.numQuestions - 1) {
                    fragment.questionIndex++
                    fragment.setOptions()
                    fragment.binding.invalidateAll()
                } else {
                    fragment.view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToDeathFragment())
                }
            } else {
                fragment.view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToWinFragment())
            }
        }
    }

}