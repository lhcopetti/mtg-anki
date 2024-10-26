package com.copetti.mtganki

import com.copetti.mtganki.domain.processor.MagicCardProcessor
import com.copetti.mtganki.provider.scryfall.ScryfallMagicMapper
import org.springframework.stereotype.Component
import java.io.File

@Component
class MtgAnkiDeckBuilder(
    val scryfallMagicCardFileReader: ScryfallMagicCardFileReader,
    val scryfallMagicMapper: ScryfallMagicMapper,
    val magicCardProcessor: MagicCardProcessor
) {

    fun buildDeck(inputFilePath: String, exportFilePath: String) {
        val scryfallMagicCards = scryfallMagicCardFileReader.loadCards(inputFilePath)
        val magicCards = scryfallMagicCards.map(scryfallMagicMapper::toMagicCard)
        val allText = magicCardProcessor.process(magicCards)
        File(exportFilePath).writeText(allText)
    }
}