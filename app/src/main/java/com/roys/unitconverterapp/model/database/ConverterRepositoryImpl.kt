package com.roys.unitconverterapp.model.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConverterRepositoryImpl @Inject constructor(private val dao: ConverterDao): ConverterRepository {
    override suspend fun insertResult(result: ConversionResult) {
        dao.insertResult(result)
    }

    override suspend fun deleteResult(result: ConversionResult) {
        dao.deleteResult(result)
    }

    override suspend fun deleteAllResults() {
        dao.deleteAll()
    }

    override fun getSavedResults(): Flow<List<ConversionResult>> {
        return dao.getResults()
    }
}