package com.fa.lib

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.draw.drawBehind
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

@Composable
fun SlippyBottomBar(
    modifier: Modifier, theme: SlippyTheme, bar: SlippyBar
) {
    val barPadding = PaddingValues(
        top = when (theme) {
            SlippyTheme.NORMAL -> 6.4.dp
            SlippyTheme.LINE -> 18.dp
            SlippyTheme.ROUNDED -> 18.dp
        }, bottom = when (theme) {
            SlippyTheme.NORMAL -> 0.dp
            SlippyTheme.LINE -> 18.dp
            SlippyTheme.ROUNDED -> 18.dp
        }
    )

    val divColor: Color = colorResource(id = bar.dividerColor ?: bar.enabledIconColor)

    val currentTab: MutableIntState = remember {
        mutableIntStateOf(value = 0)
    }
    val barSize: MutableState<IntSize> = remember {
        mutableStateOf(value = IntSize.Zero)
    }

    val endOffset = Offset(
        x = ((barSize.value.width / SlippyTab.getTabsSize()) * (currentTab.intValue + 1)).toFloat(),
        y = 0f
    )

    val startOffset =
        Offset(x = endOffset.x - (barSize.value.width / SlippyTab.getTabsSize()), y = 0f)

    val animateStartOffset: State<Offset> = animateOffsetAsState(
        targetValue = startOffset, animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMediumLow
        ), label = ""
    )

    /*val animateEndOffset = animateOffsetAsState(
        targetValue = endOffset, animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMediumLow
        ), label = ""
    )*/

    Row(modifier = modifier.then(other = Modifier
        .fillMaxWidth()
        .height(intrinsicSize = IntrinsicSize.Max)
        .onSizeChanged {
            barSize.value = it
        }
        .drawBehind {
            when (theme) {
                SlippyTheme.LINE, SlippyTheme.ROUNDED -> {
                    val divHeight: Float =
                        if (theme == SlippyTheme.LINE) 10.0F else size.height / 1.5F

                    val divWidth: Float =
                        if (theme == SlippyTheme.LINE) (size.width / SlippyTab.getTabsSize()) / 2.0F else size.width / SlippyTab.getTabsSize()

                    val xPosition: Float =
                        if (theme == SlippyTheme.LINE) animateStartOffset.value.x + divWidth / 2F else animateStartOffset.value.x
                    val yPosition: Float =
                        if (theme == SlippyTheme.LINE) size.height - divHeight else center.y - (divHeight / 2.0F)

                    val divRadius: Float = if (theme == SlippyTheme.LINE) 5.0F else 100.0F / 1.5F

                    drawRoundRect(
                        color = divColor, topLeft = Offset(
                            x = xPosition, y = yPosition
                        ), size = Size(
                            width = divWidth, height = divHeight
                        ), cornerRadius = CornerRadius(x = divRadius)
                    )
                }

                else -> { /* NO-OP */
                }
            }
        }),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SlippyTab.slippyTabs.forEachIndexed { index: Int, page: SlippyTab ->
            val animateIconColor: Color by animateColorAsState(
                animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing),
                targetValue = colorResource(id = if (currentTab.intValue == index) bar.enabledIconColor else bar.disabledIconColor),
                label = ""
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
                    modifier = Modifier.size(size = dimensionResource(id = bar.iconSize)),
                    tint = animateIconColor,
                    painter = painterResource(id = page.icon),
                    contentDescription = stringResource(
                        id = page.name
                    )
                )

                if (theme == SlippyTheme.NORMAL) {
                    val animateTextColor: Color by animateColorAsState(
                        animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing),
                        targetValue = colorResource(
                            id = if (currentTab.intValue == index) bar.enabledTextColor
                                ?: R.color.enabledTextColor else bar.disabledTextColor
                                ?: R.color.disabledTextColor
                        ),
                        label = ""
                    )

                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = stringResource(id = page.name),
                        maxLines = bar.maxLines,
                        textAlign = bar.textAlign,
                        fontWeight = FontWeight.SemiBold,
                        color = animateTextColor,
                        fontSize = TextUnit(
                            value = dimensionResource(id = bar.textSize ?: R.dimen.textSize).value,
                            type = TextUnitType.Sp
                        )
                    )
                }
            }
        }
    }

}