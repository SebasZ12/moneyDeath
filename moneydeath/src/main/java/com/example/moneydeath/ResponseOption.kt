package com.example.moneydeath

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseOption(val optionText: String, val optionAction: String) : Parcelable {
    companion object {
        const val WIN_ACTION = "WIN_ACTION"
        const val LOSE_ACTION = "LOSE_ACTION"
    }
}