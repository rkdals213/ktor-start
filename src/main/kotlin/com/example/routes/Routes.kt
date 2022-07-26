package com.example.routes

import com.example.config.DatabaseConnection
import com.example.domain.expose.Department
import com.example.domain.expose.DepartmentResponse
import com.example.dto.DepartmentRequest
import com.example.dto.EmployeeRequest
import com.example.repository.KtOrmRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionManager

fun Route.customerRouting() {
    val ktOrmRepository = KtOrmRepository


    route("/ktorm") {
        route("/department") {
            get("/{departmentId}") {
                val departmentId = call.parameters["departmentId"]
                val findDepartment = departmentId?.let { it1 -> ktOrmRepository.findDepartmentById(it1.toLong()) }

                call.respond(HttpStatusCode.OK, findDepartment.toString())
            }

            post("/register") {
                val department = call.receive<DepartmentRequest>()

                ktOrmRepository.saveDepartment(department.toEntity())

                call.respond(HttpStatusCode.OK)
            }
        }

        route("/employee") {
            get("/{employeeId}") {
                val employeeId = call.parameters["employeeId"]
                val employee = employeeId?.let { it1 -> ktOrmRepository.findEmployeeById(it1.toLong()) }

                call.respond(HttpStatusCode.OK, employee.toString())
            }

            post("/register") {
                val employeeRequest = call.receive<EmployeeRequest>()

                val department = ktOrmRepository.findDepartmentById(employeeRequest.departmentId)
                val manager = employeeRequest.managerId?.let { it1 -> ktOrmRepository.findEmployeeById(it1) }

                ktOrmRepository.saveEmployee(employeeRequest.toEntity(manager, department))

                call.respond(HttpStatusCode.OK)
            }
        }
    }

    route("/exposed") {
        route("/department") {
            get("/{departmentId}") {
                val departmentId = call.parameters["departmentId"]!!.toLong()

                Database.connect(DatabaseConnection.datasource)

                val findDepartment = transaction {
                    addLogger(StdOutSqlLogger)
                    Department.findById(departmentId)
                } ?: throw RuntimeException()

                call.respond(HttpStatusCode.OK, DepartmentResponse(findDepartment.name, findDepartment.location))
            }

            post("/register") {
                val department = call.receive<DepartmentRequest>()

                println(department)

                Database.connect(DatabaseConnection.datasource)

                transaction {
                    addLogger(StdOutSqlLogger)

                    Department.new {
                        name = department.name
                        location = department.location
                    }
                }

                call.respond(HttpStatusCode.OK)
            }
        }

        route("/employee") {
            get("/{employeeId}") {
                val employeeId = call.parameters["employeeId"]
                val employee = employeeId?.let { it1 -> ktOrmRepository.findEmployeeById(it1.toLong()) }

                call.respond(HttpStatusCode.OK, employee.toString())
            }

            post("/register") {
                val employeeRequest = call.receive<EmployeeRequest>()

                val department = ktOrmRepository.findDepartmentById(employeeRequest.departmentId)
                val manager = employeeRequest.managerId?.let { it1 -> ktOrmRepository.findEmployeeById(it1) }

                ktOrmRepository.saveEmployee(employeeRequest.toEntity(manager, department))

                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
