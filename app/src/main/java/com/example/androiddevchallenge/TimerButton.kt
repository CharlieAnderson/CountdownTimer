package com.example.androiddevchallenge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerButton(
    viewModel: TimerViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
    var time = viewModel.time.observeAsState()
        Button(
            onClick = {
                if (time.value is TimerState.COUNTING) {
                    viewModel.pauseTimer()
                } else {
                    viewModel.startTimer()
                }
            }, shape = RoundedCornerShape(3.dp),
            colors = if(time.value is TimerState.COUNTING) {
                ButtonDefaults.buttonColors(backgroundColor = Color.Magenta)
            } else {
                ButtonDefaults.buttonColors(backgroundColor = Color.Green)
            },
            content = {
                val text = if (time.value is TimerState.COUNTING) {
                    "Pause"
                } else {
                    "Start"
                }
                Text(
                    text,
                    fontSize = 24.sp
                )
            }
        )
    }
}

@Composable
fun StopButton(
    viewModel: TimerViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                viewModel.stopTimer()
                viewModel.setTimer(30, 1)
            }, shape = RoundedCornerShape(3.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            content = {
                Text(
                    "Stop",
                    fontSize = 24.sp
                )
            }
        )
    }
}