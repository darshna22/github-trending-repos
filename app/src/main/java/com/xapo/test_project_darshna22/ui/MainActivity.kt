package com.xapo.test_project_darshna22.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xapo.test_project_darshna2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}