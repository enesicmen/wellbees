package com.enes.wellbeeschallenge.ui.fragment.populerMovies

import com.enes.wellbeeschallenge.base.viewModel.BaseViewModel
import com.enes.wellbeeschallenge.data.NetworkCallback
import com.enes.wellbeeschallenge.data.Resource
import com.enes.wellbeeschallenge.data.model.MovieModel
import com.enes.wellbeeschallenge.data.repository.MovieRepository
import com.enes.wellbeeschallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesPageViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    var movieList: SingleLiveEvent<Resource<List<MovieModel>>> = SingleLiveEvent()

    fun getPopularMovies() {
        movieList.value = Resource.Loading()
        movieRepository.getPopularMovies(object : NetworkCallback<List<MovieModel>> {
            override fun onSuccess(data: List<MovieModel>) {
                movieList.value = Resource.Success(data)
            }
            override fun onError(message: String) {
                movieList.value = Resource.Error(message)
            }
        })
    }
}
