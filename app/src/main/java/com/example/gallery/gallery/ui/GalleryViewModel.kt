package com.example.gallery.gallery.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gallery.gallery.ui.model.FlickrResponse
import com.example.gallery.gallery.ui.model.ItemsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: GalleryRepository) :
    ViewModel() {

    private val _flickrResponse = MutableLiveData<FlickrResponse>()
    val flickrResponse: LiveData<FlickrResponse> get() = _flickrResponse

    var selectedItems=MutableLiveData<ItemsItem>()



    fun fetchPhotos() {
        viewModelScope.launch {
            try {
                val response = repository.getFlickrPhotos()
                _flickrResponse.value = response

            } catch (e: Exception) {

            }
        }

    }
}