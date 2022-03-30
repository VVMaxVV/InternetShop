package com.example.internetshop.model.data.factory

import android.graphics.Color
import androidx.core.graphics.toColor
import com.example.internetshop.domain.data.model.category.BaseCategory
import javax.inject.Inject

class NotificationCategoryFactory @Inject constructor() {
    fun create(): BaseCategory.Notification {
        return when ((0..2).random()) {
            0 -> BaseCategory.Notification(
                "SUMMER SALES",
                "Up to 50% off",
                Color.parseColor("#DB3022").toColor()
            )
            1 -> BaseCategory.Notification(
                "-50%",
                "Summer sales",
                Color.parseColor("#DB3022").toColor()
            )
            else -> BaseCategory.Notification(
                "SALES",
                "Black friday",
                Color.parseColor("#0C0C0C").toColor()
            )
        }
    }
}