package com.example.dto

import com.example.domain.Department
import com.example.domain.Employee
import kotlinx.serialization.Contextual
import java.time.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class EmployeeRequest(
    val name: String,
    val job: String,
    val managerId: Long? = null,
    @Contextual
    val hireDate: LocalDate = LocalDate.now(),
    val salary: Long,
    val departmentId: Long
) {
    fun toEntity(manager: Employee?, department: Department): Employee {
        return Employee().entity(name, job, manager, hireDate, salary, department)
    }
}