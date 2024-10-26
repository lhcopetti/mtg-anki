package com.copetti.mtganki

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MtgAnkiDeckBuilderApplication(
	private val mtgAnkiDeckBuilderCommandLineRunner: MtgAnkiDeckBuilderCommandLineRunner
) {

	@Bean
	fun runner() = CommandLineRunner {args ->
		mtgAnkiDeckBuilderCommandLineRunner.run(args)
	}
}

fun main(args: Array<String>) {
	runApplication<MtgAnkiDeckBuilderApplication>(*args)
}

