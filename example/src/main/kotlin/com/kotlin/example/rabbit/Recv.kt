package com.kotlin.example.rabbit


import com.rabbitmq.client.*
import java.nio.charset.StandardCharsets

class Recv {
}

@Throws(Exception::class)
fun main() {
    val factory = ConnectionFactory()
    factory.setHost("localhost")
    val connection: Connection = factory.newConnection()
    val channel: Channel = connection.createChannel()

    channel.queueDeclare(QUEUE_NAME, false, false, false, null)
    println(" [*] Waiting for messages. To exit press CTRL+C")

    val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery? ->
        val message = String(delivery!!.getBody(), StandardCharsets.UTF_8)
        println(" [x] Received '" + message + "'")
    }
    channel.basicConsume(QUEUE_NAME, true, deliverCallback, CancelCallback { consumerTag: String? -> })
}