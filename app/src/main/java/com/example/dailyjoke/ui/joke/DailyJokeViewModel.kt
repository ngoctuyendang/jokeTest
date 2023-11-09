package com.example.dailyjoke.ui.joke

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.JokeItem
import com.example.domain.usecase.GetListJokeUseCase
import com.example.domain.usecase.SaveListJokeUseCase
import com.example.domain.usecase.VoteForJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DailyJokeViewModel @Inject constructor(
    private val getListJokeUseCase: GetListJokeUseCase,
    private val voteForJokeUseCase: VoteForJokeUseCase,
    private val saveListJokeUseCase: SaveListJokeUseCase,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _listJoke = MutableLiveData<ArrayList<JokeItem>>()
    val listJoke: LiveData<ArrayList<JokeItem>>
        get() = _listJoke

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    fun getListJoke() {
        compositeDisposable.add(
            getListJokeUseCase.getListJoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    /* onSuccess = */ {
                        Log.d("getListJoke", "Data: $it")
                        addListJokeToDb(it)
                        _listJoke.postValue(it)
                    },
                    /* onError = */ {
                        Log.e("getListJoke", "Error: ${it.message}")
                        _error.postValue(it)
                    }
                )
        )
    }

    private fun addListJokeToDb(listData: List<JokeItem>) {
        compositeDisposable.add(
            saveListJokeUseCase.addListJokeToDb(listData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("addListJokeToDb", "Success")
                    },
                    {
                        Log.e("addListJokeToDb", "Error: ${it.message}")
                    }
                )
        )
    }

    fun voteForJoke(jokeId: Int, isFunny: Boolean) {
        Log.d("voteForJokeUseCase", "Entry --- JokeId: $jokeId")
        compositeDisposable.add(
            voteForJokeUseCase.voteForJoke(jokeId, isFunny)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("voteForJokeUseCase", "Success")
                    },
                    {
                        Log.e("voteForJokeUseCase", "Error: ${it.message}")
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}