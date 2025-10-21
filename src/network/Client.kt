package network

import java.net.Socket

class Client(
    private val host: String,
    private val port: Int
) {
    fun connect() {
        // Заходим в отель и подходим к reception
        val socket = Socket(host, port)

        // Начинаем общение
        communicate(socket)
    }

    private fun communicate(socket: Socket) {
        // Получаем "рот" чтобы говорить с сервером
        val output = socket.getOutputStream().bufferedWriter()

        // Получаем "уши" чтобы слушать сервер
        val input = socket.getInputStream().bufferedReader()

        // Говорим серверу
        output.write("Привет, сервер!")
        output.flush() // "произносим" сообщение

        // Слушаем ответ сервера
        val serverResponse = input.readLine()
        println("Сервер ответил: $serverResponse")

        // Уходим из отеля
        socket.close()
    }
}