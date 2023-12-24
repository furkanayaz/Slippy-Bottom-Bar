package com.fa.lib

import androidx.annotation.ColorRes

data class SlippyBar(
    @ColorRes val backgroundColor: Int,
    val textStyle: SlippyTextStyle? = null,
    val iconStyle: SlippyIconStyle? = null,
    val dividerStyle: SlippyDividerStyle? = null,
    val animationMillis: Int = 250
)
