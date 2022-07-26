package com.example.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel

object DatabaseConnection {
    private val config = HikariConfig().apply {
        println(Property["db.jdbcUrl"])
        jdbcUrl = Property["db.jdbcUrl"]
        driverClassName = Property["db.driverClassName"]
        username = Property["db.username"]
        password = Property["db.password"]
    }

    val datasource = HikariDataSource(config)

    val database = Database.connect(
        dataSource = datasource,
        logger = ConsoleLogger(threshold = LogLevel.DEBUG)
    )
}