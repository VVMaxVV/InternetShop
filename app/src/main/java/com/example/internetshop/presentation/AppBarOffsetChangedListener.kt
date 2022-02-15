package com.example.internetshop.presentation

import android.view.View
import androidx.core.graphics.Insets
import com.google.android.material.appbar.AppBarLayout


class AppBarOffsetChangedListener(
    private val contentView: View,
    private val bottomNav: View,
    private val divider: View? = null
) : AppBarLayout.OnOffsetChangedListener {
    private var windowInsets: Insets? = null
    fun updateInsets(windowInsets: Insets) {
        this.windowInsets = windowInsets
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val bottomPadding = appBarLayout.totalScrollRange + verticalOffset
        divider?.let {
            val dividerVisibility =
                if (bottomPadding <= 0)
                    View.VISIBLE else View.INVISIBLE

            it.visibility = dividerVisibility
        }
        val insets = windowInsets?.top ?: 0
        contentView.apply {
            setPadding(
                this.paddingLeft,
                this.paddingTop,
                this.paddingRight,
                bottomPadding + insets + bottomNav.paddingBottom
            )
        }
    }
}