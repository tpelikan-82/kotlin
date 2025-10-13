package com.kotlin.example.threads

class BlockingQueueDemo {
}

fun main() {

    val queue = java.util.concurrent.ArrayBlockingQueue<String>(10)

    val producer = Thread{
        for (i in 0..10) {
            queue.put("Item $i")
            println("Produce: item $i")
            Thread.sleep(1000)
        }
    }

    val consumer = Thread{
        for (i in 0..10) {
            val item = queue.take()
            println(" Consumed Item: $item")
//            Thread.sleep(500)
        }

    }

    producer.start();
    consumer.start()

    consumer.join()

}