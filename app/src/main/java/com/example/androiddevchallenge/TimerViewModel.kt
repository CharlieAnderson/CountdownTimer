package com.example.androiddevchallenge

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel: ViewModel() {
    private lateinit var timer: Timer

    private var _time = MutableLiveData<TimerState>()
    val time: LiveData<TimerState> = _time

    fun setTimer(seconds: Long, minutes: Long) {
        timer = Timer(seconds, minutes)
        _time.postValue(TimerState.STOPPED(timer.getTimeLeft()))
    }

    fun startTimer() {
        timer.startWithState()
    }

    fun pauseTimer() {
        timer.pauseWithState()
    }

    fun stopTimer() {
        timer.cancelWithState()
    }

    inner class Timer(seconds: Long, minutes: Long):
        CountDownTimer((seconds * 1000) + (minutes * 60 * 1000),1000)
    {
        var timeRemaining: Long = (seconds * 1000) + (minutes * 60 * 1000)
        var minutesLeft: Long = minutes
        var secondsLeft: Long = seconds

        fun startWithState() {
            _time.postValue(TimerState.COUNTING(getTimeLeft()))
            start()
        }

        fun cancelWithState() {
            _time.postValue(TimerState.STOPPED(getTimeLeft()))
            cancel()
            timeRemaining = 0
            minutesLeft = 0
            secondsLeft = 0
        }

        fun pauseWithState() {
            _time.postValue(TimerState.PAUSED(getTimeLeft()))
            cancel()
        }

        override fun onTick(millisUntilFinished: Long) {
            timeRemaining = millisUntilFinished
            minutesLeft = (millisUntilFinished/(1000*60));
            secondsLeft = ((millisUntilFinished/1000)-minutesLeft*60);
            _time.postValue(TimerState.COUNTING(getTimeLeft()))
        }

        override fun onFinish() {
            _time.postValue(TimerState.DONE(getTimeLeft()))
        }

        fun getTimeLeft(): String {
            var minutesString: String = ""
            var secondsString: String = ""
            if(minutesLeft < 10) {
                minutesString = "0$minutesLeft"
            } else {
                minutesString = "$minutesLeft"
            }
            if(secondsLeft < 10) {
                secondsString = "0$secondsLeft"
            } else {
                secondsString = "$secondsLeft"
            }
            return "$minutesString:$secondsString"
        }
    }
}