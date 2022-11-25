package com.linuxias.setup_for_testing.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linuxias.setup_for_testing.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var fragmentFactory: MarsPhotoFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}