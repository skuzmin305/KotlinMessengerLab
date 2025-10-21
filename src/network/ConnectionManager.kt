package network

import java.net.Socket

class ConnectionManager(private val server: Server) {
    fun start() {
        // Начинаем слушать сервер и принимать подключения
        // Для каждого нового клиента - регистрируем его
        // Направляем команды куда нужно
    }

    private fun handleNewConnection(clientSocket: Socket) {
        // Добавляем клиента в список активных
        // Начинаем слушать его сообщения
        // Направляем команды через MessageProtocol
    }

    fun sendCommandToClient(clientId: String, command: NetworkCommand) {
        // Найти клиента по ID
        // Преобразовать команду в строку (MessageProtocol)
        // Отправить клиенту
    }
}