package com.kotlin.example.threads

import org.apache.catalina.Executor
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.concurrent.Executors

// Define a path for a dummy file to read
const val FILE_PATH = "dummy_data.txt"

fun setupDummyFile() {
    val file = File(FILE_PATH)
    file.writeText("Line 1: Hello Kotlin\n")
    file.appendText("Line 2: Safe resource haandling.")
}


class MyTask : Runnable {

    override fun run() {
        println("I am running in thread ${Thread.currentThread().name}")
        try {
            FileReader(FILE_PATH).use { fileReader ->
                BufferedReader(fileReader).use { reader ->

                    var lineNumber = 1
                    var line: String? = reader.readLine()

                    // Read lines until EOF (null)
                    while (line != null) {
                        println("Read line $lineNumber: $line")
                        // Simulate a potential error condition based on content
                        if (lineNumber == 2 && line.contains("handling")) {
                            // This will trigger the catch block
                            throw RuntimeException("Simulated error during line processing.")
                        }
                        lineNumber++
                        line = reader.readLine()
                    }
                }

                println("Successfully finished reading all lines.")
            }


            while (!Thread.currentThread().isInterrupted) {
                println("I am running in thread ${Thread.currentThread().name} is running")
                Thread.sleep(1000)
            }
        } catch (e: InterruptedException) {
            println("I am not running in thread ${Thread.currentThread().name}")
            Thread.currentThread().interrupt()
        }
    }
}

fun main() {

    setupDummyFile()

    val executor = Executors.newFixedThreadPool(10)


    for (i in 0..9) {
        executor.submit {
            val myTask = MyTask()
            val thread = Thread(myTask)
            thread.start()

        }
    }

    println("I am running in thread ${Thread.currentThread().name}")



    executor.shutdown()



}