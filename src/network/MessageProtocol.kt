package network

import domain.enums.UserStatus

class MessageProtocol {

    fun serializeCommand(command: NetworkCommand) : String {
        return "STUB_SERIALIZED_COMMAND"
    }

    fun deserializeCommand(json: String): NetworkCommand {
        val userId = "stub_user_id"
        return NetworkCommand.UpdateUserStatusCommand(
            userId,
            newStatus = UserStatus.ONLINE
        )
    }
}