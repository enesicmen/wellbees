package com.enes.wellbeeschallenge.ui.fragment.populerMovies

import android.os.Bundle
import android.util.Log
import android.view.View
import com.enes.wellbeeschallenge.base.fragment.BaseVBFragment
import com.enes.wellbeeschallenge.data.Resource
import com.enes.wellbeeschallenge.databinding.FragmentPopularMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesPageFragment :
    BaseVBFragment<FragmentPopularMoviesBinding, PopularMoviesPageViewModel>() {

    override fun setViewModelClass() =
        PopularMoviesPageViewModel::class.java

    override fun setViewBinding(): FragmentPopularMoviesBinding =
        FragmentPopularMoviesBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {
        getViewModel().movieList.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    getViewBinding()?.progressBar?.visibility = View.VISIBLE
                    Log.i("enesi", "Loading")
                }
                is Resource.Success -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                    Log.i("enesi", "Success: ${it.data}")
                    // showAdDetails(it.data!!)
                }
                is Resource.Error -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                    Log.i("enesi", "Error: ${it.error}")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getViewModel().getPopularMovies()
    }
}
