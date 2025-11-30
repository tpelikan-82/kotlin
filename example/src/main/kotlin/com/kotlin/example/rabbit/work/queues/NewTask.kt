package com.kotlin.example.rabbit.work.queues

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.MessageProperties
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType
import java.lang.String
import kotlin.Array
import kotlin.Exception
import kotlin.Throws
import kotlin.io.println
import kotlin.io.use
import kotlin.use



const val TASK_QUEUE_NAME = "task_queue"


fun main(args: Array<kotlin.String>) {

    val factory = ConnectionFactory()
    factory.setHost("localhost")
    factory.newConnection().use { connection ->
        connection.createChannel().use { channel ->
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null)
            val message = args.joinToString(" ")

            channel.basicPublish(
                "", TASK_QUEUE_NAME,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.toByteArray(charset("UTF-8"))
            )
            println(" [x] Sent '" + message + "'")
        }
    }
}