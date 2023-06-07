package com.torregrosa.faunaval.di

import com.torregrosa.faunaval.repository.AnimalRepository
import com.torregrosa.faunaval.repository.AnimalRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun animalRepository(repo: AnimalRepositoryImp): AnimalRepository

}