/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.rotationMatrix
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    lateinit var image: Bitmap
    private val viewModel by viewModels<TimerViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        image = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.jetpack)
        viewModel.setTimer(30, 1)
        setContent {
            MyTheme {
                MyApp(viewModel)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(viewModel: TimerViewModel) {
    val time by viewModel.time.observeAsState()
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Spacer(modifier = Modifier.height(30.dp))
            DrawAndroidCircleCanvas(time)
            Spacer(modifier = Modifier.height(30.dp))
            drawTimer(time = time?.timeLeft ?: "Error")
            Spacer(modifier = Modifier.height(20.dp))
            TimerButton(viewModel = viewModel)
            Spacer(modifier = Modifier.height(20.dp))
            StopButton(viewModel = viewModel)
        }
    }
}


@Composable
fun drawJetpackAndroid() {

}


@Composable
fun DrawAndroidCircleCanvas(time: TimerState?) {
    if(time == null) return

    when(time) {
        is TimerState.PAUSED -> DrawJetpackHovering()
        is TimerState.COUNTING -> DrawJetpackFlying()
        is TimerState.DONE -> DrawJetpackStopped()
        is TimerState.STOPPED -> DrawJetpackStopped()
    }
}


@Composable
fun DrawJetpackFlying() {
    val infiniteTransition = rememberInfiniteTransition()

    val y by infiniteTransition.animateFloat(
        initialValue = -100f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    )
    val x by infiniteTransition.animateFloat(
        initialValue = 100f,
        targetValue = -1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    )

    Card(modifier = Modifier
        .size(390.dp)
        .padding(12.dp),
        shape = CircleShape,
        backgroundColor = Color.Blue,
        elevation = 4.dp) {

        Image(
            painter = painterResource(R.drawable.whitecloud_large),
            contentScale = ContentScale.Inside,
            contentDescription = null,
            modifier = Modifier.offset(x.dp+260.dp, y.dp-100.dp)
        )
        Image(
            painter = painterResource(R.drawable.whitecloud_large),
            contentScale = ContentScale.Inside,
            contentDescription = null,
            modifier = Modifier.offset(120.dp+x.dp, 120.dp+y.dp)
        )


        Image(
            painter = painterResource(R.drawable.jetpack),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .scale(0.5f)
        )

        Image(
            painter = painterResource(R.drawable.whitecloud_large),
            contentScale = ContentScale.Inside,
            contentDescription = null,
            modifier = Modifier.offset(330.dp+x.dp, 10.dp+y.dp)
        )

        Image(
            painter = painterResource(R.drawable.whitecloud_large),
            contentScale = ContentScale.Inside,
            contentDescription = null,
            modifier = Modifier.offset(x.dp, y.dp)
        )

        Image(
            painter = painterResource(R.drawable.whitecloud_large),
            contentScale = ContentScale.Inside,
            contentDescription = null,
            modifier = Modifier.offset(40.dp + x.dp, y.dp-150.dp)
        )

        CircularProgressIndicator(
            modifier = Modifier.size(390.dp),
            color = Color.Green,
            strokeWidth = 6.dp
        )

        CircularProgressIndicator(
            modifier = Modifier.size(380.dp),
            color = Color.Cyan,
            strokeWidth = 3.dp
        )
    }
}

@Composable
fun DrawJetpackHovering() {
    val infiniteTransition = rememberInfiniteTransition()

    val hoverY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 75f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Card(modifier = Modifier
        .size(390.dp)
        .padding(12.dp),
        shape = CircleShape,
        backgroundColor = Color.Blue,
        elevation = 4.dp) {

        Image(
            painter = painterResource(R.drawable.jetpack),
            contentScale = ContentScale.Crop,
            alignment = Alignment.BottomStart,
            contentDescription = null,
            modifier = Modifier
                .scale(0.5f)
                .offset(0.dp, hoverY.dp)

        )
    }
}

@Composable
fun DrawJetpackStopped() {
    Card(modifier = Modifier
        .size(390.dp)
        .padding(12.dp),
        shape = CircleShape,
        backgroundColor = Color.Blue,
        elevation = 4.dp) {
        Image(
            painter = painterResource(R.drawable.jetpack),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .scale(0.5f)
                .offset(0.dp, 300.dp)
        )
    }
}

@Composable
fun drawTimer(time: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = AnnotatedString(time),
            fontSize = 64.sp,
            textAlign = TextAlign.Center
        )
    }

}