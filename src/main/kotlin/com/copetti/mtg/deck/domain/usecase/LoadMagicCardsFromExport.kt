package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.GameLegality
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
            .filter (this::isArenaLegal)
    }

    private fun isJapaneseCard(magicCard: MagicCard) = magicCard.lang == "ja"

    private fun isStandardLegal(magicCard: MagicCard) = magicCard.legality.standard == Legality.LEGAL

    private fun isArenaLegal(magicCard: MagicCard) = magicCard.games.contains(GameLegality.MAGIC_ARENA)

}