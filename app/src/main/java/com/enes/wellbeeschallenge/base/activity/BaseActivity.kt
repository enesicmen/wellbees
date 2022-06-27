package com.enes.wellbeeschallenge.base.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    val activity: Activity
        get() = this
}
