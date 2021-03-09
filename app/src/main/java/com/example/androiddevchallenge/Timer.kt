package com.example.androiddevchallenge

import android.os.CountDownTimer

class Timer(seconds: Long, minutes: Long): CountDownTimer(
    (seconds * 1000)
            + (minutes * 60 * 1000),
    1000
) {
    override fun onTick(millisUntilFinished: Long) {
        TODO("Not yet implemented")
    }

    override fun onFinish() {
        TODO("Not yet implemented")
    }
}