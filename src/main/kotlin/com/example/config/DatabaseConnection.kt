package com.example.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

object DatabaseConnection {
    private val config = HikariConfig().apply {
        jdbcUrl = "jdbc:mysql://localhost:3306/ktorm"
        driverClassName = "com.mysql.cj.jdbc.Driver"
        username = "root"
        password = "1234"
    }

    private val datasource = HikariDataSource(config)

    val database = Database.connect(
        dataSource = datasource,
        logger = ConsoleLogger(threshold = LogLevel.DEBUG)
    )
}