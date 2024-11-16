package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.MagicData
import org.springframework.stereotype.Service

@Service
class LoadMagicData(
    private val loadMagicCards: LoadMagicCards,
    private val loadMagicCardSets: LoadMagicCardSets
) {

    fun load(): MagicData {
        val magicCards = loadMagicCards.load()
        val magicSets = loadMagicCardSets.load(magicCards)
        return MagicData(magicCards, magicSets)
    }
}