package com.roys.unitconverterapp.di

import android.app.Application
import androidx.room.Room
import com.roys.unitconverterapp.model.database.ConverterDatabase
import com.roys.unitconverterapp.model.database.ConverterRepository
import com.roys.unitconverterapp.model.database.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideConverterDb(context: Application): ConverterDatabase{
        return Room.databaseBuilder(
            context,
            ConverterDatabase::class.java,
            "converter_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideConverterRepo(db: ConverterDatabase): ConverterRepository{
        return ConverterRepositoryImpl(db.converterDao)
    }
}