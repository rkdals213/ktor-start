package com.example.plugins

import com.example.dto.DepartmentRequest
import com.example.dto.EmployeeRequest
import com.example.repository.Repository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val repository = Repository

    routing {
        route("/department") {
            get("/{departmentId}") {
                val departmentId = call.parameters["departmentId"]
                val findDepartment = departmentId?.let { it1 -> repository.findDepartmentById(it1.toLong()) }

                call.respond(HttpStatusCode.OK, findDepartment.toString())
            }

            post("/register") {
                val department = call.receive<DepartmentRequest>()

                repository.saveDepartment(department.toEntity())
            }
        }

        route("/employee") {
            get("/{employeeId}") {
                val employeeId = call.parameters["employeeId"]
                val employee = employeeId?.let { it1 -> repository.findEmployeeById(it1.toLong()) }

                call.respond(HttpStatusCode.OK, employee.toString())
            }

            post("/register") {
                val employeeRequest = call.receive<EmployeeRequest>()

                val department = repository.findDepartmentById(employeeRequest.departmentId)
                val manager = employeeRequest.managerId?.let { it1 -> repository.findEmployeeById(it1) }

                repository.saveEmployee(employeeRequest.toEntity(manager, department))
            }
        }
    }
}
