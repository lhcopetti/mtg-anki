package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.mock.FlashCards
import com.copetti.mtg.deck.domain.mock.MagicCards
import com.copetti.mtg.deck.domain.mock.MagicSets
import com.copetti.mtg.deck.domain.mock.VocabularyStudyCards
import com.copetti.mtg.deck.domain.model.MagicData
import com.copetti.mtg.deck.gateway.CreateDeckProviderRequest
import com.copetti.mtg.deck.provider.anki.AnkiCreateDeckProvider
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class BuildMtgDeckTest {

    @MockK
    private lateinit var loadMagicData: LoadMagicData

    @MockK
    private lateinit var processMagicData: ProcessMagicData

    @MockK
    private lateinit var createFlashCard: CreateFlashCard

    @MockK
    private lateinit var createDeckProvider: AnkiCreateDeckProvider

    @InjectMockKs
    private lateinit var buildMtgDeck: BuildMtgDeck

    @Test
    fun `should correctly load process and create the deck`() {
        val outputFilePath = "the-output"
        val magicData = MagicData(
            listOf(MagicCards.givenMultiFacedCard()),
            sets = listOf(MagicSets.givenMagicSet())
        )

        val firstVocabularyCard = VocabularyStudyCards.givenVocabularyStudyCard()
        val secondVocabularyCard = VocabularyStudyCards.givenVocabularyStudyCard()

        val vocabularyStudyCards = setOf(firstVocabularyCard, secondVocabularyCard)
        val firstFlashCard = FlashCards.givenFlashCard(front = "first")
        val secondFlashCard = FlashCards.givenFlashCard(front = "first")

        every { loadMagicData.load() } returns magicData
        every { processMagicData.process(any()) } returns vocabularyStudyCards

        val firstFlashCardRequest = CreateFlashCardEntryRequest(firstVocabularyCard)
        every { createFlashCard.create(firstFlashCardRequest) } returns firstFlashCard

        val secondFlashCardRequest = CreateFlashCardEntryRequest(secondVocabularyCard)
        every { createFlashCard.create(secondFlashCardRequest) } returns secondFlashCard

        every { createDeckProvider.create(any()) } returns Unit

        buildMtgDeck.buildDeck(outputFilePath)

        verify { loadMagicData.load() }
        verify { processMagicData.process(magicData) }
        verify { createFlashCard.create(firstFlashCardRequest) }
        verify { createFlashCard.create(secondFlashCardRequest) }

        val expectedCreateDeckRequest = CreateDeckProviderRequest(
            filePath = outputFilePath,
            flashCards = listOf(firstFlashCard, secondFlashCard)
        )
        verify { createDeckProvider.create(expectedCreateDeckRequest) }
    }
}