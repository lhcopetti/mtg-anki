package com.copetti.mtg.deck.gateway

import com.copetti.mtg.deck.domain.model.ParsedVocabulary


interface JapaneseParserProvider {

    fun parse(input: String): List<ParsedVocabulary>
}