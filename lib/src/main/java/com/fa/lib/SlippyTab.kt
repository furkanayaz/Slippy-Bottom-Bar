package com.fa.lib

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SlippyTab(
    @StringRes val name: Int,
    @DrawableRes val icon: Int,
    val enableBadge: Boolean = false,
    val badgeCount: Int? = null,
    val action: (() -> Unit)? = null
)