package com.kotlin.example.threads.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.*
import kotlin.random.Random

// 1. Define a class (e.g., ViewModel) that manages a CoroutineScope
class DataManager {

    // The Scope defines the boundaries and Job for all coroutines launched here.
    // We use SupervisorJob() to allow one coroutine failure to not cancel the others.
    private val managerScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    // Function to start a long-running, CPU-bound task
    fun startCpuTask() {
        managerScope.launch {
            // Context Element 1: Dispatchers.Default (inherited from scope)
            println("CPU Task started on thread: ${Thread.currentThread().name}")

            // Simulate a CPU-intensive calculation
            var result = 0L
            for (i in 1..100_000_000) {
                result += i
            }
            println("CPU Task finished with result: $result")
        }
    }

    // Function to start an I/O-bound task and update the main thread
    fun startNetworkTask() {
        // We use launch within the scope, but override the Dispatcher.
        managerScope.launch(Dispatchers.IO) {
            // Context Element 2: Dispatchers.IO (overridden in launch builder)
            println("Network Task started on IO thread: ${Thread.currentThread().name}")

            // Simulate a network delay
            delay(1000L)
            val data = "Fetched Data ${Random.nextInt(100)}"
            println("Network Data fetched: $data")

            // Context Element 3: Switching Dispatchers (Main/UI thread)
            // Use withContext to switch the CoroutineContext temporarily.
            withContext(Dispatchers.Default) {
                println("UI Update on Main thread: ${Thread.currentThread().name}")
                // In a real app, this is where you'd update a UI element (like a TextView)
                println("Displaying data: $data")
            }
        }
    }

    // Function to clean up all active coroutines when the DataManager is done
    fun dispose() {
        println("\n--- Cancelling CoroutineScope ---")
        // Cancelling the scope's Job cancels ALL child coroutines (Structured Concurrency)
        managerScope.cancel()
    }
}

fun main() {

    runBlocking {
        val manager = DataManager()

        manager.startCpuTask()
        manager.startNetworkTask()
        manager.startNetworkTask() // Launching multiple concurrent tasks

        // Let the tasks run for a bit
        delay(1500L)

        // Dispose the manager, demonstrating cancellation
        manager.dispose()

        // Wait briefly to see if any cancelled tasks print output (they shouldn't)
        delay(500L)
        println("Main function finished.")
    }

    val customContext = Dispatchers.Default + CoroutineName("MyBlockingContext")

    val result: Int = runBlocking(customContext) {
        42 // Returns 42
    }
    println("runBlocking returned: $result") // Output: 42

}