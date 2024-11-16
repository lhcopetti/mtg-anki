package com.copetti.mtg.deck.provider.scryfall.client

import com.copetti.mtg.deck.provider.scryfall.model.ScryfallMagicCard
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class ScryfallBulkDataClient {
    fun loadAllCards(downloadUrl: String) = WebClient.builder().build()
        .get()
        .uri(downloadUrl)
        .retrieve()
        .bodyToFlux(ScryfallMagicCard::class.java)
        .toStream()
}