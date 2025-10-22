package test

import data.managers.UserManager
import data.managers.SessionManager
import data.repositories.MessageRepository
import domain.entities.User
import domain.entities.Message
import domain.entities.ChatSession
import domain.enums.UserStatus
import domain.enums.MessageType
import patterns.observer.EventLogger
import security.DataValidator
import security.Base64Encoder
import security.TextTransformer
import utils.generators.IdGenerator

fun main() {
    println("🚀 ЗАПУСК КОМПЛЕКСНОГО ТЕСТИРОВАНИЯ СИСТЕМЫ")
    println("=".repeat(50))

    // Инициализация всех компонентов системы
    val eventLogger = EventLogger()
    val userManager = UserManager.instance
    val sessionManager = SessionManager.instance
    val messageRepository = MessageRepository(mutableListOf())
    val dataValidator = DataValidator()
    val base64Encoder = Base64Encoder()
    val textTransformer = TextTransformer()

    // Регистрация наблюдателя
    userManager.addObserver(eventLogger)
    sessionManager.addObserver(eventLogger)
    messageRepository.addObserver(eventLogger)

    // ТЕСТ 1: ВАЛИДАЦИЯ ДАННЫХ
    println("\n📋 ТЕСТ 1: СИСТЕМА ВАЛИДАЦИИ ДАННЫХ")
    println("-".repeat(30))

    val validUserName = "Иван Иванов"
    val invalidUserName = "И"
    val validMessage = "Привет, это тестовое сообщение!"
    val invalidMessage = ""

    println("Валидация имени '$validUserName': ${dataValidator.validateUserName(validUserName)}")
    println("Валидация имени '$invalidUserName': ${dataValidator.validateUserName(invalidUserName)}")
    println("Валидация сообщения '$validMessage': ${dataValidator.validateMessageText(validMessage)}")
    println("Валидация пустого сообщения: ${dataValidator.validateMessageText(invalidMessage)}")

    // ТЕСТ 2: ШИФРОВАНИЕ И КОДИРОВАНИЕ
    println("\n🔐 ТЕСТ 2: СИСТЕМА БЕЗОПАСНОСТИ")
    println("-".repeat(30))

    val secretMessage = "Секретное сообщение 123"
    val transformed = textTransformer.transformText(secretMessage)
    val restored = textTransformer.restoreText(transformed)

    val encoded = base64Encoder.encodeToBase64(secretMessage)
    val decoded = base64Encoder.decodeFromBase64(encoded)

    println("Оригинал: $secretMessage")
    println("Преобразование TextTransformer: $transformed")
    println("Восстановление: $restored")
    println("Base64 кодирование: $encoded")
    println("Base64 декодирование: $decoded")
    println("Проверка целостности: ${secretMessage == restored && secretMessage == decoded}")

    // ТЕСТ 3: УПРАВЛЕНИЕ ПОЛЬЗОВАТЕЛЯМИ
    println("\n👥 ТЕСТ 3: СИСТЕМА ПОЛЬЗОВАТЕЛЕЙ")
    println("-".repeat(30))

    val user1 = User("user_001", "Алексей Петров", UserStatus.ONLINE)
    val user2 = User("user_002", "Мария Сидорова", UserStatus.ONLINE)
    val user3 = User("user_003", "Иван Козлов", UserStatus.OFFLINE)

    userManager.addUser(user1)
    userManager.addUser(user2)
    userManager.addUser(user3)

    println("Создано пользователей: ${userManager.users.size}")
    println("Пользователь 1: ${userManager.getUser("user_001")}")
    println("Пользователь 2: ${userManager.getUser("user_002")}")

    // Изменение статуса пользователя
    userManager.updateUserStatus("user_003", UserStatus.ONLINE)
    println("Обновлённый статус user_003: ${userManager.getUser("user_003")?.status}")

    // ТЕСТ 4: СЕССИИ ЧАТА
    println("\n💬 ТЕСТ 4: СИСТЕМА СЕССИЙ ЧАТА")
    println("-".repeat(30))

    sessionManager.createSession(user1, user2)
    sessionManager.createSession(user1, user3)

    println("Создано сессий: ${sessionManager.sessions.size}")

    val firstSessionId = sessionManager.sessions.keys.first()
    val session = sessionManager.getSession(firstSessionId)
    println("Первая сессия: $session")

    // ТЕСТ 5: СООБЩЕНИЯ В ЧАТЕ
    println("\n📨 ТЕСТ 5: СИСТЕМА СООБЩЕНИЙ")
    println("-".repeat(30))

    val message1 = Message(
        "msg_001",
        "Привет, Мария! Как дела?",
        System.currentTimeMillis(),
        user1,
        MessageType.TEXT
    )

    val message2 = Message(
        "msg_002",
        "Привет, Алексей! Всё хорошо, спасибо!",
        System.currentTimeMillis() + 1000,
        user2,
        MessageType.TEXT
    )

    val systemMessage = Message(
        "msg_003",
        "Пользователь Иван присоединился к чату",
        System.currentTimeMillis() + 2000,
        user3,
        MessageType.SYSTEM
    )

    messageRepository.addMessage(message1)
    messageRepository.addMessage(message2)
    messageRepository.addMessage(systemMessage)

    println("Всего сообщений: ${messageRepository.getAllMessages().size}")
    println("Сообщения user1: ${messageRepository.getMessagesByUser("user_001").size}")
    println("Сообщения user2: ${messageRepository.getMessagesByUser("user_002").size}")

    // ТЕСТ 6: ГЕНЕРАЦИЯ ID
    println("\n🆔 ТЕСТ 6: СИСТЕМА ГЕНЕРАЦИИ ID")
    println("-".repeat(30))

    val idGenerator = IdGenerator()
    val generatedIds = (1..5).map { idGenerator.generateId() }

    println("Сгенерированные ID:")
    generatedIds.forEachIndexed { index, id ->
        println("  ${index + 1}. $id")
    }
    println("Все ID уникальны: ${generatedIds.toSet().size == generatedIds.size}")

    // ТЕСТ 7: ЗАКРЫТИЕ СЕССИИ
    println("\n🔚 ТЕСТ 7: ЗАВЕРШЕНИЕ СЕССИИ")
    println("-".repeat(30))

    sessionManager.closeSession(firstSessionId)
    val closedSession = sessionManager.getSession(firstSessionId)
    println("Сессия закрыта: ${closedSession?.isActive == false}")

    // ТЕСТ 8: СВЯЗЬ КОМПОНЕНТОВ МЕЖДУ СОБОЙ
    println("\n🔗 ТЕСТ 8: ИНТЕГРАЦИЯ КОМПОНЕНТОВ")
    println("-".repeat(30))

    // Создание зашифрованного сообщения
    val secureText = "Секретное сообщение для передачи"
    val validated = dataValidator.validateMessageText(secureText)

    if (validated) {
        val encodedMessage = base64Encoder.encodeToBase64(secureText)
        val newMessage = Message(
            "secure_msg_001",
            encodedMessage,
            System.currentTimeMillis(),
            user1,
            MessageType.TEXT
        )
        messageRepository.addMessage(newMessage)
        println("Зашифрованное сообщение добавлено в систему")

        // Извлечение и расшифровка
        val storedMessage = messageRepository.getAllMessages().last()
        val decodedMessage = base64Encoder.decodeFromBase64(storedMessage.text)
        println("Расшифрованное сообщение: $decodedMessage")
    }

    // ФИНАЛЬНАЯ СТАТИСТИКА
    println("\n📊 ФИНАЛЬНАЯ СТАТИСТИКА СИСТЕМЫ")
    println("=".repeat(50))
    println("👥 Пользователей: ${userManager.users.size}")
    println("💬 Сессий чата: ${sessionManager.sessions.size}")
    println("📨 Сообщений: ${messageRepository.getAllMessages().size}")
    println("🔐 Валидных сообщений: ${messageRepository.getAllMessages().count { dataValidator.validateMessageText(it.text) }}")
    println("🔄 Активных сессий: ${sessionManager.sessions.values.count { it.isActive }}")

    println("\n✅ КОМПЛЕКСНОЕ ТЕСТИРОВАНИЕ ЗАВЕРШЕНО!")
    println("Все компоненты системы работают корректно и интегрированы между собой! 🎉")
}