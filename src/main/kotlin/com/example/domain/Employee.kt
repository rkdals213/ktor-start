package com.example.domain

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*
import java.time.LocalDate

val Database.employees
    get() = sequenceOf(Employees)

interface Employee : Entity<Employee> {
    companion object : Entity.Factory<Employee>()

    val id: Long
    var name: String
    var job: String
    var manager: Employee?
    var hireDate: LocalDate
    var salary: Long
    var department: Department

    fun entity(name: String, job: String, manager: Employee?, hireDate: LocalDate, salary: Long, department: Department): Employee {
        this.name = name
        this.job = job
        this.manager = manager
        this.hireDate = hireDate
        this.salary = salary
        this.department = department

        return this
    }
}

object Employees : Table<Employee>("employee") {
    val id = long("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val job = varchar("job").bindTo { it.job }
    val managerId = long("manager_id").bindTo { it.manager?.id }
    val hireDate = date("hire_date").bindTo { it.hireDate }
    val salary = long("salary").bindTo { it.salary }
    val departmentId = int("department_id").references(Departments) { it.department }
}