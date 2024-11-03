package com.copetti.mtganki.domain.mock

import com.copetti.mtganki.domain.model.DualLanguageText
import com.copetti.mtganki.domain.model.MagicCard
import com.copetti.mtganki.domain.model.MagicCardFace
import java.util.*

object MagicCards {


    fun givenSingleFacedCard(
        translationCardName: String = "translation-name",
        cardText: String = "original-text",
        translationCardText: String = "translation-text",
        set: String = "any-set"
    ) = MagicCard(
        id = UUID.randomUUID().toString(),
        set = set,
        lang = "any-lang",
        cardFaces = listOf(
            MagicCardFace(
                name = DualLanguageText(original = "original-name", translation = translationCardName),
                texts = DualLanguageText(original = cardText, translation = translationCardText),
                manaCost = "cost"
            )
        )
    )

    fun givenMultiFacedCard(
        translationCardName: String = "translation-name",
        cardText: String = "original-text",
        translationCardText: String = "translation-text",
        secondFaceText: String = "second-face-card-text",
        secondFaceTranslationText: String = "second-face-translation-card-text",
    ) = MagicCard(
        id = UUID.randomUUID().toString(),
        set = "any-set",
        lang = "any-lang",
        cardFaces = listOf(
            MagicCardFace(
                name = DualLanguageText(original = "original-name", translation = translationCardName),
                texts = DualLanguageText(original = cardText, translation = translationCardText),
                manaCost = "cost"
            ),
            MagicCardFace(
                name = DualLanguageText(original = "original-name", translation = translationCardName),
                texts = DualLanguageText(original = secondFaceText, translation = secondFaceTranslationText),
                manaCost = "cost"
            )
        )
    )
}