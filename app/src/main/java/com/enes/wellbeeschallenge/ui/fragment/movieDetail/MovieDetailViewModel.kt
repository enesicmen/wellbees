package com.enes.wellbeeschallenge.ui.fragment.movieDetail

import androidx.lifecycle.ViewModel
import com.enes.wellbeeschallenge.data.NetworkCallback
import com.enes.wellbeeschallenge.data.Resource
import com.enes.wellbeeschallenge.data.model.MovieCastModel
import com.enes.wellbeeschallenge.data.repository.MovieRepository
import com.enes.wellbeeschallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    var movieDetail: SingleLiveEvent<Resource<List<MovieCastModel>>> = SingleLiveEvent()

    fun getMovieDetail(movieId: Int) {
        movieDetail.value = Resource.Loading()
        movieRepository.getMovieDetail(
            movieId,
            object : NetworkCallback<List<MovieCastModel>> {
                override fun onSuccess(data: List<MovieCastModel>) {
                    movieDetail.value = Resource.Success(data)
                }

                override fun onError(message: String) {
                    movieDetail.value = Resource.Error(message)
                }
            }
        )
    }
}
