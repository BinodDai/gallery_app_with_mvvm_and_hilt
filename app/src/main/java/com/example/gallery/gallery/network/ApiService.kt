package com.example.gallery.gallery.network

import com.example.gallery.gallery.ui.model.FlickrResponse
import retrofit2.http.GET

interface ApiService {
    @GET("photos_public.gne?format=json&nojsoncallback=1")
    suspend fun getPublicPhotos(): FlickrResponse
}