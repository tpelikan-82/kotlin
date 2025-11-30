package com.kotlin.example.threads.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds


class SuspendFunction {
}

suspend fun suspendFunction() {
    println("Suspend Function started. ${Thread.currentThread().name}")
    delay(1.seconds)
    println("Suspend Function finished")
}

suspend fun greet() {
    println("The greet() on the thread: ${Thread.currentThread().name}")
    delay(1000L)            //simulate fetch data or store data
    println("The greet() finished")
}

suspend fun main(args: Array<String>) {

//    greet()
//    suspendFunction()
//
//    withContext(Dispatchers.Default) { // this: CoroutineScope
//        // Starts a coroutine inside the scope with CoroutineScope.launch()
//        this.launch() { greet() }
//        println("The withContext() on the thread: ${Thread.currentThread().name}")
//        this.launch() { suspendFunction() }
//    }


    //use Dispatchers.Default
    val result = coroutineScope {
        this.launch {
            launch {
                delay(2.seconds)
                println("Child of the enclosing coroutine completed")
            }
            println("Child coroutine 1 completed")
        }
        launch {
            delay(1.seconds)
            println("Child coroutine 2 completed")
        }
    }

    println("Coroutine scope completed with result: $result")


    withContext(Dispatchers.Default) { // this: CoroutineScope
        // Starts downloading the first page
        val firstPage = this.async {
            delay(50.milliseconds)
            "First page"
        }

        // Starts downloading the second page in parallel
        val secondPage = this.async {
            delay(10000.milliseconds)
            "Second page"
        }

        // Awaits both results and compares them
        val resultPage1 = firstPage.await()
        val resultPage2 = secondPage.await()

        val pagesAreEqual = resultPage1 == resultPage2
        println("Pages are equal: $pagesAreEqual")
    }


}