package com.example.dto

import com.example.domain.ktorm.Department
import kotlinx.serialization.Serializable

@Serializable
data class DepartmentRequest(
    var name: String,
    var location: String
) {

    fun toEntity(): Department {
        val department = Department()
        department.name = name
        department.location = location
        return department
    }
}