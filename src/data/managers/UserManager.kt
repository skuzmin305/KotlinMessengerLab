package data.managers

import domain.entities.User
import domain.enums.UserStatus

class UserManager private constructor() {
    val users: MutableMap<String, User> = mutableMapOf()

    companion object {
        val instance = UserManager()
    }

    fun addUser(user: User) {
        users[user.id] = user
    }

    fun getUser(userId: String): User? {
        return users[userId]
    }

    fun updateUserStatus(userId: String, status: UserStatus) {
        val user : User = users[userId] ?: return
            users[userId] = user.copy(status = status)
    }
}