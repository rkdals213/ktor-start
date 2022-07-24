package com.example

import com.example.config.Property
import com.example.routes.customerRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.slf4j.event.Level
import java.util.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    val env = environment.config.propertyOrNull("ktor.environment")?.getString()

    when (env) {
        "prod" -> Property.apply {
            props = Properties().apply {
                Property::class.java.classLoader.getResourceAsStream("ktor-start.properties").use {
                    load(it)
                }
            }
        }
        else -> Property.apply {
            props = Properties().apply {
                Property::class.java.classLoader.getResourceAsStream("application.properties").use {
                    load(it)
                }
            }
        }
    }

    routing {
        customerRouting()
    }

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
}