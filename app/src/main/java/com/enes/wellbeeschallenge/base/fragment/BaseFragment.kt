package com.enes.wellbeeschallenge.base.fragment

import androidx.fragment.app.Fragment
import com.enes.wellbeeschallenge.base.activity.BaseActivity

abstract class BaseFragment : Fragment() {

    protected fun getBaseActivity() = activity as? BaseActivity
}
