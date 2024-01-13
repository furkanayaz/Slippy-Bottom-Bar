package com.fa.lib

internal const val tabsEmptyMessage =
    "To use Slippy-Bottom-Bar, the slippy-tab type list you provide as a parameter must not be empty."

internal const val startIndexGreaterMessage =
    "StartIndex variable cannot be greater than the size of the slippy tabs list."

internal data class SlippyTabsException(
    override val message: String?, override val cause: Throwable? = null
) : Exception()