package com.example.dailyjoke.ui.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dailyjoke.databinding.FragmentDailyJokeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyJokeFragment : Fragment() {

    private lateinit var mBinding: FragmentDailyJokeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentDailyJokeBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}