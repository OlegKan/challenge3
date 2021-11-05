package com.simplaapliko.challenge3.domain.business

import kotlinx.coroutines.flow.Flow

interface BusinessRepository {

    fun getBusinesses(
        query: String,
        latitude: Double,
        longitude: Double,
        openNow: Boolean,
    ): Flow<List<BusinessModel>>
}
