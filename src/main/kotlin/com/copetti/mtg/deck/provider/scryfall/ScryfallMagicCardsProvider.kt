package com.copetti.mtg.deck.provider.scryfall

import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.gateway.MagicCardsProvider
import com.copetti.mtg.deck.provider.scryfall.client.ScryfallBulkDataClient
import com.copetti.mtg.deck.provider.scryfall.client.ScryfallApiClient
import org.springframework.stereotype.Component
import java.util.stream.Stream

@Component
class ScryfallMagicCardsProvider(
    private val client: ScryfallApiClient,
    private val bulkDataClient: ScryfallBulkDataClient,
    private val mapper: ScryfallCardMapper
) : MagicCardsProvider {

    override fun loadAll(): Stream<MagicCard> {

        val bulkDataDownloadUri =
            retrieveDownloadUrl() ?: throw IllegalArgumentException("Bulk data for all cards was not found")

        return bulkDataClient.loadAllCards(bulkDataDownloadUri)
            .map(mapper::toMagicCard)
    }

    private fun retrieveDownloadUrl() = client.retrieveBulkDataCatalog()
        .data.firstOrNull { data -> data.type == BULK_DATA_ALL_CARDS_TYPE }
        ?.downloadUri

    companion object {
        private const val BULK_DATA_ALL_CARDS_TYPE = "all_cards"
    }
}