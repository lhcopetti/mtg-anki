package com.copetti.mtganki

import com.copetti.mtganki.domain.CreateFlashCardEntry
import com.copetti.mtganki.domain.CreateFlashCardEntryRequest
import com.copetti.mtganki.domain.FlashCard
import com.copetti.mtganki.domain.card.VocabularyStudyCard
import com.copetti.mtganki.domain.processor.MagicCardProcessor
import com.copetti.mtganki.gateway.CreateDeckProvider
import com.copetti.mtganki.gateway.CreateDeckProviderRequest
import com.copetti.mtganki.provider.scryfall.ScryfallMagicMapper
import org.springframework.stereotype.Component

@Component
class MtgAnkiDeckBuilder(
    val scryfallMagicCardFileReader: ScryfallMagicCardFileReader,
    val scryfallMagicMapper: ScryfallMagicMapper,
    val magicCardProcessor: MagicCardProcessor,
    val createFlashCardEntry: CreateFlashCardEntry,
    val createDeckProvider: CreateDeckProvider
) {

    fun buildDeck(inputFilePath: String, exportFilePath: String) {
        val scryfallMagicCards = scryfallMagicCardFileReader.loadCards(inputFilePath)
        val magicCards = scryfallMagicCards.map(scryfallMagicMapper::toMagicCard)
        val studyCards = magicCardProcessor.process(magicCards)
        val flashCardEntries = createFlashCards(studyCards)
        createDeckProvider.create(CreateDeckProviderRequest(exportFilePath, flashCardEntries))
    }

    private fun createFlashCards(studyCard: Set<VocabularyStudyCard>): List<FlashCard> {
        return studyCard.map(::CreateFlashCardEntryRequest)
            .map(createFlashCardEntry::create)
    }
}