package com.kotlin.example.rabbit.rpc

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery

import com.rabbitmq.client.*;

class RPCServer {
}

const val RPC_QUEUE_NAME = "rpc_queue"

private fun fib(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 1
    return fib(n - 1) + fib(n - 2)
}

@Throws(Exception::class)
fun main() {
    val factory = ConnectionFactory()
    factory.setHost("localhost")

    val connection: Connection = factory.newConnection()
    val channel: Channel = connection.createChannel()
    channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null)
    channel.queuePurge(RPC_QUEUE_NAME)

    channel.basicQos(1)

    println(" [x] Awaiting RPC requests")

    val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery? ->
        val replyProps = AMQP.BasicProperties.Builder()
            .correlationId(delivery!!.getProperties().getCorrelationId())
            .build()
        var response = ""
        try {
            val message = String(delivery.getBody(), charset("UTF-8"))
            val n = message.toInt()

            println(" [.] fib(" + message + ")")
            response += fib(n)
        } catch (e: RuntimeException) {
            println(" [.] " + e)
        } finally {
            channel.basicPublish(
                "",
                delivery.getProperties().getReplyTo(),
                replyProps,
                response.toByteArray(charset("UTF-8"))
            )
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false)
        }
    }

    channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, ({ consumerTag -> }))
}