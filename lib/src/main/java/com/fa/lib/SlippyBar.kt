package com.fa.lib

data class SlippyBar(
    val barStyle: SlippyBarStyle? = null,
    val textStyle: SlippyTextStyle? = null,
    val iconStyle: SlippyIconStyle? = null,
    val dividerStyle: SlippyDividerStyle? = null,
    val badgeStyle: SlippyBadgeStyle? = null,
    val startIndex: Int = 0,
    val animationMillis: Int = 250
) {
    init {
        SlippyOptions.CURRENT_PAGE = startIndex
    }
}
