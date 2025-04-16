package com.ucb.examen1.di


import android.content.Context
import com.ucb.data.LibroRepository
import com.ucb.data.libro.IlibroRemoteDataSource
import com.ucb.framework.libro.BookRemoteDataSource
import com.ucb.framework.service.IApiService
import com.ucb.framework.service.RetrofitClient
import com.ucb.usecase.LibroBuscado
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofitClient(): RetrofitClient = RetrofitClient

    @Provides
    @Singleton
    fun provideBookRemoteDataSource(retrofitClient: RetrofitClient): IlibroRemoteDataSource {
        return BookRemoteDataSource(retrofitClient)
    }

    @Provides
    @Singleton
    fun provideBookRepository(remoteDataSource: IlibroRemoteDataSource): LibroRepository {
        return LibroRepository(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideSearchBooks(repository: LibroRepository): LibroBuscado {
        return LibroBuscado(repository)
    }

}