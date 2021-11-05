package com.simplaapliko.challenge3.di

import com.simplaapliko.challenge3.data.network.yelp.YelpApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun yelpApi(): YelpApi {
        return YelpApi()
    }
}
