package learn.library

class LibraryManager private constructor() {
    companion object {
        val instance = LibraryManager()
    }

    private val books: MutableMap<String, Book> = mutableMapOf()

    fun addBook(book: Book) {
        books[book.id] = book
    }

    fun findBooksByAuthor(author: String) : List<Book> {
        return books.filter { it.value.author.equals(author, true) }.values.toList()
    }
}
