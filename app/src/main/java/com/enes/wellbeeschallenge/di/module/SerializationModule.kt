package com.enes.wellbeeschallenge.di.module

import com.enes.wellbeeschallenge.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("SpellCheckingInspection")
@Module
@InstallIn(SingletonComponent::class)
class SerializationModule {

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    fun provideGson(gsonBuilder: GsonBuilder): Gson = gsonBuilder.create()

    @Provides
    fun provideGsonBuilder(): GsonBuilder {
        val gsonBuilder = GsonBuilder()
            .disableHtmlEscaping()
            .serializeNulls()
        if (BuildConfig.DEBUG) {
            gsonBuilder.setPrettyPrinting()
        }
        return gsonBuilder
    }
}
