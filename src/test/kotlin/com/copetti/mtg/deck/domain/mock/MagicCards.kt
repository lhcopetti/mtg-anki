package com.copetti.mtg.deck.domain.mock

import com.copetti.mtg.deck.domain.model.*
import java.util.*

object MagicCards {


    fun givenSingleFacedCard(
        name: String = "the-name",
        text: String = "original-text",
        translationName: String = "translation-name",
        translationText: String = "translation-text",
        set: String = "any-set",
        lang: String = "any-lang",
        standardLegality: Legality = Legality.LEGAL,
        games: Set<GameLegality> = GameLegality.entries.toSet(),
        manaCost: String = "cost"
    ) = MagicCard(
        id = UUID.randomUUID().toString(),
        set = set,
        lang = lang,
        cardFaces = listOf(
            MagicCardFace(
                name = DualLanguageText(original = name, translation = translationName),
                text = DualLanguageText(original = text, translation = translationText),
                manaCost = manaCost
            )
        ),
        legality = FormatLegality(
            standard = standardLegality
        ),
        games = games
    )

    fun givenMultiFacedCard(
        faces: List<MagicCardFace> = listOf(
            MagicCardFaces.givenMagicCardFaces(),
            MagicCardFaces.givenMagicCardFaces()
        ),
        set: String = "any-set",
        lang: String = "any-lang",
        standardLegality: Legality = Legality.LEGAL,
        games: Set<GameLegality> = GameLegality.entries.toSet()
    ) = MagicCard(
        id = UUID.randomUUID().toString(),
        set = set,
        lang = lang,
        cardFaces = faces,
        legality = FormatLegality(
            standard = standardLegality
        ),
        games = games
    )
}