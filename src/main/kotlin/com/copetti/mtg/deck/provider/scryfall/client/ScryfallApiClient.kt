package com.copetti.mtg.deck.provider.scryfall.client

import com.copetti.mtg.deck.provider.scryfall.model.ScryfallBulkDataResponse
import com.copetti.mtg.deck.provider.scryfall.model.ScryfallMagicSet
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "scryfallApiClient", url = "\${scryfall.url}")
interface ScryfallApiClient {

    @GetMapping("/sets/{code}")
    fun retrieveSet(
        @PathVariable code: String
    ): ScryfallMagicSet

    @GetMapping("/bulk-data")
    fun retrieveBulkDataCatalog(): ScryfallBulkDataResponse
}