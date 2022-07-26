package com.example.repository

import com.example.config.DatabaseConnection
import com.example.domain.ktorm.Department
import com.example.domain.ktorm.Employee
import com.example.domain.ktorm.departments
import com.example.domain.ktorm.employees
import org.ktorm.dsl.eq
import org.ktorm.entity.*

object KtOrmRepository {
    private val database = DatabaseConnection.database

    fun findDepartmentById(id: Long): Department = database.departments.find { it.id eq id } ?: throw RuntimeException()

    fun findEmployeeById(id: Long): Employee = database.employees.find { it.id eq id } ?: throw RuntimeException()

    fun saveDepartment(department: Department) = database.departments.add(department)

    fun saveEmployee(employee: Employee) = database.employees.add(employee)
}