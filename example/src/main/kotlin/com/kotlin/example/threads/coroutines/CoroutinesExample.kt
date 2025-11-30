package com.kotlin.example.threads.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * A demonstration of traditional Java/Kotlin Threads versus Kotlin Coroutines.
 *
 * Coroutines (using delay) are non-blocking and lightweight, managed by the
 * Kotlin runtime. Threads (using Thread.sleep) are blocking and heavy, managed by the OS.
 */

// --- 1. Traditional Thread Example (Blocking) ---

fun runBlockingThreads() {
    println("\n--- 1. Traditional Threading ---")

    val time = measureTimeMillis {
        val threads = mutableListOf<Thread>()

        repeat(1000) { i ->
            val thread = Thread {
                // Thread.sleep() is BLOCKING. The OS thread is fully occupied
                // and cannot do any other work for this duration.
                Thread.sleep(1000L)
                println("Thread $i completed. Running on: ${Thread.currentThread().name}")
            }
            threads.add(thread)
            thread.start()
        }

        // Wait for all threads to finish before exiting main
        threads.forEach { it.join() }
    }

    println("\nTraditional Threading took $time ms (Total time is sequential sum of sleep).")
    println("Each job required its own distinct OS Thread.")
}


// --- 2. Kotlin Coroutine Example (Non-Blocking) ---

fun runCoroutines() = runBlocking { // runBlocking is used here to block the main thread until all coroutines finish
    println("\n--- 2. Kotlin Coroutines (Lightweight) ---")

    val time = measureTimeMillis {
        // Launch 5 concurrent coroutines
        val jobs = List(1000) { i ->
            launch {
                // delay() is NON-BLOCKING. The underlying thread is freed
                // to execute other coroutines or tasks during this "wait."
                delay(1000L)

                // You will often see the same thread name reused (e.g., "coroutine-dispatcher-1"),
                // demonstrating lightweight concurrency.
                println("Coroutine $i completed. Running on: ${Thread.currentThread().name}")
            }
        }

        // Wait for all coroutines (jobs) to complete
        jobs.forEach { it.join() }
    }

    println("\nKotlin Coroutines took $time ms (Total time is parallel, close to 1000 ms).")
    println("All jobs ran concurrently, likely reusing the same underlying OS thread(s).")
}

fun main() {
    runBlockingThreads()
    runCoroutines()
}