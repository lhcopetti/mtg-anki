package com.copetti.mtganki.gateway


interface JapaneseParserProvider {

    fun parse(input: String): List<String>
}