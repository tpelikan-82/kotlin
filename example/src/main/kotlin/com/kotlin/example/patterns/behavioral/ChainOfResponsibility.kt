package com.kotlin.example.patterns.behavioral

/**
 * LogLevel Enum
 * Defines the request types or levels that the chain can handle.
 */
enum class LogLevel(val level: Int) {
    DEBUG(1),
    INFO(2),
    WARNING(3),
    ERROR(4),
    FATAL(5)
}

/**
 * Abstract Handler
 * Defines the interface for handling requests and implements the next link in the chain.
 */
abstract class AbstractLogger(private val level: LogLevel) {
    // The link to the next handler in the chain
    private var nextLogger: AbstractLogger? = null

    /**
     * Sets the next handler in the chain. This is how the chain is constructed.
     */
    fun setNextLogger(nextLogger: AbstractLogger) {
        this.nextLogger = nextLogger
    }

    /**
     * The core handling method. It processes the message if the level matches
     * and then passes the request down the chain to the successor.
     */
    fun logMessage(level: LogLevel, message: String) {
        // Step 1: Check if this handler is responsible for the message
        if (this.level.level <= level.level) {
            write(message, level)
        }

        // Step 2: Pass the request to the next handler if one exists
        nextLogger?.logMessage(level, message)
    }

    // Abstract method to be implemented by concrete handlers
    protected abstract fun write(message: String, level: LogLevel)
}

/**
 * Concrete Handler 1: Console Logger
 * Handles messages at the DEBUG level or higher.
 */
class ConsoleLogger(level: LogLevel) : AbstractLogger(level) {
    override fun write(message: String, level: LogLevel) {
        println("[CONSOLE] [${level.name}]: $message")
    }
}

/**
 * Concrete Handler 2: File Logger
 * Handles messages at the WARNING level or higher.
 */
class FileLogger(level: LogLevel) : AbstractLogger(level) {
    override fun write(message: String, level: LogLevel) {
        println("[FILE_SYSTEM] [${level.name}]: Writing to file: $message")
    }
}

/**
 * Concrete Handler 3: Email Logger
 * Handles messages at the ERROR level or higher.
 */
class EmailLogger(level: LogLevel) : AbstractLogger(level) {
    override fun write(message: String, level: LogLevel) {
        println("[EMAIL_ALERT] [${level.name}]: Sending email notification: $message")
    }
}

/**
 * Client Implementation
 * Creates and links the chain, then initiates requests.
 */
fun createChainOfLoggers(): AbstractLogger {
    // 1. Create the handlers
    val consoleLogger = ConsoleLogger(LogLevel.DEBUG)
    val fileLogger = FileLogger(LogLevel.WARNING)
    val emailLogger = EmailLogger(LogLevel.ERROR)

    // 2. Link the handlers to form the chain
    // The chain starts at the ConsoleLogger (lowest level)
    consoleLogger.setNextLogger(fileLogger)
    fileLogger.setNextLogger(emailLogger)

    // The start of the chain is returned (the logger that handles the lowest priority)
    return consoleLogger
}

fun main() {
    val loggerChain = createChainOfLoggers()

    println("--- Test 1: DEBUG message (Lowest Level) ---")
    // Should only be handled by ConsoleLogger
    loggerChain.logMessage(LogLevel.DEBUG, "Loading user configuration.")

    println("\n--- Test 2: WARNING message (Medium Level) ---")
    // Should be handled by ConsoleLogger and FileLogger
    loggerChain.logMessage(LogLevel.WARNING, "Resource pool is running low.")

    println("\n--- Test 3: FATAL message (Highest Level) ---")
    // Should be handled by ConsoleLogger, FileLogger, and EmailLogger
    loggerChain.logMessage(LogLevel.FATAL, "Database connection lost!")
}