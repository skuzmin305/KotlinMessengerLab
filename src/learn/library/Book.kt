package learn.library

import learn.library.enums.BookGenre

data class Book(
    val id : String,
    val title : String,
    val author : String,
    val year : Int,
    val genre : BookGenre
) {
}