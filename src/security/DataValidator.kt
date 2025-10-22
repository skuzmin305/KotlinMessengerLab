package security

class DataValidator {
    fun validateMessageText(text: String): Boolean {
        return text.isNotEmpty() && text.length < 1000
    }

    fun validateUserName(userName: String): Boolean {
        if (userName.trim().isEmpty()) return false
        val allowedPattern = Regex("^[а-яА-я a-zA-Z0-9-]*$")
        return  !userName.first().equals(" ")
                && !userName.last().equals(" ")
                && allowedPattern.matches(userName)
                && (userName.length in 2..30)
    }
}