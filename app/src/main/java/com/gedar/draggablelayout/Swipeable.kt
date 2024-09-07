package com.gedar.draggablelayout

import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableBetween(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
    startAction: @Composable (BoxScope.() -> Unit)? = null,
    endAction: @Composable (BoxScope.() -> Unit)? = null
) {
    val density = LocalDensity.current
    var endItemWidthState by remember { mutableFloatStateOf(0F) }
    var startItemWidthState by remember { mutableFloatStateOf(0F) }
    val initialAnchorsState = DraggableAnchors {
        DragAnchors.Start at -endItemWidthState
        DragAnchors.Center at 0f
        DragAnchors.End at startItemWidthState
    }

    val state by remember {
        mutableStateOf(
            AnchoredDraggableState(
                initialValue = DragAnchors.Center,
                anchors = initialAnchorsState,
                positionalThreshold = { distance: Float -> distance * 0.5f },
                velocityThreshold = { with(density) { 100.dp.toPx() } },
                snapAnimationSpec = tween(),
                decayAnimationSpec = exponentialDecay(),
            )
        )
    }

    Box(
        modifier = modifier
            .clip(RectangleShape)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .anchoredDraggable(state, Orientation.Horizontal)
                .offset {
                    IntOffset(
                        x = +(state
                            .requireOffset()).roundToInt(),
                        y = 0,
                    )
                },
            content = content
        )

        endAction?.let {
            Box(
                Modifier
                    .align(Alignment.CenterEnd)
                    .onGloballyPositioned { coordinates ->
                        endItemWidthState = coordinates.size.width.toFloat()
                        state.updateAnchors(
                            DraggableAnchors {
                                DragAnchors.Start at -(endItemWidthState)
                                DragAnchors.Center at 0f
                                DragAnchors.End at (startItemWidthState)
                            }
                        )
                    }
                    .offset {
                        IntOffset(
                            x = +(state
                                .requireOffset()
                                    + endItemWidthState).roundToInt(),
                            y = 0,
                        )
                    }
            ) {
                endAction()
            }
        }

        startAction?.let {
            Box(
                Modifier
                    .align(Alignment.CenterStart)
                    .onGloballyPositioned { coordinates ->
                        startItemWidthState = coordinates.size.width.toFloat()
                        state.updateAnchors(
                            DraggableAnchors {
                                DragAnchors.Start at -(endItemWidthState)
                                DragAnchors.Center at 0f
                                DragAnchors.End at (startItemWidthState)
                            }
                        )
                    }
                    .offset {
                        IntOffset(
                            x = +(state
                                .requireOffset() - startItemWidthState).roundToInt(),
                            y = 0,
                        )
                    }
            ) {
                startAction()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableAbove(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
    startAction: @Composable (BoxScope.() -> Unit)? = null,
    endAction: @Composable (BoxScope.() -> Unit)? = null
) {
    val density = LocalDensity.current
    var endItemWidthState by remember { mutableFloatStateOf(0F) }
    var startItemWidthState by remember { mutableFloatStateOf(0F) }
    val initialAnchorsState = DraggableAnchors {
        DragAnchors.Start at -endItemWidthState
        DragAnchors.Center at 0f
        DragAnchors.End at startItemWidthState
    }

    val state by remember {
        mutableStateOf(
            AnchoredDraggableState(
                initialValue = DragAnchors.Center,
                anchors = initialAnchorsState,
                positionalThreshold = { distance: Float -> distance * 0.5f },
                velocityThreshold = { with(density) { 100.dp.toPx() } },
                snapAnimationSpec = tween(),
                decayAnimationSpec = exponentialDecay(),
            )
        )
    }

    Box(
        modifier = modifier
            .clip(RectangleShape)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .anchoredDraggable(state, Orientation.Horizontal),
            content = content
        )

        endAction?.let {
            Box(
                Modifier
                    .align(Alignment.CenterEnd)
                    .onGloballyPositioned { coordinates ->
                        endItemWidthState = coordinates.size.width.toFloat()
                        state.updateAnchors(
                            DraggableAnchors {
                                DragAnchors.Start at -(endItemWidthState)
                                DragAnchors.Center at 0f
                                DragAnchors.End at (startItemWidthState)
                            }
                        )
                    }
                    .offset {
                        IntOffset(
                            x = +(state
                                .requireOffset()
                                    + endItemWidthState).roundToInt(),
                            y = 0,
                        )
                    }
            ) {
                endAction()
            }
        }

        startAction?.let {
            Box(
                Modifier
                    .align(Alignment.CenterStart)
                    .onGloballyPositioned { coordinates ->
                        startItemWidthState = coordinates.size.width.toFloat()
                        state.updateAnchors(
                            DraggableAnchors {
                                DragAnchors.Start at -(endItemWidthState)
                                DragAnchors.Center at 0f
                                DragAnchors.End at (startItemWidthState)
                            }
                        )
                    }
                    .offset {
                        IntOffset(
                            x = +(state
                                .requireOffset() - startItemWidthState).roundToInt(),
                            y = 0,
                        )
                    }
            ) {
                startAction()
            }
        }
    }
}

enum class DragAnchors {
    Start,
    Center,
    End,
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableBehind(
    modifier: Modifier,
    content: @Composable BoxScope.() -> Unit,
    startAction: @Composable (BoxScope.() -> Unit)? = null,
    endAction: @Composable (BoxScope.() -> Unit)? = null
) {
    val density = LocalDensity.current
    var endItemWidthState by remember { mutableFloatStateOf(0F) }
    var startItemWidthState by remember { mutableFloatStateOf(0F) }
    val initialAnchorsState = DraggableAnchors {
        DragAnchors.Start at -endItemWidthState
        DragAnchors.Center at 0f
        DragAnchors.End at startItemWidthState
    }

    val state by remember {
        mutableStateOf(
            AnchoredDraggableState(
                initialValue = DragAnchors.Center,
                anchors = initialAnchorsState,
                positionalThreshold = { distance: Float -> distance * 0.5f },
                velocityThreshold = { with(density) { 100.dp.toPx() } },
                snapAnimationSpec = tween(),
                decayAnimationSpec = exponentialDecay(),
            )
        )
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RectangleShape)
    ) {

        endAction?.let {
            Box(
                Modifier
                    .align(Alignment.CenterEnd)
                    .onGloballyPositioned { coordinates ->
                        startItemWidthState = coordinates.size.width.toFloat()
                        state.updateAnchors(
                            DraggableAnchors {
                                DragAnchors.Start at -(endItemWidthState)
                                DragAnchors.Center at 0f
                                DragAnchors.End at (startItemWidthState)
                            }
                        )
                    }
            ) {
                endAction()
            }
        }

        startAction?.let {
            Box(
                Modifier
                    .onGloballyPositioned { coordinates ->
                        endItemWidthState = coordinates.size.width.toFloat()
                        state.updateAnchors(
                            DraggableAnchors {
                                DragAnchors.Start at -(endItemWidthState)
                                DragAnchors.Center at 0f
                                DragAnchors.End at (startItemWidthState)
                            }
                        )
                    }
            ) {
                startAction()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset {
                    IntOffset(
                        x = state
                            .requireOffset()
                            .roundToInt(),
                        y = 0,
                    )
                }
                .anchoredDraggable(state, Orientation.Horizontal),
            content = content
        )
    }
}

@Preview
@Composable
fun DraggablePreview() {

    val mainModifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
    val textPadding = Modifier.padding(5.dp)

    @Composable
    fun ContentTest() {
        Box(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(color = Color.Gray)
        ) {
            Text("center", modifier = Modifier.align(Alignment.Center))
        }
    }

    @Composable
    fun StartTest() {
        Box(
            Modifier
                .width(50.dp)
                .fillMaxHeight()
                .background(color = Color.Green)
        ) {
            Text("left", modifier = Modifier.align(Alignment.Center))
        }
    }

    @Composable
    fun EndTest() {
        Box(
            Modifier
                .width(50.dp)
                .fillMaxHeight()
                .background(color = Color.Red)
        ) {
            Text("right", modifier = Modifier.align(Alignment.Center))
        }
    }

    Column {
        Text("draggable between", textPadding)
        DraggableBetween(
            modifier = mainModifier,
            content = { ContentTest() },
            startAction = { StartTest() },
            endAction = { EndTest() }
        )
        Text("draggable above", textPadding)
        DraggableAbove(
            modifier = mainModifier,
            content = { ContentTest() },
            startAction = { StartTest() },
            endAction = { EndTest() }
        )
        Text("draggable behind", textPadding)
        DraggableBehind(
            modifier = mainModifier,
            content = { ContentTest() },
            startAction = { StartTest() },
            endAction = { EndTest() }
        )
    }
}