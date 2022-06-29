package com.enes.wellbeeschallenge.ui.fragment.populerMovies

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
                }
                is Resource.Success -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                    setPopularMoviesList(it.data!!)
                }
                is Resource.Error -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
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

    private fun searchMovie() {
        getViewBinding()?.etSearch?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }
        }
        )
    }
}
