package com.enes.wellbeeschallenge.ui.fragment.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enes.wellbeeschallenge.R
import com.enes.wellbeeschallenge.data.model.MovieCastModel
import com.enes.wellbeeschallenge.databinding.ItemRowCastBinding
import com.enes.wellbeeschallenge.ui.ext.loadTmdbImage

class MovieCastAdapter(
    activity: Activity,
    castList: List<MovieCastModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val RES_ITEM_ROW: Int = R.layout.item_row_cast
    private var mInflater: LayoutInflater? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mActivity: Activity? = null
    private var mCallback: CallBack? = null
    private var mMoviecastList: List<MovieCastModel>

    init {
        mActivity = activity
        mInflater = LayoutInflater.from(mActivity)
        mLayoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        mMoviecastList = castList
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.layoutManager = mLayoutManager
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val item: View = mInflater?.inflate(RES_ITEM_ROW, parent, false)!!
        return MovieDetailViewHolder(item)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mMovieDetail: MovieCastModel =
            mMoviecastList[position]

        (holder as MovieDetailViewHolder).onbind(
            position,
            mMovieDetail
        )
    }

    override fun getItemCount(): Int {
        return mMoviecastList.size
    }

    fun setCallBack(callBack: CallBack?) {
        mCallback = callBack
    }

    inner class MovieDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemRowCastBinding.bind(itemView)

        init {
            setFontSize()
        }

        private fun setFontSize() {
        }

        fun onbind(position: Int, movieCastModel: MovieCastModel) {
            binding.ivProfile.loadTmdbImage(movieCastModel.imagePath)
            binding.tvArtistName.text = movieCastModel.name
            binding.tvCharacterName.text = movieCastModel.character
            binding.root.setOnClickListener(
                View.OnClickListener {
                    mCallback?.onClickItem(position, movieCastModel)
                }
            )
        }
    }

    interface CallBack {
        fun onClickItem(position: Int, movieCastModel: MovieCastModel)
    }
}
