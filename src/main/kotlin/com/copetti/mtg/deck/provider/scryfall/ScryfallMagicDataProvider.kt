package com.copetti.mtg.deck.provider.scryfall

import com.copetti.mtg.deck.common.extensions.getLogger
import com.copetti.mtg.deck.domain.model.MagicSet
import com.copetti.mtg.deck.gateway.MagicSetDataProvider
import com.copetti.mtg.deck.provider.scryfall.client.ScryfallApiClient
import org.springframework.stereotype.Component

@Component
class ScryfallMagicDataProvider(
    private val client: ScryfallApiClient
) : MagicSetDataProvider {

    private val log = getLogger()
    override fun retrieveSet(setCode: String): MagicSet {

        log.info("Retrieving set information | set: $setCode")
        val scryfallSetData = client.retrieveSet(setCode)
        return MagicSet(
            code = scryfallSetData.code,
            name = scryfallSetData.name,
            releaseDate = scryfallSetData.releasedAt
        ).also { log.info("Retrieved set information successfully | setCode: ${it.code}, name: ${it.name}, releaseDate: ${it.releaseDate}") }
    }
}