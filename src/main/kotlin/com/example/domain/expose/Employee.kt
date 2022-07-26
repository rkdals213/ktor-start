package com.example.domain.expose

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object Employees : LongIdTable() {
    val name = varchar("name", 50)
    val job = varchar("job", 10)
    val manager = reference("employee", Employees)
    val hireDate = datetime("hireDate")
    val salary = integer("salary")
    val department = reference("department", Departments)
}

class Employee(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Employee>(Employees)

    var name by Employees.name
    var job by Employees.job
    var manager by Employees.manager
    var hireDate by Employees.hireDate
    var salary by Employees.salary
    var department by Employees.department
}
