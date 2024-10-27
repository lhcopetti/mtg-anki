package com.copetti.mtganki.provider.anki

import com.copetti.mtganki.common.extensions.getLogger
import com.copetti.mtganki.gateway.CreateDeckProvider
import com.copetti.mtganki.gateway.CreateDeckProviderRequest
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

        val process = ProcessBuilder("python", "scripts/create-anki-deck.py")
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
            .forEach{ writer.write(it)}

        writer.close()
    }

    private fun readAllData(process: Process): List<String> {
        return BufferedReader(InputStreamReader(process.inputStream))
            .readLines()
    }
}