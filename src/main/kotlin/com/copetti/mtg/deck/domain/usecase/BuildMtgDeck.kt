package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.common.extensions.getLogger
import com.copetti.mtg.deck.domain.model.FlashCard
import com.copetti.mtg.deck.domain.model.VocabularyStudyCard
import com.copetti.mtg.deck.gateway.CreateDeckProvider
import com.copetti.mtg.deck.gateway.CreateDeckProviderRequest
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.time.LocalDateTime
import kotlin.io.path.Path

@Component
class BuildMtgDeck(
    val loadMagicData: LoadMagicData,
    val processMagicData: ProcessMagicData,
    val createFlashCard: CreateFlashCard,
    val createDeckProvider: CreateDeckProvider
) {

    private val log = getLogger()

    fun buildDeck(exportFilePath: String) {
        log.info("Beginning construction of magic deck | outputFilePath: $exportFilePath")
        val magicData = loadMagicData.load()
        log.info("Magic data loaded successfully | cards: #${magicData.cards.size}, sets: #${magicData.sets.size}")
        val studyCards = processMagicData.process(magicData)
        log.info("Study cards created successfully | studyCards: #${studyCards.size}")
        val flashCardEntries = createFlashCards(studyCards)
        log.info("Flash card creation completed")
        createDeckProvider.create(CreateDeckProviderRequest(exportFilePath, flashCardEntries))
        log.info("Deck created successfully | outputFilePath: $exportFilePath")
    }

    private fun createFlashCards(studyCard: Set<VocabularyStudyCard>): List<FlashCard> {
        return studyCard.map(::CreateFlashCardEntryRequest)
            .map(createFlashCard::create)
    }
}