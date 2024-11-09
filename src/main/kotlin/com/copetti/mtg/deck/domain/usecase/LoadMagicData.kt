package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.MagicData
import org.springframework.stereotype.Service

@Service
class LoadMagicData(
    private val loadMagicCardsFromExport: LoadMagicCardsFromExport,
    private val loadMagicCardSets: LoadMagicCardSets
) {

    fun load(inputFilePath: String): MagicData {
        val magicCards = loadMagicCardsFromExport.load(inputFilePath)
        val magicSets = loadMagicCardSets.load(magicCards)
        return MagicData(magicCards, magicSets)
    }
}