package com.fa.lib

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.compose.ui.text.style.TextAlign

data class SlippyTextStyle(
    @DimenRes val textSize: Int? = null,
    @ColorRes val enabledTextColor: Int? = null,
    @ColorRes val disabledTextColor: Int? = null,
    val textAlign: TextAlign = TextAlign.Center,
    val maxLines: Int = 1,
)
