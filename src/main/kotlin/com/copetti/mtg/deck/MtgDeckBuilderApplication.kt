package com.copetti.mtg.deck

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableFeignClients
class MtgDeckBuilderApplication(
    private val mtgDeckBuilderCommandLineRunner: MtgDeckBuilderCommandLineRunner
) {

    @Bean
    fun runner() = CommandLineRunner { args ->
        mtgDeckBuilderCommandLineRunner.run(args)
    }
}

fun main(args: Array<String>) {
    runApplication<MtgDeckBuilderApplication>(*args)
}

