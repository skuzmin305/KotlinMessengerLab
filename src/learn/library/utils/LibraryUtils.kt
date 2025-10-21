package learn.library.utils

import learn.library.Book
import learn.library.enums.BookGenre

class LibraryUtils {

    fun findBooksByGenre(books : List<Book>, genre : BookGenre) : List<Book> {
        val listBooksOfGenre : MutableList<Book> = mutableListOf()

        for (book in books) {
            when (book.genre) {
                genre -> listBooksOfGenre.add(book)
                else -> {}
            }
        }

        return listBooksOfGenre.toList()
    }
}