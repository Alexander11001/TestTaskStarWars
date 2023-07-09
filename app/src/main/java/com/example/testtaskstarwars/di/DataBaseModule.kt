package com.example.testtaskstarwars.di

import android.content.Context
import androidx.room.Room
import com.example.testtaskstarwars.data.room.dao.StarWarsDao
import com.example.testtaskstarwars.data.room.dao.StarWarsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): StarWarsDatabase {
        return Room.databaseBuilder(
            context,
            StarWarsDatabase::class.java, "star-wars-db"
        ).build()
    }

    @Provides
    fun provideStarWarsDao(database: StarWarsDatabase): StarWarsDao {
        return database.starWarsDao()
    }
}