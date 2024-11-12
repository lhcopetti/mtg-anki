package com.copetti.mtg.deck.domain.mock

import com.copetti.mtg.deck.domain.model.DualLanguageText
import com.copetti.mtg.deck.domain.model.MagicCardFace


object MagicCardFaces {

    fun givenMagicCardFaces(
        name: String = "card-name",
        translationName: String = "translation-name",
        text: String = "card-text",
        translationText: String = "translation-card-text",
        manaCost: String = "mana-cost"
    ) = MagicCardFace(
        name = DualLanguageText(original = name, translation = translationName),
        text = DualLanguageText(original = text, translation = translationText),
        manaCost = manaCost
    )
}