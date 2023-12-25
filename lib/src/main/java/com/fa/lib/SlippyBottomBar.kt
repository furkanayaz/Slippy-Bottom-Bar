package com.fa.lib

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.fa.slippybottombar.R

/**
 * @since Dec 25, 2023
 * @author Furkan Ayaz
 *
 * @param [theme]
 * The purpose of requesting SlippyTheme is to give special colors and
 * dimension values to the bottom bar view you will create.
 *
 * @param [bar]
 * The purpose of requesting a Slippy Bar is to give special colors, dimension values and
 * animation time to the bottom bar view you will create.
 *
 * @param [tabs]
 * The purpose of requesting Slippy Tabs is to enable the pages you need to create to display
 * the bottom bar view you will create to your users.
 *
 * @exception [tabs] To use Slippy-Bottom-Bar, the slippy-tab type list you provide as a parameter must not be empty.
 *
 * @throws [SlippyTabsException]
 *
 * @return [SlippyBottomBar]
 *
 * @sample
 * val tabs: List<SlippyTab> =
 *         listOf(SlippyTab(name = R.string.home, icon = R.drawable.home, action = {
 *             currentPage = getPage(context = context, id = R.string.home)
 *         }), SlippyTab(name = R.string.search, icon = R.drawable.search, action = {
 *             currentPage = getPage(context = context, id = R.string.search)
 *         }), SlippyTab(name = R.string.record, icon = R.drawable.record, action = {
 *             currentPage = getPage(context = context, id = R.string.record)
 *         }), SlippyTab(name = R.string.records, icon = R.drawable.records, action = {
 *             currentPage = getPage(context = context, id = R.string.records)
 *         }), SlippyTab(name = R.string.settings, icon = R.drawable.settings, action = {
 *             currentPage = getPage(context = context, id = R.string.settings)
 *         })
 *         )
 *
 *     SlippyBottomBar(
 *         theme = SlippyTheme.LINE, bar = SlippyBar(
 *             backgroundColor = R.color.white, textStyle = SlippyTextStyle(
 *                 textSize = R.dimen.textSize,
 *                 enabledTextColor = R.color.enabledTextColor,
 *                 disabledTextColor = R.color.disabledTextColor
 *             ), iconStyle = SlippyIconStyle(
 *                 iconSize = R.dimen.iconSize,
 *                 disabledIconColor = R.color.disabledIconColor,
 *                 enabledIconColor = R.color.enabledIconColor, // When the round style is chosen, it should be white in color.
 *             ), dividerStyle = SlippyDividerStyle(
 *                 dividerColor = R.color.dividerColor
 *             )
 *         ), tabs = tabs)
 * */

