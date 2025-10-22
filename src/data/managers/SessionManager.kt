package data.managers

import domain.entities.ChatSession
import domain.entities.User
import patterns.observer.EventType
import patterns.observer.Observable
import utils.generators.IdGenerator

class SessionManager private constructor() : Observable() {
    val sessions: MutableMap<String, ChatSession> = mutableMapOf()

    companion object {
        val instance = SessionManager()
    }

    fun createSession(user1 : User, user2 : User) {
        if (user1 == user2) return
        val sessionId = IdGenerator().generateId()

        val chatSession: ChatSession = ChatSession(
            sessionId,
            user1,
            user2,
            System.currentTimeMillis(),
            true
        )

        sessions[sessionId] = chatSession
        notifyObservers(EventType.SESSION_CREATED, chatSession)
    }

    fun getSession(sessionId: String) : ChatSession? {
        return sessions[sessionId]
    }

    fun closeSession(sessionId : String) {
        val session = sessions[sessionId] ?: return
        sessions[sessionId] = session.copy(isActive = false)
        notifyObservers(EventType.SESSION_CLOSED, session)
    }
}