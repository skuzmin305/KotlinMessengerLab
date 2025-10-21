package domain.entities

import domain.enums.UserStatus

data class User (
    val id : String,
    val nickName : String,
    val status : UserStatus
)