package com.linuxias.setup_for_testing.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.linuxias.setup_for_testing.R
import com.linuxias.setup_for_testing.adapters.ImageAdapter
import com.linuxias.setup_for_testing.databinding.FragmentImagePickBinding
import com.linuxias.setup_for_testing.other.Constants.GRID_SPAN_COUNT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImagePickFragment: Fragment(R.layout.fragment_image_pick) {
    @Inject
    lateinit var imageAdapter: ImageAdapter

    private var _binding: FragmentImagePickBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MarsPhotoViewModel>()

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImagePickBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        imageAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setCurImageUrl(it)
        }
    }

    private fun setupRecyclerView() {
        binding.rvImages.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        }
    }
}