package patterns.observer

enum class EventType {
    MESSAGE_RECEIVED, // - получено новое сообщение
    USER_STATUS_CHANGED, // - изменился статус пользователя
    SESSION_CREATED, // - создана новая сессия чата
    NETWORK_CONNECTION_CHANGED // - изменилось сетевое соединение
}