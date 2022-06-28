package com.enes.wellbeeschallenge.ui.fragment.populerMovies

import android.os.Bundle
import android.util.Log
import android.view.View
import com.enes.wellbeeschallenge.base.fragment.BaseVBFragment
import com.enes.wellbeeschallenge.data.Resource
import com.enes.wellbeeschallenge.data.model.MovieModel
import com.enes.wellbeeschallenge.databinding.FragmentPopularMoviesBinding
import com.enes.wellbeeschallenge.ui.activity.MainActivity
import com.enes.wellbeeschallenge.ui.fragment.adapter.PopularMoviesAdapter
import com.enes.wellbeeschallenge.ui.fragment.movieDetail.MovieDetailPageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesPageFragment :
    BaseVBFragment<FragmentPopularMoviesBinding, PopularMoviesPageViewModel>() {

    lateinit var mPopularMoviesAdapter: PopularMoviesAdapter

    private var mPopularMoviesList: MutableList<MovieModel> = arrayListOf()

    override fun setViewModelClass() =
        PopularMoviesPageViewModel::class.java

    override fun setViewBinding(): FragmentPopularMoviesBinding =
        FragmentPopularMoviesBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {
        setPopularMoviesAdapter()
        getViewModel().movieList.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    getViewBinding()?.progressBar?.visibility = View.VISIBLE
                    Log.i("enesi", "Loading")
                }
                is Resource.Success -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                    // Log.i("enesi", "Success: ${it.data}")
                    setPopularMoviesList(it.data!!)
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

    private fun setPopularMoviesAdapter() {
        mPopularMoviesAdapter = PopularMoviesAdapter(requireActivity(), mPopularMoviesList)
        mPopularMoviesAdapter.setCallBack(object : PopularMoviesAdapter.CallBack {
            override fun onClickItem(position: Int, movieModel: MovieModel) {
                var fragment = MovieDetailPageFragment.newInstance(movieModel)
                var mainActivity = activity as MainActivity
                mainActivity.showFragment(fragment)
            }
        })
        getViewBinding()?.rvPopularMovies?.adapter = mPopularMoviesAdapter
    }

    private fun setPopularMoviesList(popularMovies: List<MovieModel>) {
        mPopularMoviesList.clear()
        mPopularMoviesList.addAll(popularMovies)
        mPopularMoviesAdapter.notifyDataSetChanged()
    }
}
