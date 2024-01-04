package com.fa.lib

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes

data class SlippyIconStyle(
    @DimenRes val iconSize: Int,
    @ColorRes val disabledIconColor: Int,
    @ColorRes val enabledIconColor: Int
)