@Throws(exceptionClasses = [SlippyTabsException::class])
@Composable
fun SlippyBottomBar(theme: SlippyTheme, bar: SlippyBar, tabs: List<SlippyTab>) {
    if (tabs.isEmpty()) throw SlippyTabsException()

    val divColor: Color = colorResource(id = bar.dividerStyle?.dividerColor ?: R.color.dividerColor)

    val currentTab: MutableIntState = remember {
        mutableIntStateOf(value = 0)
    }

    val barSize: MutableState<IntSize> = remember {
        mutableStateOf(value = IntSize.Zero)
    }

    val barPadding = PaddingValues(
        top = when (theme) {
            SlippyTheme.CLASSIC -> 6.4.dp
            SlippyTheme.LINE -> 18.dp
            SlippyTheme.ROUNDED -> 18.dp
        }, bottom = when (theme) {
            SlippyTheme.CLASSIC -> 0.dp
            SlippyTheme.LINE -> 18.dp
            SlippyTheme.ROUNDED -> 18.dp
        }
    )

    val endOffset = Offset(
        x = ((barSize.value.width / tabs.size) * (currentTab.intValue + 1)).toFloat(), y = 0f
    )

    val startOffset = Offset(x = endOffset.x - (barSize.value.width / tabs.size), y = 0f)

    val animateStartOffset: State<Offset> = animateOffsetAsState(
        targetValue = startOffset, animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMediumLow
        ), label = ""
    )

    Row(modifier = Modifier
        .background(color = colorResource(id = bar.backgroundColor))
        .fillMaxWidth()
        .height(intrinsicSize = IntrinsicSize.Max)
        .onSizeChanged {
            barSize.value = it
        }
        .drawWithCache {
            when (theme) {
                SlippyTheme.LINE, SlippyTheme.ROUNDED -> {
                    //PerformanceTest: Runs until you draw [To perform calculations]
                    val divHeight: Float =
                        if (theme == SlippyTheme.LINE) 10.0F else size.height / 1.5F

                    val divWidth: Float =
                        if (theme == SlippyTheme.LINE) (size.width / tabs.size) / 2.0F else size.width / tabs.size

                    val xPosition: Float =
                        if (theme == SlippyTheme.LINE) animateStartOffset.value.x + divWidth / 2F else animateStartOffset.value.x

                    val yPosition: Float =
                        if (theme == SlippyTheme.LINE) size.height - divHeight else size.height / 2 - (divHeight / 2.0F)

                    val divRadius: Float = if (theme == SlippyTheme.LINE) 5.0F else 100.0F / 1.5F

                    this.onDrawBehind {
                        //PerformanceTest: Runs continuously

                        drawRoundRect(
                            color = divColor, topLeft = Offset(
                                x = xPosition, y = yPosition
                            ), size = Size(
                                width = divWidth, height = divHeight
                            ), cornerRadius = CornerRadius(x = divRadius)
                        )
                    }
                }

                else -> {
                    this.onDrawBehind { /* NO-OP */ }
                }
            }
        },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEachIndexed { index: Int, page: SlippyTab ->
            val animateIconColor: Color by animateColorAsState(
                animationSpec = tween(
                    durationMillis = bar.animationMillis, easing = FastOutLinearInEasing
                ), targetValue = colorResource(
                    id = if (currentTab.intValue == index) bar.iconStyle?.enabledIconColor
                        ?: R.color.enabledIconColor else bar.iconStyle?.disabledIconColor
                        ?: R.color.disabledIconColor
                ), label = ""
            )

            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(weight = 1.0F, fill = true)
                .clickable {
                    if (currentTab.intValue != index) {
                        currentTab.intValue = index
                        page.action?.invoke()
                    }
                }
                .padding(paddingValues = barPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround) {
                Icon(
                    modifier = Modifier.size(
                        size = dimensionResource(
                            id = bar.iconStyle?.iconSize ?: R.dimen.iconSize
                        )
                    ),
                    tint = animateIconColor,
                    painter = painterResource(id = page.icon),
                    contentDescription = stringResource(
                        id = page.name
                    )
                )

                if (theme == SlippyTheme.CLASSIC) {
                    val animateTextColor: Color by animateColorAsState(
                        animationSpec = tween(
                            durationMillis = bar.animationMillis, easing = FastOutLinearInEasing
                        ), targetValue = colorResource(
                            id = if (currentTab.intValue == index) bar.textStyle?.enabledTextColor
                                ?: R.color.enabledTextColor else bar.textStyle?.disabledTextColor
                                ?: R.color.disabledTextColor
                        ), label = ""
                    )

                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = stringResource(id = page.name),
                        maxLines = bar.textStyle?.maxLines ?: 1,
                        textAlign = bar.textStyle?.textAlign,
                        fontWeight = FontWeight.SemiBold,
                        color = animateTextColor,
                        fontSize = TextUnit(
                            value = dimensionResource(
                                id = bar.textStyle?.textSize ?: R.dimen.textSize
                            ).value, type = TextUnitType.Sp
                        )
                    )
                }
            }

        }
    }

}