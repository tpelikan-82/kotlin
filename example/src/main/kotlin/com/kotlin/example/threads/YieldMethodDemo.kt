package com.kotlin.example.threads

class YieldMethodDemo {
}

fun performanceTask(name: String, isYield: Boolean = true) {
    for (i in 1..5) {
        println("${name} is working: iteration  $i")
        try {
            Thread.sleep(10000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        if (isYield) {
            Thread.yield()
        }
    }
}


fun main() {

    val myThread1 = Thread{
        performanceTask("Worker 1", false)
    }
    myThread1.priority = Thread.MAX_PRIORITY

    val myThread2 = Thread{
        performanceTask("Worker 2")
    }

    val myThread3 = Thread{
        performanceTask("Worker 3", false)
    }

    val myThread4 = Thread{
        performanceTask("Worker 4")
    }

    val myThread5 = Thread{
        performanceTask("Worker 5")
    }

    val myThread6 = Thread{
        performanceTask("Worker 6")
    }

    val myThread7 = Thread{
        performanceTask("Worker 7")
    }

    val myThread8 = Thread{
        performanceTask("Worker 8")
    }
    myThread1.start()
    myThread2.start()
    myThread3.start()
    myThread4.start()
    myThread5.start()
    myThread6.start()
    myThread7.start()
    myThread8.start()


}