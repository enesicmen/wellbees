package com.enes.wellbeeschallenge.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.enes.wellbeeschallenge.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
