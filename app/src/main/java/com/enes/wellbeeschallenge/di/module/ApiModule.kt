package com.enes.wellbeeschallenge.di.module

import com.enes.wellbeeschallenge.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
