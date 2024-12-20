package com.copetti.mtg.deck.provider.anki

import com.copetti.mtg.deck.common.extensions.getLogger
import com.copetti.mtg.deck.gateway.CreateDeckProvider
import com.copetti.mtg.deck.gateway.CreateDeckProviderRequest
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

@Component
class AnkiCreateDeckProvider(
    private val ankiCreateFlashCardEntry: AnkiCreateFlashCardEntry
) : CreateDeckProvider {

    private val logger = getLogger()
    override fun create(request: CreateDeckProviderRequest) {

        val process = ProcessBuilder("bin/mtg-anki-deck-creator", request.filePath)
            .redirectErrorStream(true)
            .start()

        writeAllFlashCardsToStream(request, process)
        val data = readAllData(process)
        val exitCode = process.waitFor()

        if (exitCode != 0) {
            data.forEach(logger::error)
            throw RuntimeException("Failed to create Anki deck (exit code: $exitCode).")
        }

        data.forEach(logger::info)
    }

    private fun writeAllFlashCardsToStream(request: CreateDeckProviderRequest, process: Process) {
        val writer = BufferedWriter(OutputStreamWriter(process.outputStream))

        request.flashCards
            .map(ankiCreateFlashCardEntry::create)
            .forEach(writer::write)

        writer.close()
    }

    private fun readAllData(process: Process): List<String> {
        return BufferedReader(InputStreamReader(process.inputStream))
            .readLines()
    }
}