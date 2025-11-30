package com.kotlin.example.patterns.behavioral

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 1. Mediator Interface
 * Defines the contract for communication between Colleague objects.
 */
interface ChatMediator {
    fun sendMessage(message: String, colleague: User)
    fun addUser(user: User)
}

/**
 * 2. Colleague Abstract Class/Interface
 * Defines the contract for objects that communicate with the Mediator.
 * Colleague objects hold a reference to their Mediator.
 */
abstract class User(private val mediator: ChatMediator, val name: String) {
    fun send(message: String) {
        println("[$name] sending message: \"$message\"")
        mediator.sendMessage(message, this)
    }

    abstract fun receive(message: String, sender: User)

    // For initialization, so the Mediator can register the User
    init {
        mediator.addUser(this)
    }
}

/**
 * 3. Concrete Mediator
 * Implements the communication logic for the Colleague objects.
 * It knows about and coordinates the actions of the Colleagues.
 */
class ChatRoom : ChatMediator {
    private val users = mutableListOf<User>()
    private val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    override fun addUser(user: User) {
        users.add(user)
        println("--- ${user.name} joined the chat room. ---")
    }

    override fun sendMessage(message: String, colleague: User) {
        val timestamp = LocalDateTime.now().format(timeFormatter)
        // Loop through all users and notify them, except the sender (colleague)
        users.filter { it != colleague }.forEach { receiver ->
            receiver.receive("[$timestamp] $message", colleague)
        }
    }
}

/**
 * 4. Concrete Colleague
 * Implements the User interface and communicates with other users
 * only through its Mediator (ChatRoom).
 */
class ChatUser(mediator: ChatMediator, name: String) : User(mediator, name) {
    override fun receive(message: String, sender: User) {
        println("[${this.name} received from ${sender.name}]: $message")
    }
}

fun main() {
    // Create the Concrete Mediator (The Chat Room)
    val chatRoom = ChatRoom()

    // Create Concrete Colleagues (The Users), passing the mediator to their constructor
    val alice = ChatUser(chatRoom, "Alice")
    val bob = ChatUser(chatRoom, "Bob")
    val charlie = ChatUser(chatRoom, "Charlie")

    println("\n--- Starting Conversation ---\n")

    // Alice sends a message, which goes through the ChatRoom mediator
    // and is distributed to all *other* users (Bob and Charlie).
    alice.send("Hello everyone! I'm planning a Kotlin workshop.")

    println("\n--- Mediator in Action ---\n")

    // Bob replies. Only Alice and Charlie receive this message.
    bob.send("Sounds great, Alice! When is it?")

    println("\n--- Charlie Jumps In ---\n")

    // Charlie sends a message. Only Alice and Bob receive it.
    charlie.send("I'm in! Is it about Coroutines?")
}