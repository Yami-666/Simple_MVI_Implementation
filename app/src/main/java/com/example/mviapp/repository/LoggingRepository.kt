package com.example.mviapp.repository

import kotlinx.coroutines.delay

class LoggingRepository : ILoggingRepository {
    /**
     * Mock for testing!
     * */
    override suspend fun login(email: String, password: String) : Boolean {
        delay(2000)
        return true
    }
}

interface ILoggingRepository {
    suspend fun login(email: String, password: String): Boolean
}