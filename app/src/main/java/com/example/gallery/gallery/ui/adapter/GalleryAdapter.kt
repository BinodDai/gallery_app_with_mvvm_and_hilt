package com.example.gallery.gallery.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.gallery.R
import com.example.gallery.databinding.GalleryItemBinding
import com.example.gallery.gallery.ui.model.ItemsItem

class GalleryAdapter(
    private var context: Context,
    private var items: List<ItemsItem>,
    private var onItemClicked: (ItemsItem) -> Unit
) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GalleryItemBinding.inflate(inflater, parent, false)
        return GalleryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(items[position], context, onItemClicked)
    }

    fun updateItems(newItems: List<ItemsItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    class GalleryViewHolder(private var binding: GalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: ItemsItem, context: Context, onItemClicked: (ItemsItem) -> Unit) {

            binding.apply {

                if (items.media?.m.isNullOrEmpty()) {
                    image.setImageResource(R.drawable.error_image)
                } else {
                    Glide.with(context)
                        .load(items.media?.m)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.error_image)
                        .into(image)
                }


                title.text = items.title.toString()
                root.setOnClickListener {
                    onItemClicked(items)
                }
            }
        }

    }
}