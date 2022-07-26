package com.example.dto

import com.example.domain.ktorm.Department
import com.example.domain.ktorm.Employee
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class EmployeeRequest(
    val name: String,
    val job: String,
    val managerId: Long? = null,
    @Contextual
    val hireDate: LocalDateTime,
    val salary: Long,
    val departmentId: Long
) {
    fun toEntity(manager: Employee?, department: Department): Employee {
        return Employee().entity(name, job, manager, hireDate.of(), salary, department)
    }
}


fun LocalDateTime.of(): java.time.LocalDateTime {
    return java.time.LocalDateTime.of(
        this.year,
        this.month,
        this.dayOfMonth,
        this.hour,
        this.minute,
        this.second
    )
}
