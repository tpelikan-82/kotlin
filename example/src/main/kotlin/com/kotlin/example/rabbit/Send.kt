package com.kotlin.example.rabbit

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import java.nio.charset.StandardCharsets


val QUEUE_NAME: String = "hello";


    fun main() {

        val factory = ConnectionFactory()
        factory.setHost("localhost")
        factory.newConnection().use { connection -> connection.createChannel().use { channel ->

            channel.queueDeclare(QUEUE_NAME, false, false, false, null)
            val message = "Hello World!"
            channel.basicPublish("", QUEUE_NAME, null, message.toByteArray())
            println(" [x] Sent '" + message + "'")


        } }

    }

