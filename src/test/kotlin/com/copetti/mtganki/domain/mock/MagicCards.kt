package com.copetti.mtganki.domain.mock

import com.copetti.mtganki.domain.model.DualLanguageText
import com.copetti.mtganki.domain.model.MagicCard
import com.copetti.mtganki.domain.model.MagicCardFace
import java.util.*

object MagicCards {

    fun givenSingleFacedCard(
        translationCardName: String = "translation-name",
        cardText: String = "original-text",
        translationCardText: String = "translation-text"
    ) = MagicCard(
        id = UUID.randomUUID().toString(),
        set = "any-set",
        lang = "any-lang",
        cardFaces = listOf(
            MagicCardFace(
                name = DualLanguageText(original = "original-name", translation = translationCardName),
                texts = DualLanguageText(original = cardText, translation = translationCardText),
                manaCost = "cost"
            )
        )
    )
}