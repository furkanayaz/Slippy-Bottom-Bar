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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.fa.lib.SlippyBar
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
                    SlippyDemonstration()
                }
            }
        }
    }
}

@Composable
fun SlippyDemonstration() {
    val context: Context = LocalContext.current
    var currentPage: String by remember {
        mutableStateOf(value = "Home")
    }

    val tabs: List<SlippyTab> =
        listOf(SlippyTab(name = R.string.home, icon = R.drawable.home, action = {
            currentPage = getPage(context = context, id = R.string.home)
        }), SlippyTab(name = R.string.search, icon = R.drawable.search, action = {
            currentPage = getPage(context = context, id = R.string.search)
        }), SlippyTab(name = R.string.record, icon = R.drawable.record, action = {
            currentPage = getPage(context = context, id = R.string.record)
        }), SlippyTab(name = R.string.records, icon = R.drawable.records, action = {
            currentPage = getPage(context = context, id = R.string.records)
        }), SlippyTab(name = R.string.settings, icon = R.drawable.settings, action = {
            currentPage = getPage(context = context, id = R.string.settings)
        })
        )

    SlippyTab.addTabs(tabs = tabs)

    Spacer(modifier = Modifier)
    Text(
        text = "Slippy bottom bar for the demonstration.\nYou are in $currentPage page.",
        textAlign = TextAlign.Center
    )

    SlippyBottomBar(
        theme = SlippyTheme.LINE, bar = SlippyBar(
            backgroundColor = R.color.white, textStyle = SlippyTextStyle(
                textSize = R.dimen.textSize,
                enabledTextColor = R.color.enabledTextColor,
                disabledTextColor = R.color.disabledTextColor
            ), iconStyle = SlippyIconStyle(
                iconSize = R.dimen.iconSize,
                disabledIconColor = R.color.disabledIconColor,
                enabledIconColor = R.color.enabledIconColor, // When the round style is chosen, it should be white in color.
            ), dividerStyle = SlippyDividerStyle(
                dividerColor = R.color.dividerColor
            )
        )
    )
}

private fun getPage(context: Context, @StringRes id: Int): String =
    ContextCompat.getString(context, id)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SlippyBottomBarTheme {
        SlippyDemonstration()
    }
}