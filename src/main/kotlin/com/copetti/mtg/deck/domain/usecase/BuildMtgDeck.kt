package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.FlashCard
import com.copetti.mtg.deck.domain.model.VocabularyStudyCard
import com.copetti.mtg.deck.gateway.CreateDeckProvider
import com.copetti.mtg.deck.gateway.CreateDeckProviderRequest
import com.copetti.mtg.deck.gateway.LoadMagicCardsExportProvider
import org.springframework.stereotype.Component

@Component
class BuildMtgDeck(
    val loadMagicCardsFromExport: LoadMagicCardsFromExport,
    val processMagicCards: ProcessMagicCards,
    val createFlashCard: CreateFlashCard,
    val createDeckProvider: CreateDeckProvider
) {

    fun buildDeck(inputFilePath: String, exportFilePath: String) {
        val magicCards = loadMagicCardsFromExport.load(inputFilePath)
        val studyCards = processMagicCards.process(magicCards)
        val flashCardEntries = createFlashCards(studyCards)
        createDeckProvider.create(CreateDeckProviderRequest(exportFilePath, flashCardEntries))
    }

    private fun createFlashCards(studyCard: Set<VocabularyStudyCard>): List<FlashCard> {
        return studyCard.map(::CreateFlashCardEntryRequest)
            .map(createFlashCard::create)
    }
}