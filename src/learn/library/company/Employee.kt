package learn.library.company

import learn.library.enums.Department

data class Employee(
    val id: String,
    val name: String,
    val department: Department,
    val salary: Int
)