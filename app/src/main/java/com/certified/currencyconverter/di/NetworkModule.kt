package com.certified.currencyconverter.di

import com.certified.currencyconverter.data.network.ConverterApiService
import com.certified.currencyconverter.data.repository.Repository
import com.certified.currencyconverter.util.ApiErrorUtil
import com.certified.currencyconverter.util.Config.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    fun provideConverterApiService(retrofit: Retrofit): ConverterApiService =
        retrofit.create(ConverterApiService::class.java)

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideRepository(converterApiService: ConverterApiService) =
        Repository(converterApiService)

    @Provides
    fun provideErrorUtils(retrofit: Retrofit) = ApiErrorUtil(retrofit)
}