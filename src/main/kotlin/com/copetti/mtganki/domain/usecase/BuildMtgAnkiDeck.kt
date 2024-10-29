package com.copetti.mtganki.domain.usecase

import com.copetti.mtganki.domain.model.FlashCard
import com.copetti.mtganki.domain.model.VocabularyStudyCard
import com.copetti.mtganki.gateway.CreateDeckProvider
import com.copetti.mtganki.gateway.CreateDeckProviderRequest
import com.copetti.mtganki.provider.scryfall.ScryfallMagicCardMapper
import org.springframework.stereotype.Component

@Component
class BuildMtgAnkiDeck(
    val readScryfallExportFile: ReadScryfallExportFile,
    val scryfallMagicCardMapper: ScryfallMagicCardMapper,
    val processMagicCards: ProcessMagicCards,
    val createFlashCard: CreateFlashCard,
    val createDeckProvider: CreateDeckProvider
) {

    fun buildDeck(inputFilePath: String, exportFilePath: String) {
        val scryfallMagicCards = readScryfallExportFile.loadCards(inputFilePath)
        val magicCards = scryfallMagicCards.map(scryfallMagicCardMapper::toMagicCard)
        val studyCards = processMagicCards.process(magicCards)
        val flashCardEntries = createFlashCards(studyCards)
        createDeckProvider.create(CreateDeckProviderRequest(exportFilePath, flashCardEntries))
    }

    private fun createFlashCards(studyCard: Set<VocabularyStudyCard>): List<FlashCard> {
        return studyCard.map(::CreateFlashCardEntryRequest)
            .map(createFlashCard::create)
    }
}