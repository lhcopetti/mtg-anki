package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.common.extensions.getLogger
import com.copetti.mtg.deck.domain.model.GameLegality
import com.copetti.mtg.deck.domain.model.Legality
import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.gateway.MagicCardsProvider
import org.springframework.stereotype.Service

@Service
class LoadMagicCards(
    private val magicCardsProvider: MagicCardsProvider
) {

    private val log = getLogger()

    fun load(): List<MagicCard> {
        return magicCardsProvider.loadAll()
            .filter(this::isJapaneseCard)
            .filter(this::isStandardLegal)
            .filter(this::isArenaLegal)
            .toList()
            .also { log.info("Loading all magic cards completed | size: ${it.size}") }
    }

    private fun isJapaneseCard(magicCard: MagicCard) = magicCard.lang == "ja"

    private fun isStandardLegal(magicCard: MagicCard) = magicCard.legality.standard == Legality.LEGAL

    private fun isArenaLegal(magicCard: MagicCard) = magicCard.games.contains(GameLegality.MAGIC_ARENA)

}