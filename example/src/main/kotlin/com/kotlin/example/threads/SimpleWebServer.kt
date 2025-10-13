package com.kotlin.example.threads

import java.io.InputStream
import java.io.OutputStream
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.StandardCharsets


private class NonClosingInputStream(private val delegate: InputStream) : InputStream() {
    override fun read(): Int = delegate.read()
    override fun read(b: ByteArray): Int = delegate.read(b)
    override fun read(b: ByteArray, off: Int, len: Int): Int = delegate.read(b, off, len)
    override fun available(): Int = delegate.available()
    // IMPORTANT: Override close() to do nothing.
    override fun close() {
        // Keeps the underlying stream (and socket) open.
    }
}

private class NonClosingOutputStream(private val delegate: OutputStream) : OutputStream() {
    override fun write(b: Int) = delegate.write(b)
    override fun write(b: ByteArray) = delegate.write(b)
    override fun write(b: ByteArray, off: Int, len: Int) = delegate.write(b, off, len)
    override fun flush() = delegate.flush()
    // IMPORTANT: Override close() to do nothing.
    override fun close() {
        // Keeps the underlying stream (and socket) open.
    }
}



class ClientHandler(val socket: Socket) : Thread() {

    override fun run() {

        val notCloseInputStream = NonClosingInputStream(socket.getInputStream());
        val notCloseOutputStream = NonClosingOutputStream(socket.getOutputStream());

        writeTextResponse(notCloseOutputStream, "HTTP/1.1 200 OK\rContent-Type: text-plain r\n\r Here is my response")
        readTextLineByLine(notCloseInputStream)



        socket.close()

    }

    fun readTextLineByLine(inputStream: InputStream) {
        println("--- 1. Reading Text Line by Line ---")

        // 1. Wrap the original stream to suppress closing
        val nonClosingStream = NonClosingInputStream(inputStream)

        // The use function ensures the BufferedReader is closed automatically (resource management).
        nonClosingStream.bufferedReader().use { reader ->
            var line: String?
            // Read lines until null (end of stream) is encountered
            while (reader.readLine().also { line = it } != null) {
                println("Received: $line")
            }
        }

        println("Status: Socket InputStream successfully read, but remains open.")
    }

    fun writeTextResponse(outputStream: OutputStream, responseText: String) {
        println("\n--- 4. Writing Text Response ---")

        val nonClosingStream = NonClosingOutputStream(outputStream)

        // Alternative for binary data or specific protocols:
        nonClosingStream.write(responseText.toByteArray(StandardCharsets.UTF_8))
        nonClosingStream.flush()
    }

}





fun main() {

    val serverSocket = ServerSocket(8085)
    println("Listening on ${serverSocket.inetAddress.hostAddress}")

    while (true) {
        val serverSocket = serverSocket.accept();
        ClientHandler(serverSocket).start()
    }

}