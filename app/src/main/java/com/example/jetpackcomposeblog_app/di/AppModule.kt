package com.example.jetpackcomposeblog_app.di

import com.example.jetpackcomposeblog_app.pokemon.data.constants.ApiConstant.BASE_URL
import com.example.jetpackcomposeblog_app.pokemon.data.datasource.ApiService
import com.example.jetpackcomposeblog_app.pokemon.data.repository.DashboardRepositoryImpl
import com.example.jetpackcomposeblog_app.pokemon.domain.repository.DashboardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDashboardRepository(apiService: ApiService): DashboardRepository {
        return DashboardRepositoryImpl(apiService = apiService)
    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()
            .create(ApiService::class.java)
    }
}