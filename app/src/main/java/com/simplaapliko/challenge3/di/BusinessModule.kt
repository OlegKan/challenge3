package com.simplaapliko.challenge3.di

import com.simplaapliko.challenge3.data.datasource.BusinessDataSource
import com.simplaapliko.challenge3.data.datasource.business.BusinessCache
import com.simplaapliko.challenge3.data.datasource.business.BusinessDataSourceFactory
import com.simplaapliko.challenge3.data.datasource.business.BusinessMemoryCache
import com.simplaapliko.challenge3.data.datasource.business.BusinessRemoteDataSource
import com.simplaapliko.challenge3.data.network.yelp.YelpApi
import com.simplaapliko.challenge3.data.repository.BusinessRepositoryImpl
import com.simplaapliko.challenge3.domain.business.BusinessRepository
import com.simplaapliko.challenge3.domain.business.BusinessSearchInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BusinessModule {

    @Provides
    @Singleton
    fun businessRemoteDataSource(
        yelpApi: YelpApi,
        businessCache: BusinessCache
    ): BusinessDataSource {
        return BusinessRemoteDataSource(yelpApi, businessCache)
    }

    @Provides
    @Singleton
    fun businessCache(): BusinessCache {
        return BusinessMemoryCache()
    }

    @Provides
    @Singleton
    fun businessDataSourceFactory(
        businessDataSource: BusinessDataSource,
        localCache: BusinessCache,
    ): BusinessDataSourceFactory {
        return BusinessDataSourceFactory(
            businessDataSource,
            localCache,
        )
    }

    @Provides
    @Singleton
    fun businessRepository(
        dataSourceFactory: BusinessDataSourceFactory,
    ): BusinessRepository {
        return BusinessRepositoryImpl(dataSourceFactory)
    }

    @Provides
    @Singleton
    fun businessSearchInteractor(
        businessRepository: BusinessRepository
    ): BusinessSearchInteractor {
        return BusinessSearchInteractor(businessRepository)
    }
}
