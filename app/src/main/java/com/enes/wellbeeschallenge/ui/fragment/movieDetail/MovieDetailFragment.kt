package com.enes.wellbeeschallenge.ui.fragment.movieDetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.enes.wellbeeschallenge.data.Resource
import com.enes.wellbeeschallenge.data.model.MovieCastModel
import com.enes.wellbeeschallenge.data.model.MovieModel
import com.enes.wellbeeschallenge.databinding.FragmentMovieDetailsBinding
import com.enes.wellbeeschallenge.ui.base.BaseFragment
import com.enes.wellbeeschallenge.ui.ext.loadTmdbImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailsBinding, MovieDetailViewModel>() {

    private lateinit var mMovie: MovieModel
    private lateinit var mCastAdapter: MovieCastAdapter
    private var mCastList = mutableListOf<MovieCastModel>()

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

    override fun initLogic() {
        super.initLogic()
        getViewModel().getMovieDetail(mMovie.id)
    }

    override fun setViewModelClass() =
        MovieDetailViewModel::class.java

    override fun setViewBinding(): FragmentMovieDetailsBinding =
        FragmentMovieDetailsBinding.inflate(layoutInflater)

    override fun readDataFromArguments() {
        super.readDataFromArguments()
        arguments?.let {
            val safeArgs = MovieDetailFragmentArgs.fromBundle(it)
            mMovie = safeArgs.movie
        }
    }

    private fun setData() {
        getViewBinding()?.apply {
            movieBackdropImageView.loadTmdbImage(mMovie.backdropImagePath)
            averageTextView.text = mMovie.average.toString()
            releaseDateTextView.text = mMovie.releaseDate.toString()
            movieOverviewTextView.text = mMovie.overview.toString()
        }
    }

    private fun setCastAdapter() {
        mCastAdapter = MovieCastAdapter(requireActivity(), mCastList)
        mCastAdapter.setCallBack(object : MovieCastAdapter.CallBack {

            override fun onClickItem(position: Int, movieCastModel: MovieCastModel) {
                val actionDetail = MovieDetailFragmentDirections.actionMovieDetailsToPersonDetails(
                    personId = movieCastModel.id,
                    personName = movieCastModel.name
                )
                findNavController().navigate(actionDetail)
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
