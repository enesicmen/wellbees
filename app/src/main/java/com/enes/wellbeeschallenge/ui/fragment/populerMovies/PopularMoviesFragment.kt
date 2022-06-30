package com.enes.wellbeeschallenge.ui.fragment.populerMovies

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.enes.wellbeeschallenge.data.Resource
import com.enes.wellbeeschallenge.data.model.MovieModel
import com.enes.wellbeeschallenge.databinding.FragmentPopularMoviesBinding
import com.enes.wellbeeschallenge.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment :
    BaseFragment<FragmentPopularMoviesBinding, PopularMoviesViewModel>() {

    lateinit var mPopularMoviesAdapter: PopularMoviesAdapter

    private var mPopularMoviesList: MutableList<MovieModel> = arrayListOf()

    override fun setViewModelClass() =
        PopularMoviesViewModel::class.java

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

    override fun initLogic() {
        super.initLogic()
        getViewModel().getPopularMovies()
    }

    private fun setPopularMoviesAdapter() {
        mPopularMoviesAdapter = PopularMoviesAdapter(requireActivity(), mPopularMoviesList)
        mPopularMoviesAdapter.setCallBack(object : PopularMoviesAdapter.CallBack {
            override fun onClickItem(position: Int, movieModel: MovieModel) {
                val actionDetail = PopularMoviesFragmentDirections.actionMovieListToMovieDetails(movie = movieModel)
                findNavController().navigate(actionDetail)
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
