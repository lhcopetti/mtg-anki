package com.copetti.mtg.deck.gateway


interface JapaneseParserProvider {

    fun parse(input: String): List<String>
}