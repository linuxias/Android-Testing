package com.linuxias.setup_for_testing.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.linuxias.setup_for_testing.R
import com.linuxias.setup_for_testing.databinding.FragmentAddMarsPhotoItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMarsPhotoItemFragment : Fragment(R.layout.fragment_add_mars_photo_item) {
    private val viewModel by viewModels<MarsPhotoViewModel>()

    private var _binding: FragmentAddMarsPhotoItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddMarsPhotoItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivMarsImage.setOnClickListener {
            findNavController().navigate(
                AddMarsPhotoItemFragmentDirections.actionAddMarsPhotoFragmentToImagePickFragment()
            )
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setCurImageUrl("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}