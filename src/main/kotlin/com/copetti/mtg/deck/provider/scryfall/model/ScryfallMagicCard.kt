package com.copetti.mtg.deck.provider.scryfall.model

import com.copetti.mtg.deck.domain.model.Legality
import com.fasterxml.jackson.annotation.JsonProperty

enum class ScryfallLegality {
    @JsonProperty("legal")
    LEGAL,
    @JsonProperty("not_legal")
    NOT_LEGAL,
    @JsonProperty("restricted")
    RESTRICTED,
    @JsonProperty("banned")
    BANNED;

    fun toDomain() = when(this) {
        LEGAL -> Legality.LEGAL
        NOT_LEGAL -> Legality.NOT_LEGAL
        RESTRICTED -> Legality.RESTRICTED
        BANNED -> Legality.BANNED
    }
}

data class ScryfallFormatLegality(
    val standard: ScryfallLegality
)

data class ScryfallCardFace(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("printed_name")
    val printedName: String?,
    @JsonProperty("printed_text")
    val printedText: String?,
    @JsonProperty("oracle_text")
    val oracleText: String,
    @JsonProperty("mana_cost")
    val manaCost: String,
)


data class ScryfallMagicCard(
    val id: String,
    val name: String,
    val set: String,
    val lang: String,

    @JsonProperty("printed_name")
    val printedName: String?,
    @JsonProperty("oracle_text")
    val oracleText: String?,
    @JsonProperty("printed_text")
    val printedText: String?,
    @JsonProperty("mana_cost")
    val manaCost: String?,

    @JsonProperty("card_faces")
    val cardFaces: List<ScryfallCardFace>?,

    val legalities: ScryfallFormatLegality
)