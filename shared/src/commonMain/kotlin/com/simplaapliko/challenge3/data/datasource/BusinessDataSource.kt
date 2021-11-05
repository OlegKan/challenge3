package com.simplaapliko.challenge3.data.datasource

import com.simplaapliko.challenge3.data.datasource.business.BusinessEntity
import kotlinx.coroutines.flow.Flow

interface BusinessDataSource {

    fun getBusinesses(
        query: String,
        categories: String?,
        latitude: Double,
        longitude: Double,
        openNow: Boolean,
    ): Flow<List<BusinessEntity>>
}