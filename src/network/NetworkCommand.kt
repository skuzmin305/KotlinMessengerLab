package network

import domain.entities.Message
import domain.entities.User
import domain.enums.UserStatus

sealed class NetworkCommand {

    data class SendMessageCommand(val message: Message) : NetworkCommand()
    data class CreateSessionCommand(val user1 : User, val user2 : User) : NetworkCommand()
    data class UpdateUserStatusCommand(val userId : String, val newStatus : UserStatus) : NetworkCommand()
}