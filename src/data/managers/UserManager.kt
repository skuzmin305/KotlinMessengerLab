package data.managers

import domain.entities.User
import domain.enums.UserStatus
import patterns.observer.EventType
import patterns.observer.Observable

class UserManager private constructor() : Observable() {
    val users: MutableMap<String, User> = mutableMapOf()

    companion object {
        val instance = UserManager()
    }

    fun addUser(user: User) {
        users[user.id] = user
        notifyObservers(EventType.USER_CREATED, user)
    }

    fun getUser(userId: String): User? {
        return users[userId]
    }

    fun updateUserStatus(userId: String, status: UserStatus) {
        val user = users[userId] ?: return
        users[userId] = user.copy(status = status)
        notifyObservers(EventType.USER_STATUS_CHANGED, user)
    }
}