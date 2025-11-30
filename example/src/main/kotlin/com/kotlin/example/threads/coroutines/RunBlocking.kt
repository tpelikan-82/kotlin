package com.kotlin.example.threads.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

object RunBlocking {

    val counter = AtomicInteger(0)

    fun increment() = counter.incrementAndGet()

}

// A third-party interface you can't change
interface Repository {
    fun readItem(): Int
}

object MyRepository : Repository {
    override fun readItem(): Int {
        // Bridges to a suspending function
        return runBlocking {
            myReadItem()
        }
    }
}

suspend fun myReadItem(): Int {
    println("Read item ${Thread.currentThread().name}")
    delay(100.milliseconds)
    println("Read item delay ${Thread.currentThread().name}")
    return 4
}



suspend fun main() {

    coroutineScope {
        val result: Int =  MyRepository.readItem()

        this.launch { myReadItem() }

    }


    coroutineScope {
        repeat(50_000) {
            this.launch {
                delay(1.seconds)
                RunBlocking.increment()
                println(RunBlocking.counter)
            }
        }
    }

    println("Final value  ${RunBlocking.counter}")

    repeat(50_000) {
        thread {
            Thread.sleep(100L)
            RunBlocking.increment()
            println(RunBlocking.counter)
        }
    }

    println("Final value  ${RunBlocking.counter}")


}
