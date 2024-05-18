package com.example.gallery.gallery.ui

import com.example.gallery.gallery.network.ApiService
import com.example.gallery.gallery.ui.model.FlickrResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getFlickrPhotos(): FlickrResponse {
        return apiService.getPublicPhotos()
    }
}