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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

fun setupTimer(seconds: Long, minutes: Long) {
    val timer = Timer(seconds, minutes)
    
}


// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        DrawAndroidCircleCanvas()
    }
}

@Composable
fun DrawAndroidCircleCanvas() {
    val infiniteTransition = rememberInfiniteTransition()
    val y by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 3000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    )
    val x by infiniteTransition.animateFloat(
        initialValue = 600f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    )
    Canvas(modifier = Modifier
        .clip(CircleShape)
        .width(400.dp)
        .height(400.dp)
        .background(color = Color.DarkGray)
        .border(width = 3.dp, color = Color.Red, shape = CircleShape)
    ) {
        val middle = Offset(size.minDimension / 2, size.minDimension / 2)
        drawCircle(
            color = Color.Cyan,
            radius = 20f,
            center = Offset(x, y)
        )
        drawCircle(
            color = Color.Cyan,
            radius = 40f,
            center = Offset(-150 + x, -750f + y+5f)
        )
        drawCircle(
            color = Color.Cyan,
            radius = 80f,
            center = Offset(150 + x, -500f + y+6f)
        )
        drawCircle(
            color = Color.Cyan,
            radius = 30f,
            center = Offset(250 + x, -1250f + 2*y)
        )
        drawCircle(
            color = Color.Cyan,
            radius = 60f,
            center = Offset(-75 + x, -1000f + 2.5f*y)
        )
    }
    Image(
        painter = painterResource(R.drawable.jetpack),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier.size(200.dp).offset(100.dp, 150.dp)
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
