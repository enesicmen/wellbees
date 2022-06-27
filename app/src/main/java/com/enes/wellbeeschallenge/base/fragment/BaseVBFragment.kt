package com.enes.wellbeeschallenge.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.enes.wellbeeschallenge.base.viewModel.BaseViewModel

abstract class BaseVBFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFragment() {

    private var cachedView: View? = null

    private var viewBinding: VB? = null

    private val mViewBinding: VB?
        get() = viewBinding

    private lateinit var mViewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (cachedView == null) {
            mViewModel = ViewModelProvider(this).get(setViewModelClass())

            viewBinding = setViewBinding()
            cachedView = mViewBinding?.root
            initView(savedInstanceState)
        }
        return cachedView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun setViewModelClass(): Class<VM>

    abstract fun setViewBinding(): VB

    open fun getViewBinding(): VB? {
        return mViewBinding
    }

    open fun getViewModel(): VM {
        return mViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}
