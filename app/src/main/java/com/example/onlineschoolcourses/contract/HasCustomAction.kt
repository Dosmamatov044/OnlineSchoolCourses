package com.example.onlineschoolcourses.contract

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface HasCustomAction {

    /**
     * @return a custom action specification, see [CustomAction] class for details
     */
    fun getCustomAction(): CustomAction

}

class CustomAction(
    @DrawableRes val iconRes: Int,
    @StringRes val textRes: Int,
    val onCustomAction: Runnable
)