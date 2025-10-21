package utils.generators

import java.util.UUID

class IdGenerator {
    fun generateId() : String {
        return UUID.randomUUID().toString()
    }
}