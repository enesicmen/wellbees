package com.enes.wellbeeschallenge.ui.fragment.populerMovies

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enes.wellbeeschallenge.R
import com.enes.wellbeeschallenge.data.model.MovieModel
import com.enes.wellbeeschallenge.databinding.RowPopularMoviesBinding
import com.enes.wellbeeschallenge.ui.ext.loadTmdbImage

class PopularMoviesAdapter(
    activity: Activity,
    popularMovies: List<MovieModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val RES_ITEM_ROW: Int = R.layout.row_popular_movies
    private var mInflater: LayoutInflater? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mActivity: Activity? = null
    private var mCallback: CallBack? = null
    private var mPopularMoviesList: List<MovieModel>

    init {
        mActivity = activity
        mInflater = LayoutInflater.from(mActivity)
        mLayoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        mPopularMoviesList = popularMovies
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.layoutManager = mLayoutManager
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val item: View = mInflater?.inflate(RES_ITEM_ROW, parent, false)!!
        return PopularMoviesViewHolder(item)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mPopularMovies: MovieModel =
            mPopularMoviesList[position]

        (holder as PopularMoviesViewHolder).onBind(
            position,
            mPopularMovies
        )
    }

    override fun getItemCount(): Int {
        return mPopularMoviesList.size
    }

    fun setCallBack(callBack: CallBack?) {
        mCallback = callBack
    }

    inner class PopularMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RowPopularMoviesBinding.bind(itemView)

        fun onBind(position: Int, movieModel: MovieModel) {

            binding.ivMovie.loadTmdbImage(movieModel.posterImagePath)

            binding.tvTitle.text = movieModel.title
            binding.tvAvarage.text = movieModel.average.toString()
            binding.tvReleaseDate.text = movieModel.releaseDate

            binding.root.setOnClickListener(
                View.OnClickListener {
                    mCallback?.onClickItem(position, movieModel)
                }
            )
        }
    }

    interface CallBack {
        fun onClickItem(position: Int, movieModel: MovieModel)
    }
}
