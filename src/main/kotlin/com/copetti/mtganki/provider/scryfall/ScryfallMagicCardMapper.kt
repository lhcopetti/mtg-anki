package com.copetti.mtganki.provider.scryfall

import com.copetti.mtganki.domain.model.DualLanguageText
import com.copetti.mtganki.domain.model.MagicCard
import com.copetti.mtganki.domain.model.MagicCardFace
import com.copetti.mtganki.provider.scryfall.model.ScryfallMagicCard
import org.springframework.stereotype.Component

@Component
class ScryfallMagicCardMapper {


    fun toMagicCard(scryfallMagicCard: ScryfallMagicCard) = MagicCard(
        id = scryfallMagicCard.id,
        set = scryfallMagicCard.set,
        lang = scryfallMagicCard.lang,
        cardFaces = resolveCardFaces(scryfallMagicCard)
    )

    private fun resolveCardFaces(scryfallMagicCard: ScryfallMagicCard): List<MagicCardFace> {

        if (scryfallMagicCard.cardFaces != null)
            return resolveMultiFacedCard(scryfallMagicCard)

        return listOf(resolveSingleFacedCard(scryfallMagicCard))
    }


    private fun resolveMultiFacedCard(scryfallMagicCard: ScryfallMagicCard): List<MagicCardFace> {

        return scryfallMagicCard.cardFaces!!.map { cardFace ->
            MagicCardFace(
                name = DualLanguageText(original = cardFace.name, translation = cardFace.printedName ?: cardFace.name),
                texts = DualLanguageText(original = cardFace.oracleText, translation = cardFace.printedText ?: cardFace.oracleText)
            )
        }

    }

    private fun resolveSingleFacedCard(scryfallMagicCard: ScryfallMagicCard): MagicCardFace {
        return MagicCardFace(
            name = DualLanguageText(
                original = scryfallMagicCard.name,
                translation = scryfallMagicCard.printedName ?: scryfallMagicCard.name
            ),
            texts = DualLanguageText(
                original = scryfallMagicCard.oracleText ?: "",
                translation = scryfallMagicCard.printedText ?: scryfallMagicCard.oracleText ?: "",
            )
        )
    }
}