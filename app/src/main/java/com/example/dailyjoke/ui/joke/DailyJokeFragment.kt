package com.example.dailyjoke.ui.joke

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dailyjoke.R
import com.example.dailyjoke.databinding.FragmentDailyJokeBinding
import com.example.domain.models.JokeItem
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyJokeFragment : Fragment() {

    private lateinit var mBinding: FragmentDailyJokeBinding
    private val mViewModel: DailyJokeViewModel by viewModels()
    private var count = 0
    private var selectedItem: JokeItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentDailyJokeBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupEvent()
    }

    private fun setupEvent() {
        mBinding.funnyBtn.setOnClickListener {
            selectedItem?.let {
                mViewModel.voteForJoke(it.id, true)
                updateViewTemp()
            }
        }
        mBinding.notFunnyBtn.setOnClickListener {
            selectedItem?.let {
                mViewModel.voteForJoke(it.id, false)
                updateViewTemp()
            }
        }
    }

    private fun updateViewTemp() {
        count++
        mViewModel.listJoke.value?.let { list ->
            if (count < list.size) {
                selectedItem = list[count]
                mBinding.jokeContent.text = selectedItem?.content
            } else {
                updateEmptyData()
            }
        }
    }

    private fun updateEmptyData() {
        mBinding.jokeContent.text = requireContext().getString(R.string.empty_data)
        mBinding.jokeContent.gravity = Gravity.FILL
        mBinding.funnyBtn.visibility = View.GONE
        mBinding.notFunnyBtn.visibility = View.GONE
    }

    private fun setupUI() {
        mViewModel.getListJoke()
        mViewModel.listJoke.observe(viewLifecycleOwner) {
            Log.d("DailyJokeFragment.setupUI", "Data: $it")
            if (it.isNotEmpty()) {
                selectedItem = it[count]
                selectedItem?.let { item ->
                    mBinding.jokeContent.text = item.content
                }
            }
        }

        mViewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(mBinding.root, "${it.message}", Snackbar.LENGTH_SHORT).show()
        }
    }
}