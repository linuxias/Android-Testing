package com.linuxias.setup_for_testing.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.linuxias.setup_for_testing.adapters.ImageAdapter
import javax.inject.Inject

class MarsPhotoFragmentFactory @Inject constructor (
    private var imageAdapter: ImageAdapter
): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ImagePickFragment::class.java.name -> ImagePickFragment(imageAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}