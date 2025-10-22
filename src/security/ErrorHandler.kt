package security

import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandler {
    fun handleNetworkError(error: Exception): String {
        return when (error) {
            is ConnectException -> "Не удалось подключиться к серверу. Проверьте интернет-соединение."
            is SocketTimeoutException -> "Превышено время ожидания ответа от сервера."
            is UnknownHostException -> "Сервер не найден. Проверьте адрес подключения."
            is IOException -> "Ошибка сетевого соединения."
            else -> "Произошла unexpected ошибка: ${error.message ?: "неизвестная ошибка"}"
        }
    }

    fun handleValidationError(field: String, value: String): String {
        val validator = DataValidator()

        return when (field) {
            "messageText" -> {
                if (validator.validateMessageText(value)) "OK"
                else "Сообщение невалидно: пустое или слишком длинное"
            }
            "userName" -> {
                if (validator.validateUserName(value)) "OK"
                else "Некорректное имя пользователя!"
            }
            else -> "Неизвестное поле"
        }
    }

    fun logError(context: String, error: Exception) {
        println("[${java.time.LocalTime.now()}] [$context] ОШИБКА: ${error.javaClass.simpleName}")
        println("Сообщение: ${error.message}")
        error.printStackTrace()
    }
}