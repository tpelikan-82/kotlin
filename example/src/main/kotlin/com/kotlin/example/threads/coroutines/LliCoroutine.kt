package com.kotlin.example.threads.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

class LliCoroutine {
}




fun main () = runBlocking {
    val myDispatcher = Executors.newFixedThreadPool(4).asCoroutineDispatcher()

    repeat(10) { i ->
        launch(myDispatcher) {
            println("Coroutine $i is running on thread ${Thread.currentThread().name}")
        }
    }
    delay(500L)
    myDispatcher.close()
}

