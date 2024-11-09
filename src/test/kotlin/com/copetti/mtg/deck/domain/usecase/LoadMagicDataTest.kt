package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.mock.MagicCards
import com.copetti.mtg.deck.domain.mock.MagicSets
import com.copetti.mtg.deck.domain.model.MagicData
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LoadMagicDataTest {
    @MockK
    private lateinit var loadMagicCardsFromExport: LoadMagicCardsFromExport
    @MockK
    private lateinit var loadMagicCardSets: LoadMagicCardSets
    @InjectMockKs
    private lateinit var loadMagicData: LoadMagicData

    @Test
    fun `should load all cards and sets correctly`() {
        val cards = listOf(
            MagicCards.givenSingleFacedCard(),
            MagicCards.givenMultiFacedCard()
        )
        val sets = listOf(
            MagicSets.givenMagicSet(code = "first-set"),
            MagicSets.givenMagicSet(code = "second-set"),
        )
        val inputFilePath = "the-input-file-path"

        every { loadMagicCardsFromExport.load(any()) } returns cards
        every { loadMagicCardSets.load(any()) } returns sets

        val actual = loadMagicData.load(inputFilePath)
        val expected = MagicData(
            cards = cards,
            sets = sets
        )
        assertThat(actual).isEqualTo(expected)

        verify { loadMagicCardsFromExport.load(inputFilePath) }
        verify { loadMagicCardSets.load(cards) }
    }
}