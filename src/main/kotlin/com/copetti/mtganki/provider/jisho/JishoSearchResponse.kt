package com.copetti.mtganki.provider.jisho

import com.fasterxml.jackson.annotation.JsonProperty

data class JishoSearchResponse(
    val data: List<JishoDataItem>
)

data class JishoDataItem(
    val slug: String,

    @JsonProperty("is_common")
    val isCommon: Boolean,

    val tags: List<String>,
    val jlpt: List<String>,

    val japanese: List<JishoJapaneseItem>,
    val senses: List<JishoSense>,
)

data class JishoJapaneseItem(
    val word: String?,
    val reading: String?
)

data class JishoSense(
    @JsonProperty("english_definitions")
    val englishDefinitions: List<String>,
)