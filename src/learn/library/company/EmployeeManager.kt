package learn.library.company

import learn.library.enums.Department

class EmployeeManager private constructor() {
    companion object {
        val instance = EmployeeManager()
    }

    val employees: MutableList<Employee> = mutableListOf()

    fun hireEmployee(employee: Employee) {
        employees.add(employee)
    }

    fun findEmployeesByDepartment(department: Department) : List<Employee>{
        return employees.filter { it.department.equals(department) }
    }
}