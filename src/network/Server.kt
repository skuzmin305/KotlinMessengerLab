package network

import java.net.ServerSocket
import java.net.Socket

class Server(private val port : Int) {
    private val serverSocket = ServerSocket(port)

    fun start() {
        while (true) {
            // Ждём пока придёт клиент
            val clientSocket = serverSocket.accept()
            // Обслуживаем клиента
            handleClient(clientSocket)
        }
    }

    private fun handleClient(clientSocket: Socket) {
        // Получаем "рот" клиента (чтение данных)
        val input = clientSocket.getInputStream().bufferedReader()

        // Получаем "уши" клиента (отправка данных)
        val output = clientSocket.getOutputStream().bufferedWriter()

        // Читаем что сказал клиент
        val clientMessage = input.readLine()
        println("Клиент сказал: $clientMessage")

        // Отвечаем клиенту
        output.write("Привет, я сервер!")
        output.flush()

        // Закрываем соединение
        clientSocket.close()
    }
}