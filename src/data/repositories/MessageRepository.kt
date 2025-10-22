package data.repositories

import domain.entities.Message
import patterns.observer.EventType
import patterns.observer.Observable

class MessageRepository (
    private val messages: MutableList<Message>
) : Observable() {

    fun addMessage(message: Message) {
        messages.add(message)
        notifyObservers(EventType.MESSAGE_RECEIVED, message)
    }

    fun getAllMessages(): List<Message> {
        return messages.toList()
    }

    fun getMessagesByUser(userId: String): List<Message> {
        return messages.filter { it.sender.id == userId }
    }

}