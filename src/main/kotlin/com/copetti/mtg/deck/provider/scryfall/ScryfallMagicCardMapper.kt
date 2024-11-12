package com.copetti.mtg.deck.provider.scryfall

import com.copetti.mtg.deck.domain.model.DualLanguageText
import com.copetti.mtg.deck.domain.model.FormatLegality
import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.domain.model.MagicCardFace
import com.copetti.mtg.deck.provider.scryfall.model.ScryfallGame
import com.copetti.mtg.deck.provider.scryfall.model.ScryfallMagicCard
import org.springframework.stereotype.Component

@Component
class ScryfallMagicCardMapper {


    fun toMagicCard(scryfallMagicCard: ScryfallMagicCard) = MagicCard(
        id = scryfallMagicCard.id,
        set = scryfallMagicCard.set,
        lang = scryfallMagicCard.lang,
        cardFaces = resolveCardFaces(scryfallMagicCard),
        legality = resolveLegality(scryfallMagicCard),
        games = resolveGames(scryfallMagicCard)
    )

    private fun resolveLegality(scryfallMagicCard: ScryfallMagicCard): FormatLegality {
        return FormatLegality(
            standard = scryfallMagicCard.legalities.standard.toDomain()
        )
    }

    private fun resolveGames(scryfallMagicCard: ScryfallMagicCard) = scryfallMagicCard.games.map(ScryfallGame::toDomain).toSet()

    private fun resolveCardFaces(scryfallMagicCard: ScryfallMagicCard): List<MagicCardFace> {

        if (scryfallMagicCard.cardFaces != null)
            return resolveMultiFacedCard(scryfallMagicCard)

        return listOf(resolveSingleFacedCard(scryfallMagicCard))
    }


    private fun resolveMultiFacedCard(scryfallMagicCard: ScryfallMagicCard): List<MagicCardFace> {

        return scryfallMagicCard.cardFaces!!.map { cardFace ->
            MagicCardFace(
                name = DualLanguageText(original = cardFace.name, translation = cardFace.printedName ?: cardFace.name),
                text = DualLanguageText(original = cardFace.oracleText, translation = cardFace.printedText ?: cardFace.oracleText),
                manaCost = cardFace.manaCost
            )
        }

    }

    private fun resolveSingleFacedCard(scryfallMagicCard: ScryfallMagicCard): MagicCardFace {
        return MagicCardFace(
            name = DualLanguageText(
                original = scryfallMagicCard.name,
                translation = scryfallMagicCard.printedName ?: scryfallMagicCard.name
            ),
            text = DualLanguageText(
                original = scryfallMagicCard.oracleText ?: "",
                translation = scryfallMagicCard.printedText ?: scryfallMagicCard.oracleText ?: "",
            ),
            manaCost = scryfallMagicCard.manaCost ?: ""
        )
    }
}