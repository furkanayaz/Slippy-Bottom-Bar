package com.fa.lib

internal enum class ExceptionMessage(val message: String) {
    TABS_EMPTY_MESSAGE("To use Slippy-Bottom-Bar, the slippy-tab type list you provide as a parameter must not be empty."),
    START_INDEX_GREATER_MESSAGE("StartIndex variable cannot be greater than the size of the slippy tabs list.")
}

internal data class SlippyTabsException(
    override val message: String?, override val cause: Throwable? = null
) : Exception()