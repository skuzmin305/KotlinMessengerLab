package data.repositories

import domain.entities.Message

class MessageRepository(
    private val messages: MutableList<Message>
) {

    fun addMessage(message: Message) {
        messages.add(message)
    }

    fun getAllMessages(): List<Message> {
        return messages.toList()
    }

    fun getMessagesByUser(userId: String): List<Message> {
        return messages.filter { it.sender.id == userId }
    }

}