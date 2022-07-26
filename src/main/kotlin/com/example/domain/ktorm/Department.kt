package com.example.domain.ktorm

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

val Database.departments
    get() = sequenceOf(Departments)

interface Department : Entity<Department> {
    companion object : Entity.Factory<Department>()

    val id: Long
    var name: String
    var location: String
}

object Departments : Table<Department>("department") {
    val id = long("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val location = varchar("location").bindTo { it.location }
}