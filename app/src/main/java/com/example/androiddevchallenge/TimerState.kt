package com.example.androiddevchallenge

sealed class TimerState(open var timeLeft: String = "") {

    data class COUNTING(override var timeLeft: String): TimerState(timeLeft)

    data class PAUSED(override var timeLeft: String): TimerState(timeLeft)

    data class STOPPED(override var timeLeft: String): TimerState(timeLeft)

    data class DONE(override var timeLeft: String): TimerState(timeLeft)
}
