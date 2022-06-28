package com.enes.wellbeeschallenge.ui.fragment.movieDetail

import android.os.Bundle
import android.view.View
import com.enes.wellbeeschallenge.base.fragment.BaseVBFragment
import com.enes.wellbeeschallenge.data.Resource
import com.enes.wellbeeschallenge.data.model.MovieCastModel
import com.enes.wellbeeschallenge.data.model.MovieModel
import com.enes.wellbeeschallenge.databinding.FragmentMovieDetailBinding
import com.enes.wellbeeschallenge.ui.activity.MainActivity
import com.enes.wellbeeschallenge.ui.ext.loadTmdbImage
import com.enes.wellbeeschallenge.ui.fragment.adapter.MovieCastAdapter
import com.enes.wellbeeschallenge.ui.fragment.adapter.PopularMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailPageFragment :
    BaseVBFragment<FragmentMovieDetailBinding, MovieDetailPageViewModel>() {

    private lateinit var mMovie: MovieModel
    private lateinit var mCastAdapter: MovieCastAdapter
    private var mCastList = mutableListOf<MovieCastModel>()

    companion object {
        fun newInstance(
            movie: MovieModel
        ): MovieDetailPageFragment {
            val args = Bundle()
            var fragment = MovieDetailPageFragment()
            fragment.mMovie = movie
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        setCastAdapter()
        getViewModel().movieDetail.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    getViewBinding()?.progressBar?.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                    setCastList(it.data!!)
                    setData()
                }
                is Resource.Error -> {
                    getViewBinding()?.progressBar?.visibility = View.GONE
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getViewModel().getMovieDetail(mMovie.id)
    }

    override fun setViewModelClass() =
        MovieDetailPageViewModel::class.java

    override fun setViewBinding(): FragmentMovieDetailBinding =
        FragmentMovieDetailBinding.inflate(layoutInflater)

    private fun setData() {
        getViewBinding()?.movieBackdropImageView.loadTmdbImage(mMovie.backdropImagePath)
        getViewBinding()?.averageTextView?.text = mMovie.average.toString()
        getViewBinding()?.releaseDateTextView?.text = mMovie.releaseDate.toString()
        getViewBinding()?.movieOverviewTextView?.text = mMovie.overview.toString()
    }

    private fun setCastAdapter() {
        mCastAdapter = MovieCastAdapter(requireActivity(),mCastList)
        mCastAdapter.setCallBack(object : MovieCastAdapter.CallBack {

            override fun onClickItem(position: Int, movieCastModel: MovieCastModel) {
            }
        })
        getViewBinding()?.rvCast?.adapter = mCastAdapter
    }

    private fun setCastList(castList: List<MovieCastModel>) {
        mCastList.clear()
        mCastList.addAll(castList)
        mCastAdapter.notifyDataSetChanged()
    }
}
