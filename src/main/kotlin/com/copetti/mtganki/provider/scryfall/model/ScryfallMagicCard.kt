package com.copetti.mtganki.provider.scryfall.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ScryfallCardFace (
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

data class ScryfallMagicCard (
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
    val cardFaces: List<ScryfallCardFace>?
)