package ru.itmo.soa.spacemarine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
class SpacemarineApplication

fun main(args: Array<String>) {
    runApplication<SpacemarineApplication>(*args)
}
