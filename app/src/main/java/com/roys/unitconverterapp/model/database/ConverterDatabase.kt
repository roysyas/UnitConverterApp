package com.roys.unitconverterapp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ConversionResult::class], version = 1)
abstract class ConverterDatabase: RoomDatabase() {
    abstract val converterDao: ConverterDao
}