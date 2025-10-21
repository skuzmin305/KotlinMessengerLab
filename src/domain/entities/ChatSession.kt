package domain.entities

data class ChatSession(
    val id : String,
    val user1   : User,
    val user2 : User,
    val createdAt : Long,
    val isActive: Boolean
) {
}