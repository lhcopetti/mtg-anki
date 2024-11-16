package com.copetti.mtg.deck.provider.scryfall

import com.copetti.mtg.deck.domain.mock.MagicCards
import com.copetti.mtg.deck.provider.scryfall.client.ScryfallApiClient
import com.copetti.mtg.deck.provider.scryfall.client.ScryfallBulkDataClient
import com.copetti.mtg.deck.provider.scryfall.model.ScryfallBulkData
import com.copetti.mtg.deck.provider.scryfall.model.ScryfallBulkDataResponse
import com.copetti.mtg.deck.provider.scryfall.model.ScryfallMagicCard
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.stream.Stream

@ExtendWith(MockKExtension::class)
class ScryfallMagicCardsProviderTest {

    @MockK
    private lateinit var client: ScryfallApiClient

    @MockK
    private lateinit var bulkDataClient: ScryfallBulkDataClient

    @MockK
    private lateinit var mapper: ScryfallCardMapper

    @InjectMockKs
    private lateinit var scryfallMagicCardsProvider: ScryfallMagicCardsProvider

    @Test
    fun `should load all cards from the bulk data catalog and map them correctly`() {
        val bulkDataUrl = "the-url"

        val bulkDataCatalogResponse = ScryfallBulkDataResponse(
            data = listOf(
                ScryfallBulkData(type = "any-type", downloadUri = "any-download-url"),
                ScryfallBulkData(type = "all_cards", downloadUri = bulkDataUrl),
            )
        )
        every { client.retrieveBulkDataCatalog() } returns bulkDataCatalogResponse

        val firstCard: ScryfallMagicCard = mockk(ScryfallMagicCard::class.java.toString())
        val secondCard: ScryfallMagicCard = mockk(ScryfallMagicCard::class.java.toString())
        val cards = Stream.of(firstCard, secondCard)
        every { bulkDataClient.loadAllCards(any()) } returns cards

        val firstMagicCard = MagicCards.givenSingleFacedCard(name = "first")
        val secondMagicCard = MagicCards.givenSingleFacedCard(name = "second")
        every { mapper.toMagicCard(any()) } returns firstMagicCard andThen secondMagicCard

        val result = scryfallMagicCardsProvider.loadAll()


        val expected = listOf(firstMagicCard, secondMagicCard)
        assertThat(result).hasSameElementsAs(expected)

        verify { client.retrieveBulkDataCatalog() }
        verify { bulkDataClient.loadAllCards(bulkDataUrl) }
        verify { mapper.toMagicCard(firstCard) }
        verify { mapper.toMagicCard(secondCard) }
    }

}