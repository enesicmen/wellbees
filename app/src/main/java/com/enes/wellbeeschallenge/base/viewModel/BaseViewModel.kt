package com.enes.wellbeeschallenge.base.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel() : ViewModel() {
    private var disposable: CompositeDisposable = CompositeDisposable()

    private var isLoadingWithProgress: MutableLiveData<Boolean>? = null

    private var isLoadingWithLoadingIcon: MutableLiveData<Boolean>? = null

    init {
        isLoadingWithProgress = MutableLiveData()
        isLoadingWithLoadingIcon = MutableLiveData()
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
