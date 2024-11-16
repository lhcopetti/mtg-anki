package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.mock.MagicCards
import com.copetti.mtg.deck.domain.model.GameLegality
import com.copetti.mtg.deck.domain.model.Legality
import com.copetti.mtg.deck.gateway.MagicCardsProvider
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.util.Streams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LoadMagicCardsTest {

    @MockK
    private lateinit var magicCardsProvider: MagicCardsProvider

    @InjectMockKs
    private lateinit var loadMagicCards: LoadMagicCards

    @Test
    fun `should load the cards from provider`() {
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

        every { magicCardsProvider.loadAll() } returns Streams.stream(cardList)

        val actual = loadMagicCards.load()

        assertThat(actual).isEqualTo(cardList)

        verify { magicCardsProvider.loadAll() }
    }

    @Test
    fun `should filter out cards that are not japanese`() {
        val enCard = MagicCards.givenSingleFacedCard(
            lang = "en",
            set = "dsk",
            standardLegality = Legality.LEGAL
        )
        val jaCard = MagicCards.givenMultiFacedCard(
            lang = "ja",
            set = "blb",
            standardLegality = Legality.LEGAL
        )
        val cardList = listOf(enCard, jaCard)

        every { magicCardsProvider.loadAll() } returns Streams.stream(cardList)

        val actual = loadMagicCards.load()
        val expected = listOf(jaCard)

        assertThat(actual).isEqualTo(expected)

        verify { magicCardsProvider.loadAll() }
    }

    @Test
    fun `should filter out cards that are not standard legal`() {
        val notLegalInStandard = MagicCards.givenSingleFacedCard(
            lang = "ja",
            set = "dsk",
            standardLegality = Legality.NOT_LEGAL
        )
        val legalInStandard = MagicCards.givenMultiFacedCard(
            lang = "ja",
            set = "blb",
            standardLegality = Legality.LEGAL
        )
        val cardList = listOf(notLegalInStandard, legalInStandard)

        every { magicCardsProvider.loadAll() } returns Streams.stream(cardList)

        val actual = loadMagicCards.load()
        val expected = listOf(legalInStandard)

        assertThat(actual).isEqualTo(expected)

        verify { magicCardsProvider.loadAll() }
    }

    @Test
    fun `should filter out cards that are not legal in magic arena`() {
        val notLegalInArena = MagicCards.givenSingleFacedCard(
            lang = "ja",
            set = "dsk",
            standardLegality = Legality.NOT_LEGAL,
            games = setOf(GameLegality.PAPER, GameLegality.MTG_ONLINE)
        )
        val legalInArena = MagicCards.givenMultiFacedCard(
            lang = "ja",
            set = "blb",
            standardLegality = Legality.LEGAL,
            games = setOf(GameLegality.MAGIC_ARENA)
        )
        val cardList = listOf(notLegalInArena, legalInArena)

        every { magicCardsProvider.loadAll() } returns Streams.stream(cardList)

        val actual = loadMagicCards.load()
        val expected = listOf(legalInArena)

        assertThat(actual).isEqualTo(expected)

        verify { magicCardsProvider.loadAll() }
    }

}