package com.example.gallery.gallery.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.R
import com.example.gallery.databinding.FragmentImageListBinding
import com.example.gallery.gallery.ui.GalleryViewModel
import com.example.gallery.gallery.ui.adapter.GalleryAdapter

class ImageListFragment : Fragment() {

    private lateinit var binding: FragmentImageListBinding
    private lateinit var navController: NavController
    private lateinit var galleryAdapter: GalleryAdapter
    private val viewModel: GalleryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        setUpRecycleView()
        observeFlickrResponse()
        initClickListener()

    }

    private fun initClickListener() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }



    private fun observeFlickrResponse() {
        viewModel.flickrResponse.observe(viewLifecycleOwner) { flickerResponse ->
            flickerResponse.items?.let { galleryAdapter.updateItems(it) }
        }

        viewModel.fetchPhotos()
    }

    private fun setUpRecycleView() {
        galleryAdapter = GalleryAdapter(requireContext(), emptyList()) { item ->
            viewModel.selectedItems.value = item
            navController.navigate(R.id.imageDetailsFragment)
        }
        binding.imageRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.imageRv.adapter = galleryAdapter
    }

}