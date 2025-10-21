package learn.library.utils
import java.time.Year

import learn.library.Book
import learn.library.BookGenre

class BookUtils {
    fun createDefaultBook() : Book {
        return Book(
            id = "1",
            title = "Another book",
            author = "Another author",
            year = Year.now().value.toInt(),
            genre = BookGenre.FICTION
        )
    }
}