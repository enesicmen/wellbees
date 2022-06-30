package com.enes.wellbeeschallenge.ui.fragment.populerMovies

import androidx.lifecycle.ViewModel
import com.enes.wellbeeschallenge.data.NetworkCallback
import com.enes.wellbeeschallenge.data.Resource
import com.enes.wellbeeschallenge.data.model.MovieModel
import com.enes.wellbeeschallenge.data.repository.MovieRepository
import com.enes.wellbeeschallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

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
