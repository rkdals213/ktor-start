package com.example.domain.expose

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object Departments : LongIdTable("department") {
    val name = varchar("name", 50).default("")
    val location = varchar("location", 1000).default("")
}

class Department(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Department>(Departments)

    var name by Departments.name
    var location by Departments.location
}

@kotlinx.serialization.Serializable
data class DepartmentResponse(
    val name: String,
    val location: String
)
