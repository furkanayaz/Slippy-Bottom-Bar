package com.fa.lib

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.compose.ui.text.style.TextAlign

data class SlippyBar(
    @DimenRes val textSize: Int? = null,
    @ColorRes val enabledTextColor: Int? = null,
    @ColorRes val disabledTextColor: Int? = null,
    @DimenRes val iconSize: Int,
    @ColorRes val disabledIconColor: Int,
    @ColorRes val enabledIconColor: Int,
    @ColorRes val dividerColor: Int? = null,
    val textAlign: TextAlign = TextAlign.Center,
    val maxLines: Int = 1
)
