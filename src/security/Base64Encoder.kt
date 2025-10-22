package security

import java.util.Base64

class Base64Encoder {
    fun encodeToBase64(text: String): String {
        return Base64.getEncoder().encodeToString(text.toByteArray(Charsets.UTF_8))
    }

    fun decodeFromBase64(encoded: String): String {
        return String(Base64.getDecoder().decode(encoded))
    }
}