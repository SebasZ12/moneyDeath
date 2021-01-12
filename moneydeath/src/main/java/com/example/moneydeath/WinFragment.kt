package com.example.moneydeath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.moneydeath.databinding.FragmentWinBinding

class WinFragment : Fragment() {

    var pointsGained: Int? = 1
    var totalPoints: Int? = 1
    lateinit var binding: FragmentWinBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentWinBinding>(
            inflater, R.layout.fragment_win, container, false)
        pointsGained = arguments?.getInt(AMOUNT_POINTS_GAINED)
        totalPoints = pointsGained?.plus(1)
        binding.win = this
        return binding.root

    }

    companion object {
        const val AMOUNT_POINTS_GAINED = "AMOUNT_POINTS_GAINED"
    }
}