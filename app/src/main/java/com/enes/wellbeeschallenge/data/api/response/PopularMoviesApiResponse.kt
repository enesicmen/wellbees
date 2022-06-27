
import com.enes.wellbeeschallenge.data.model.MovieModel

data class PopularMoviesApiResponse(
    val page: Int = 0,
    val results: List<MovieModel> = listOf(),
)
