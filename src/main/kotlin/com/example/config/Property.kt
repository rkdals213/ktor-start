package com.example.config

import java.util.*

object Property {

     var props = Properties().apply {
        Property::class.java.classLoader.getResourceAsStream("application.properties").use {
            load(it)
        }
    }

    operator fun get(key: String): String = props[key].toString()

}