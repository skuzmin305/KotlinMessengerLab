package learn.library.utils

import java.util.UUID

class IdGenerator {
    fun generateId() : String {
        return UUID.randomUUID().toString()
    }
}