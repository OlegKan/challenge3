package com.simplaapliko.challenge3.data.datasource.business

import com.simplaapliko.challenge3.data.datasource.BusinessDataSource
import com.simplaapliko.challenge3.data.network.yelp.Business
import com.simplaapliko.challenge3.data.network.yelp.BusinessResponse
import com.simplaapliko.challenge3.data.network.yelp.YelpApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BusinessRemoteDataSource(
    private val yelpApi: YelpApi,
    private val cache: BusinessCache
) : BusinessDataSource {
    override fun getBusinesses(
        query: String,
        categories: String?,
        latitude: Double,
        longitude: Double,
        openNow: Boolean,
    ): Flow<List<BusinessEntity>> {
        return flow {
            val response =
                yelpApi.searchBusiness(
                    query = query,
                    categories = categories,
                    latitude = latitude,
                    longitude = longitude,
                    openNow = openNow
                )

            val entities = response.toEntities(query)
            cache.put(
                query = query,
                latitude = latitude,
                longitude = longitude,
                entities = entities
            )
            emit(entities)
        }
    }
}

private fun BusinessResponse.toEntities(query: String): List<BusinessEntity> {
    return businesses.orEmpty().map { it.toEntity(query) }
}

private fun Business.toEntity(query: String): BusinessEntity {
    return BusinessEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        url = url,
        phone = phone,
        distance = distance,
        type = query
    )
}
