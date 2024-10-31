package com.copetti.mtganki.domain.model

data class DualLanguageText(
    val original: String,
    val translation: String
)

data class MagicCardFace(
    val name: DualLanguageText,
    val texts: DualLanguageText,
    val manaCost: String
)

data class MagicCard(
    val id: String,
    val set: String,
    val lang: String,
    val cardFaces: List<MagicCardFace>,
)