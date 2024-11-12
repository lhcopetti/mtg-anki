package com.copetti.mtg.deck.domain.model

data class DualLanguageText(
    val original: String,
    val translation: String
)

data class MagicCardFace(
    val name: DualLanguageText,
    val text: DualLanguageText,
    val manaCost: String
)

enum class Legality {
    LEGAL,
    NOT_LEGAL,
    RESTRICTED,
    BANNED
}

data class FormatLegality(
    val standard: Legality
)

enum class GameLegality {
    MTG_ONLINE,
    MAGIC_ARENA,
    PAPER,
    ASTRAL,
    SEGA
}

data class MagicCard(
    val id: String,
    val set: String,
    val lang: String,
    val cardFaces: List<MagicCardFace>,
    val legality: FormatLegality,
    val games: Set<GameLegality>
)