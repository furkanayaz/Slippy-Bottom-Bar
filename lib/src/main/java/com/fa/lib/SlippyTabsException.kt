package com.fa.lib

private const val exceptionMessage =
    "To use Slippy-Bottom-Bar, the slippy-tab type list you provide as a parameter must not be empty."

data class SlippyTabsException(
    override val message: String? = exceptionMessage, override val cause: Throwable? = null
) : Exception()