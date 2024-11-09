package com.copetti.mtg.deck.provider.scryfall

import com.copetti.mtg.deck.domain.model.MagicSet
import com.copetti.mtg.deck.gateway.MagicSetDataProvider
import com.copetti.mtg.deck.provider.scryfall.client.ScryfallMagicSetClient
import org.springframework.stereotype.Component

@Component
class ScryfallMagicDataProvider(
    private val client: ScryfallMagicSetClient
) : MagicSetDataProvider {
    override fun retrieveSet(setCode: String): MagicSet {

        val scryfallSetData = client.retrieveSet(setCode)
        return MagicSet(
            code = scryfallSetData.code,
            name = scryfallSetData.name,
            releaseDate = scryfallSetData.releasedAt
        )
    }
}