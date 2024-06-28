package com.fa.slippybottombar

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.fa.lib.SlippyBadgeStyle
import com.fa.lib.SlippyBar
import com.fa.lib.SlippyBarStyle
import com.fa.lib.SlippyBottomBar
import com.fa.lib.SlippyDividerStyle
import com.fa.lib.SlippyIconStyle
import com.fa.lib.SlippyTab
import com.fa.lib.SlippyTextStyle
import com.fa.lib.SlippyTheme
import com.fa.slippybottombar.ui.theme.SlippyBottomBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlippyBottomBarTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SlippyDemonstration(isEnable = true)
                }
            }
        }
    }
}

@Composable
fun SlippyDemonstration(isEnable: Boolean) {
    val context: Context = LocalContext.current
    var currentPage: String by remember {
        mutableStateOf(value = context.getString(R.string.record))
    }

    val badgeCounter: MutableState<Int> = remember {
        mutableIntStateOf(value = 0)
    }

    val badgeVisibility: MutableState<Boolean> = remember {
        mutableStateOf(value = true)
    }

    if (isEnable) {
        SlippyCommands(
            page = currentPage, counter = badgeCounter, visibility = badgeVisibility
        )
    }

    val tabs: List<SlippyTab> =
        listOf(SlippyTab(name = R.string.home, icon = R.drawable.home, action = {
            currentPage = getPage(context = context, id = R.string.home)
        }), SlippyTab(name = R.string.search, icon = R.drawable.search, action = {
            currentPage = getPage(context = context, id = R.string.search)
        }), SlippyTab(name = R.string.record, icon = R.drawable.record, action = {
            currentPage = getPage(context = context, id = R.string.record)
        }), SlippyTab(name = R.string.records,
            icon = R.drawable.records,
            enableBadge = badgeVisibility.value,
            badgeCount = badgeCounter.value,
            action = {
                currentPage = getPage(context = context, id = R.string.records)
            }), SlippyTab(name = R.string.settings, icon = R.drawable.settings, action = {
            currentPage = getPage(context = context, id = R.string.settings)
        })
        )

    SlippyBottomBar(
        theme = SlippyTheme.LINE, bar = SlippyBar(
            barStyle = SlippyBarStyle(backgroundColor = R.color.white), textStyle = SlippyTextStyle(
                enabledTextColor = R.color.enabledTextColor,
                disabledTextColor = R.color.disabledTextColor
            ), iconStyle = SlippyIconStyle(
                disabledIconColor = R.color.disabledIconColor,
                enabledIconColor = R.color.enabledIconColor, // When the round style is chosen, it should be white in color.
            ), dividerStyle = SlippyDividerStyle(
                dividerColor = R.color.dividerColor
            ), badgeStyle = SlippyBadgeStyle(
                backgroundColor = R.color.red, contentColor = R.color.white
            ), startIndex = 2
        ), tabs = tabs
    )
}

@Composable
fun SlippyCommands(page: String, counter: MutableState<Int>, visibility: MutableState<Boolean>) {
    val context: Context = LocalContext.current

    Spacer(modifier = Modifier)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = context.getString(R.string.page_desc, page), textAlign = TextAlign.Center
        )

        Button(onClick = {
            counter.value = ++counter.value
        }) {
            Text(text = stringResource(id = R.string.click_increase))
        }

        Button(onClick = {
            visibility.value = visibility.value.not()
        }) {
            Text(text = stringResource(id = R.string.click_hide))
        }
    }
}

private fun getPage(context: Context, @StringRes id: Int): String =
    ContextCompat.getString(context, id)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SlippyBottomBarTheme {
        SlippyDemonstration(isEnable = false)
    }
}