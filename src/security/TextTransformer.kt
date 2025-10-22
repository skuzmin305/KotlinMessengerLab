package security

import kotlin.text.iterator

class TextTransformer {
    fun transformText(text: String): String {
        val builder = StringBuilder()

        for (char : Char in text) {
            if (char == '\n' || char == ' ') {
                builder.append(char)
            } else {
                builder.append(char + 3)
            }
        }

        return builder.toString()
    }

    fun restoreText(transformed: String): String {
        val builder = StringBuilder()

        for (char : Char in transformed) {
            if (char == '\n' || char == ' ') {
                builder.append(char)
            } else {
                builder.append(char - 3)
            }
        }

        return builder.toString()
    }
}