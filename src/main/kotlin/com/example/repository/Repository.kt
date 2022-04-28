package com.example.repository

import com.example.config.DatabaseConnection
import com.example.domain.Department
import com.example.domain.Employee
import com.example.domain.departments
import com.example.domain.employees
import org.ktorm.dsl.eq
import org.ktorm.entity.*

object Repository {
    private val database = DatabaseConnection.database

    fun findDepartmentById(id: Long): Department = database.departments.find { it.id eq id } ?: throw RuntimeException()

    fun findEmployeeById(id: Long): Employee = database.employees.find { it.id eq id } ?: throw RuntimeException()

    fun saveDepartment(department: Department) = database.departments.add(department)

    fun saveEmployee(employee: Employee) = database.employees.add(employee)
}