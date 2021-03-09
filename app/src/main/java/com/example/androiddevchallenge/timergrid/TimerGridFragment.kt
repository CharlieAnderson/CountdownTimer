package com.example.androiddevchallenge.timergrid

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androiddevchallenge.R

class TimerGridFragment : Fragment() {

    companion object {
        fun newInstance() = TimerGridFragment()
    }

    private lateinit var viewModel: TimerGridViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.timer_grid_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TimerGridViewModel::class.java)
        // TODO: Use the ViewModel
    }

}