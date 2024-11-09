package com.copetti.mtg.deck.domain.mock

import com.copetti.mtg.deck.domain.model.*
import java.util.*

object MagicCards {


    fun givenSingleFacedCard(
        translationCardName: String = "translation-name",
        cardText: String = "original-text",
        translationCardText: String = "translation-text",
        set: String = "any-set",
        lang: String = "any-lang",
        standardLegality: Legality = Legality.LEGAL
    ) = MagicCard(
        id = UUID.randomUUID().toString(),
        set = set,
        lang = lang,
        cardFaces = listOf(
            MagicCardFace(
                name = DualLanguageText(original = "original-name", translation = translationCardName),
                texts = DualLanguageText(original = cardText, translation = translationCardText),
                manaCost = "cost"
            )
        ),
        legality = FormatLegality(
            standard = standardLegality
        )
    )

    fun givenMultiFacedCard(
        translationCardName: String = "translation-name",
        cardText: String = "original-text",
        translationCardText: String = "translation-text",
        secondFaceText: String = "second-face-card-text",
        secondFaceTranslationText: String = "second-face-translation-card-text",
        set: String = "any-set",
        lang: String = "any-lang",
        standardLegality: Legality = Legality.LEGAL
    ) = MagicCard(
        id = UUID.randomUUID().toString(),
        set = set,
        lang = lang,
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
        ),
        legality = FormatLegality(
            standard = standardLegality
        )
    )
}