package com.kotlin.example.threads

class DeamonThreadDemo {
}

fun main() {

    val deemonThread = Thread{
        while(true) {
            println("Deamon thread running")
            Thread.sleep(1000)
            println("Deamon thread finished")
        }
    }

    deemonThread.setDaemon(true)
    deemonThread.start()
    println("Min thread work here. ..")


    Thread.sleep(5000)

    println("DeamonThreadDemo deamon")

}