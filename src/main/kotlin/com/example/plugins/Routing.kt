package com.example.plugins

import com.example.repository.Repository
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.configureRouting() {
    val repository = Repository

    routing {
        get("/department/{departmentId}") {
            val departmentId = call.parameters["departmentId"]
            val findDepartment = departmentId?.let { it1 -> repository.findDepartmentById(it1.toLong()) }

            call.respond(HttpStatusCode.OK, findDepartment.toString())
        }

        get("/employee/{employeeId}") {
            val employeeId = call.parameters["employeeId"]
            val employee = employeeId?.let { it1 -> repository.findEmployeeById(it1.toLong()) }

            call.respond(HttpStatusCode.OK, employee.toString())
        }
    }
}
