package com.copetti.mtg.deck.provider.scryfall.client

import com.copetti.mtg.deck.common.extensions.getLogger
import com.copetti.mtg.deck.provider.scryfall.model.ScryfallMagicCard
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.util.stream.Stream

@Component
class ScryfallBulkDataClient {

    private val log = getLogger()

    fun loadAllCards(downloadUrl: String): Stream<ScryfallMagicCard> {
        log.info("Downloading all scryfall cards | downloadUrl: $downloadUrl")
        return WebClient.builder().build()
            .get()
            .uri(downloadUrl)
            .retrieve()
            .bodyToFlux(ScryfallMagicCard::class.java)
            .doOnComplete { log.info("The download of all scryfall cards completed successfully") }
            .toStream()
    }
}