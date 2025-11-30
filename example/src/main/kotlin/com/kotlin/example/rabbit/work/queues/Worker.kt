package com.kotlin.example.rabbit.work.queues

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


fun main() {
    val factory = ConnectionFactory()
    factory.setHost("localhost")
    val connection: Connection = factory.newConnection()
    val channel: Channel = connection.createChannel()

    channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null)
    println(" [*] Waiting for messages. To exit press CTRL+C")

    channel.basicQos(1)

    val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery? ->
        val message = String(delivery!!.getBody(), charset("UTF-8"))
        println(" [x] Received '" + message + "'")
        try {
            doWork(message)
        } finally {
            println(" [x] Done")
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false)
        }
    }
    channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, { consumerTag -> })
}

private fun doWork(task: String) {
    for (ch in task.toCharArray()) {
        if (ch == '.') {
            try {
                Thread.sleep(1000)
            } catch (_ignored: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }
}