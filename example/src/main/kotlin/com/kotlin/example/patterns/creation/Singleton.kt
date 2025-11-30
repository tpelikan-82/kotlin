package com.kotlin.example.patterns.creation

import com.kotlin.example.threads.coroutines.RunBlocking
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

class Singleton {
}

/**
 * Creation pattern
 *
 * Thread-Safe and Eagerly Initialized: The Kotlin object declaration is a concise way to create a class and an instance of that class at the same time.
 * The instance is created lazily upon first access before the body of the object is executed, and its initialization is thread-safe by the Kotlin/JVM compiler.
 */

object DatabaseManager {
    // Properties and functions of your singleton go here
    private var connectionCount = 0

    init {
        println("DatabaseManager initialized (Singleton)")
    }

    fun connect() {
        connectionCount++
        println("Connected to database. Total connections: $connectionCount")
    }

    fun disconnect() {
        if (connectionCount > 0) {
            connectionCount--
        }
        println("Disconnected from database. Total connections: $connectionCount")
    }
}

fun main() = runBlocking  {
    DatabaseManager.connect()
    DatabaseManager.connect()
    DatabaseManager.disconnect()
        DatabaseManager.connect()
        DatabaseManager.connect()
        DatabaseManager.disconnect()

    coroutineScope {
        repeat(50_000) {
            this.launch {
                DatabaseManager.connect()
                delay(1000)
            }
        }
    }

    coroutineScope {
        repeat(50_000) {
            this.launch {
                DatabaseManager.connect()
                delay(1000)
            }
        }
    }
}
    // Access the singleton instance directly by its name


    // You cannot manually create an instance of an object
    // val manager = DatabaseManager() // This is not allowed
//}