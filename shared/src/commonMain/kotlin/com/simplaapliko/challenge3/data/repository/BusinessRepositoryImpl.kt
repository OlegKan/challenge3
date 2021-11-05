package com.simplaapliko.challenge3.data.repository

import com.simplaapliko.challenge3.data.datasource.business.BusinessDataSourceFactory
import com.simplaapliko.challenge3.data.datasource.business.BusinessEntity
import com.simplaapliko.challenge3.domain.business.BusinessModel
import com.simplaapliko.challenge3.domain.business.BusinessRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.math.roundToInt

class BusinessRepositoryImpl(
    private val dataSourceFactory: BusinessDataSourceFactory
) : BusinessRepository {

    override fun getBusinesses(
        query: String,
        latitude: Double,
        longitude: Double,
        openNow: Boolean,
    ): Flow<List<BusinessModel>> {
        return dataSourceFactory.getDataSource(
            query = query,
            latitude = latitude,
            longitude = longitude,
        ).getBusinesses(
            query = query,
            categories = null,
            latitude = latitude,
            longitude = longitude,
            openNow = openNow
        )
            .map { list -> list.filter { it.name != null } }
            .map { list -> list.map { it.toModel() } }
    }
}

private fun BusinessEntity.toModel(): BusinessModel {
    return BusinessModel(
        id = id,
        name = name ?: "",
        imageUrl = imageUrl,
        url = url,
        phone = phone,
        distance = distance,
        formattedDistance = distance.formatDistance(),
        type = type,
    )
}

private fun Double.formatDistance(): String {
    return "${(this / 1609).roundToDecimals(2)} mi"
}

private fun Double.roundToDecimals(decimals: Int): Float {
    var dotAt = 1
    repeat(decimals) { dotAt *= 10 }
    val roundedValue = (this * dotAt).roundToInt()
    return (roundedValue / dotAt) + (roundedValue % dotAt).toFloat() / dotAt
}
