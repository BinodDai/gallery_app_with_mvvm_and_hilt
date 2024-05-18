package com.example.gallery.gallery.ui.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.gallery.R
import com.example.gallery.databinding.FragmentImageDetailsBinding
import com.example.gallery.gallery.ui.GalleryViewModel
import com.example.gallery.gallery.ui.model.ItemsItem

class ImageDetailsFragment : Fragment() {
    private lateinit var binding: FragmentImageDetailsBinding
    private val viewModel: GalleryViewModel by activityViewModels()
    private lateinit var selectedItem: ItemsItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        initClickListener()
    }

    private fun initClickListener() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.download.setOnClickListener {


        }
    }

    private fun setUpUi() {

        selectedItem = viewModel.selectedItems.value!!

        binding.apply {

            if (selectedItem.media?.m == null) {
                image.setImageResource(R.drawable.error_image)
            } else {
                Glide.with(requireContext())
                    .load(selectedItem.media?.m)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.error_image)
                    .into(image)
            }


            tvTitle.text = selectedItem.title
            desc.text = Html.fromHtml(selectedItem.description)
            dateTaken.text = selectedItem.dateTaken
        }
    }

}