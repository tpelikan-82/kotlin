package com.kotlin.example.threads

import java.io.File
import kotlin.concurrent.thread

class JoiningThreadDemo {


}

fun consolidateResults() {
    println("Consolidate")
}

fun processFile(file: File) {
    println("Processing ${file.name}")
}

fun main() {

    val files = listOf(File("File1.txt"), File("File2.txt"), File("File3.txt"), File("File4.txt")  )

    val threads : List<Thread> = files.map { Thread{
        processFile(it)
    }
    }

    threads.forEach(Thread::start)
    threads.forEach(Thread::join)

    consolidateResults()

}