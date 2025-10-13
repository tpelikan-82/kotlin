package com.kotlin.example.threads

class FileDownloader(val fileName: String) : Thread() {

    override fun run() {

        println("Downloading $fileName")

        val timeout = Math.random() * 5000L


        Thread.sleep(timeout.toLong())

        println("Downloaded $fileName")
    }

}

fun main(args: Array<String>) {

    println("Starting FileDownloader")

    val files = listOf<String>("file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt")

    val threads = files.map { FileDownloader(it) }
    threads.forEach {
        it.start()
    }

    threads.forEach {
        it.join()
    }

    println("Done")

}