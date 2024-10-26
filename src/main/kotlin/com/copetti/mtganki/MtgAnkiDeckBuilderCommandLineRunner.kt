package com.copetti.mtganki

import org.springframework.boot.ExitCodeGenerator
import org.springframework.boot.SpringApplication
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import java.io.File
import kotlin.system.exitProcess


@Component
class MtgAnkiDeckBuilderCommandLineRunner(
    private val mtgAnkiDeckBuilder: MtgAnkiDeckBuilder,
    private val applicationContext: ApplicationContext
) {


    fun run(args: Array<String>) {
        try {
            doRun(args)
        } catch (t: Throwable) {
            println("Exception during mtg-anki deck builder: ${t.message}")
            SpringApplication.exit(applicationContext, ExitCodeGenerator { 1 })
            exitProcess(1)
        }
    }

    private fun doRun(args: Array<String>) {

        if (args.size != 2) {
            println("Please, specify the input and output file paths correctly")
            println("mvn spring-boot:run <input-card-dump> <output-deck>")
            throw IllegalArgumentException()
        }

        val inputFilePath = args[0]
        if (!File(inputFilePath).exists()) {
            println("Invalid input file path: $inputFilePath")
            throw IllegalArgumentException()
        }

        val outputFilePath = args[1]
        mtgAnkiDeckBuilder.buildDeck(inputFilePath, outputFilePath)

    }
}