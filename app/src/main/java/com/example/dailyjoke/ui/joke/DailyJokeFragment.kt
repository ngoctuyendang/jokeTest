package com.example.dailyjoke.ui.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dailyjoke.databinding.FragmentDailyJokeBinding

class DailyJokeFragment : Fragment() {

    lateinit var mBinding: FragmentDailyJokeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentDailyJokeBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}