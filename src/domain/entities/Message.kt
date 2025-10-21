package domain.entities

import domain.enums.MessageType

data class Message(
    val id: String,
    val text: String,
    val timeStamp: Long,
    val sender: User,
    val messageType: MessageType
)