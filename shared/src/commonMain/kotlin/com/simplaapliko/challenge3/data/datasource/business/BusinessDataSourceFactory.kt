package com.simplaapliko.challenge3.data.datasource.business

import com.simplaapliko.challenge3.data.datasource.BusinessDataSource

class BusinessDataSourceFactory(
    private val remoteDataSource: BusinessDataSource,
    private val localCache: BusinessCache,
) {

    fun getDataSource(
        query: String,
        latitude: Double,
        longitude: Double,
    ): BusinessDataSource {
        return when {
            //TODO
            // isCacheValid(
            //     localCache,
            //     query = query,
            //     latitude = latitude,
            //     longitude = longitude
            // ) -> localDataSource
            else -> remoteDataSource
        }
    }

    fun getRemoteDataSource(): BusinessDataSource {
        return remoteDataSource
    }

    private fun isCacheValid(
        cache: BusinessCache,
        query: String,
        latitude: Double,
        longitude: Double,
    ): Boolean {
        return cache.get(query = query, latitude = latitude, longitude = longitude).isNotEmpty()
    }

    fun clearCache() {
        localCache.clear()
    }
}
