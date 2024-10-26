package com.copetti.mtganki.domain.card

data class DualLanguageText (
    val original: String,
    val translation: String
)

data class MagicCard (
    val id: String,
    val set: String,
    val lang: String,
    val names: List<DualLanguageText>,
    val texts: List<DualLanguageText>
)