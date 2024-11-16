package com.copetti.mtg.deck

import com.copetti.mtg.deck.common.extensions.getLogger
import com.copetti.mtg.deck.domain.usecase.BuildMtgDeck
import org.springframework.boot.ExitCodeGenerator
import org.springframework.boot.SpringApplication
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import kotlin.system.exitProcess


@Component
class MtgDeckBuilderCommandLineRunner(
    private val buildMtgDeck: BuildMtgDeck,
    private val applicationContext: ApplicationContext
) {

    private val logger = getLogger()

    fun run(args: Array<String>) {
        try {
            doRun(args)
        } catch (t: Throwable) {
            logger.error("Exception during mtg deck build process", t)
            SpringApplication.exit(applicationContext, ExitCodeGenerator { 1 })
            exitProcess(1)
        }
    }

    private fun doRun(args: Array<String>) {

        if (args.size != 1) {
            println("Please, specify the output file path correctly")
            println("mvn spring-boot:run <output-deck>")
            throw IllegalArgumentException()
        }

        val outputFilePath = args[0]
        buildMtgDeck.buildDeck(outputFilePath)

    }
}