package com.copetti.mtganki.provider.scryfall

import com.copetti.mtganki.domain.model.DualLanguageText
import com.copetti.mtganki.domain.model.MagicCard
import com.copetti.mtganki.provider.scryfall.model.ScryfallMagicCard
import org.springframework.stereotype.Component

@Component
class ScryfallMagicCardMapper {


    fun toMagicCard(scryfallMagicCard: ScryfallMagicCard) = MagicCard(
        id = scryfallMagicCard.id,
        set = scryfallMagicCard.set,
        lang = scryfallMagicCard.lang,
        names = resolveNames(scryfallMagicCard),
        texts = resolveTexts(scryfallMagicCard)
    )

    private fun resolveNames(scryfallMagicCard: ScryfallMagicCard): List<DualLanguageText> {
        val names = mutableListOf<DualLanguageText>()

        if (scryfallMagicCard.printedName != null) {
            names.add(DualLanguageText(original = scryfallMagicCard.name, translation = scryfallMagicCard.printedName))
        }
        if (scryfallMagicCard.cardFaces != null) {
            names.addAll(scryfallMagicCard.cardFaces.map {
                DualLanguageText(
                    original = it.name,
                    translation = it.printedName ?: it.name
                )
            })
        }
        return names;
    }

    private fun resolveTexts(scryfallMagicCard: ScryfallMagicCard): List<DualLanguageText> {
        val texts = mutableListOf<DualLanguageText>()

        if (scryfallMagicCard.oracleText != null && scryfallMagicCard.printedText != null) {
            texts.add(
                DualLanguageText(
                    original = scryfallMagicCard.oracleText,
                    translation = scryfallMagicCard.printedText,
                )
            )
        }

        if (scryfallMagicCard.cardFaces != null) {
            texts.addAll(scryfallMagicCard.cardFaces.map {
                DualLanguageText(
                    original = it.oracleText,
                    translation = it.printedText ?: it.oracleText
                )
            })
        }
        return texts
    }
}