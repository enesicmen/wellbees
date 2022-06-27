package com.enes.wellbeeschallenge.di.module

import com.enes.wellbeeschallenge.BuildConfig
import com.enes.wellbeeschallenge.data.api.base.SuspendCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("SpellCheckingInspection")
@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        suspendCallAdapterFactory: SuspendCallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addCallAdapterFactory(suspendCallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideCallAdapterFactory() = SuspendCallAdapterFactory()
}
