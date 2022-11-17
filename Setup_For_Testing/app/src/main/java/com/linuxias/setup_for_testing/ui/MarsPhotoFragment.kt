package com.linuxias.setup_for_testing.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.linuxias.setup_for_testing.R
import com.linuxias.setup_for_testing.databinding.FragmentMarsPhotoBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MarsPhotoFragment : Fragment(R.layout.fragment_mars_photo) {
    private val viewModel by viewModels<MarsPhotoViewModel>()

    private var _binding: FragmentMarsPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarsPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddMarsPhotoItem.setOnClickListener {
            findNavController().navigate(
                R.id.action_marsPhotoFragment_to_addMarsPhotoFragment
            )
        }
    }
}