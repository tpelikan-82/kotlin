package com.kotlin.example.rabbit.rpc

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.ConnectionFactory
import java.io.IOException
import java.lang.AutoCloseable
import java.util.*
import java.util.concurrent.CompletableFuture


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.UUID;
import java.util.concurrent.*;


class RPCClient : AutoCloseable {
    private val connection: Connection
    private val channel: Channel
    private val requestQueueName = "rpc_queue"

    init {
        val factory = ConnectionFactory()
        factory.setHost("localhost")

        connection = factory.newConnection()
        channel = connection.createChannel()
    }

    @Throws(IOException::class, InterruptedException::class, ExecutionException::class)
    fun call(message: String): String? {
        val corrId: String? = UUID.randomUUID().toString()

        val replyQueueName: String? = channel.queueDeclare().getQueue()
        val props = AMQP.BasicProperties.Builder()
            .correlationId(corrId)
            .replyTo(replyQueueName)
            .build()

        channel.basicPublish("", requestQueueName, props, message.toByteArray(charset("UTF-8")))

        val response = CompletableFuture<String?>()

        val ctag: String? = channel.basicConsume(replyQueueName, true, { consumerTag, delivery ->
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response.complete(String(delivery.getBody(), charset("UTF-8")))
            }
        }, { consumerTag -> })

        val result = response.get()
        channel.basicCancel(ctag)
        return result
    }

    @Throws(IOException::class)
    override fun close() {
        connection.close()
    }

    companion object {
        @JvmStatic
        fun main(argv: Array<String>) {
            try {
                RPCClient().use { fibonacciRpc ->
                    for (i in 0..31) {
                        val i_str = i.toString()
                        println(" [x] Requesting fib(" + i_str + ")")
                        val response = fibonacciRpc.call(i_str)
                        println(" [.] Got '" + response + "'")
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: TimeoutException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            }
        }
    }
}