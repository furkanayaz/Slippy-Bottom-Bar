package com.fa.lib

data class SlippyBar(
    val barStyle: SlippyBarStyle? = null,
    val textStyle: SlippyTextStyle? = null,
    val iconStyle: SlippyIconStyle? = null,
    val dividerStyle: SlippyDividerStyle? = null,
    val badgeStyle: SlippyBadgeStyle? = null,
    val animationMillis: Int = 250
)
