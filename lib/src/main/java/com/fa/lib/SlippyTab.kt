package com.fa.lib

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SlippyTab(
    @StringRes val name: Int, @DrawableRes val icon: Int, val action: (() -> Unit) ? = null
) {
    companion object {
        internal val slippyTabs: MutableList<SlippyTab> = mutableListOf()
        fun addTabs(tabs: List<SlippyTab>) {
            slippyTabs.clear()
            slippyTabs.addAll(tabs)
        }

        internal fun getTabsSize(): Int = slippyTabs.size
    }
}