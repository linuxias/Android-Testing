package com.linuxias.setup_for_testing.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linuxias.setup_for_testing.R
import com.linuxias.setup_for_testing.databinding.FragmentAddMarsPhotoItemBinding
import com.linuxias.setup_for_testing.databinding.FragmentImagePickBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagePickFragment : Fragment(R.layout.fragment_image_pick) {
    private var _binding: FragmentImagePickBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImagePickBinding.inflate(inflater, container, false)
        return binding.root
    }
}