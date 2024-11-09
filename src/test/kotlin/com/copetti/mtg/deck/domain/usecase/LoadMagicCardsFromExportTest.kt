package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.mock.MagicCards
import com.copetti.mtg.deck.domain.model.Legality
import com.copetti.mtg.deck.gateway.LoadMagicCardsExportProvider
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LoadMagicCardsFromExportTest {

    @MockK
    private lateinit var loadMagicCardsExportProvider: LoadMagicCardsExportProvider

    @InjectMockKs
    private lateinit var loadMagicCardsFromExport: LoadMagicCardsFromExport

    @Test
    fun `should load the cards from export`() {
        val inputFilePath = "the-input-file-path"
        val cardList = listOf(
            MagicCards.givenSingleFacedCard(
                lang = "ja",
                set = "dsk",
                standardLegality = Legality.LEGAL
            ),
            MagicCards.givenMultiFacedCard(
                lang = "ja",
                set = "blb",
                standardLegality = Legality.LEGAL
            )
        )

        every { loadMagicCardsExportProvider.loadAll(any()) } returns cardList

        val actual = loadMagicCardsFromExport.load(inputFilePath)

        assertThat(actual).isEqualTo(cardList)

        verify { loadMagicCardsExportProvider.loadAll(inputFilePath) }

    }

}