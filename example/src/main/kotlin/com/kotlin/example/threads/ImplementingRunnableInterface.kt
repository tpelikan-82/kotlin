package com.kotlin.example.threads



class MyRunnableInterface : Runnable {
    override fun run() {
        println("Min thread work here...")
    }
}



fun main() {


    val myRunnableInterfaceThread = Thread(MyRunnableInterface())
    myRunnableInterfaceThread.start()


    myRunnableInterfaceThread.stop()

}