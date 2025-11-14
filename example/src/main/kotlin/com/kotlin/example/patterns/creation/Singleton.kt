package com.kotlin.example.patterns.creation

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

fun main() {
    // Access the singleton instance directly by its name
    DatabaseManager.connect()
    DatabaseManager.connect()
    DatabaseManager.disconnect()

    // You cannot manually create an instance of an object
    // val manager = DatabaseManager() // This is not allowed
}