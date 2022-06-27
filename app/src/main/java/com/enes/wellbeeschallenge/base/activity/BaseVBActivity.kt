package com.enes.wellbeeschallenge.base.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.enes.wellbeeschallenge.base.viewModel.BaseViewModel

abstract class BaseVBActivity<VB : ViewBinding, VM : BaseViewModel> : BaseActivity() {

    private var viewBinding: VB? = null

    private val mViewBinding: VB
        get() = viewBinding!!

    private lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set ViewModel
        // mViewModel =ViewModelProvider(this, mViewModelFactory).get(setViewModelClass())
        mViewModel = ViewModelProvider(this).get(setViewModelClass())

        // Set ViewBinding
        viewBinding = setViewBinding()
        setContentView(mViewBinding.root)
        initView(savedInstanceState)
    }

    /**
     * Prepare UI Components here
     *
     * @param savedInstanceState
     */
    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun setViewModelClass(): Class<VM>

    abstract fun setViewBinding(): VB

    open fun getViewBinding(): VB {
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
