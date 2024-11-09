package com.copetti.mtg.deck

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableFeignClients
class MtgAnkiDeckBuilderApplication(
    private val mtgAnkiDeckBuilderCommandLineRunner: MtgAnkiDeckBuilderCommandLineRunner
) {

    @Bean
    fun runner() = CommandLineRunner { args ->
        mtgAnkiDeckBuilderCommandLineRunner.run(args)
    }
}

fun main(args: Array<String>) {
    runApplication<MtgAnkiDeckBuilderApplication>(*args)
}

