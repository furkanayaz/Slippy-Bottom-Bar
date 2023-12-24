package com.fa.slippybottombar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.fa.lib.SlippyBar
import com.fa.lib.SlippyBottomBar
import com.fa.lib.SlippyTab
import com.fa.lib.SlippyTheme
import com.fa.slippybottombar.ui.theme.SlippyBottomBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlippyBottomBarTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    SlippyDemonstration()
                }
            }
        }
    }
}

@Composable
fun SlippyDemonstration() {
    SlippyTab.addTabs(
        tabs = listOf(
            SlippyTab(name = R.string.home, icon = R.drawable.home),
            SlippyTab(name = R.string.search, icon = R.drawable.search),
            SlippyTab(name = R.string.record, icon = R.drawable.record),
            SlippyTab(name = R.string.bookmarks, icon = R.drawable.bookmark),
            SlippyTab(name = R.string.settings, icon = R.drawable.settings)
        )
    )

    SlippyBottomBar(
        modifier = Modifier.background(color = Color.White),
        theme = SlippyTheme.LINE,
        bar = SlippyBar(
            iconSize = R.dimen.iconSize,
            disabledIconColor = R.color.disabledIconColor,
            enabledIconColor = R.color.enableIconColor,
            dividerColor = R.color.dividerColor
        )
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SlippyBottomBarTheme {
        SlippyDemonstration()
    }
}