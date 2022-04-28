package com.example.config

import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

object DatabaseConnection {
    val database: Database = Database.connect(
        url = "jdbc:mysql://localhost:3306/ktorm",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "1234",
        logger = ConsoleLogger(threshold = LogLevel.DEBUG)
    )
}