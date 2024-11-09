package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.Legality
import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.gateway.LoadMagicCardsExportProvider
import org.springframework.stereotype.Service

@Service
class LoadMagicCardsFromExport(
    private val loadMagicCardsExportProvider: LoadMagicCardsExportProvider
) {

    fun load(inputFilePath: String): List<MagicCard> {
        return loadMagicCardsExportProvider.loadAll(inputFilePath)
            .filter (this::isJapaneseCard)
            .filter (this::isStandardLegal)
    }

    private fun isJapaneseCard(magicCard: MagicCard) = magicCard.lang == "ja"

    private fun isStandardLegal(magicCard: MagicCard) = magicCard.legality.standard == Legality.LEGAL
}