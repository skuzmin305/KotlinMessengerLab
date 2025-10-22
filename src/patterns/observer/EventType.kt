package patterns.observer

enum class EventType {
    USER_STATUS_CHANGED,            // - изменился статус пользователя
    USER_CREATED,                   // создан новый пользователь
    USER_UPDATED,                   // обновлены данные пользователя
    SESSION_CREATED,                // - создана новая сессия чата
    SESSION_CLOSED,                 // сессия закрыта
    SESSION_UPDATED,                // сессия обновлена
    MESSAGE_RECEIVED,               // - получено новое сообщение
    MESSAGE_SENT,                   // сообщение отправлено
    MESSAGE_DELETED,                // сообщение удалено
    NETWORK_CONNECTION_CHANGED,     // - изменилось сетевое соединение
    NETWORK_CONNECTED,              // соединение установлено
    NETWORK_DISCONNECTED            // соединение разорвано
}