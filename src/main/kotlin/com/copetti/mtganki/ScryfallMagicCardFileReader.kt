package com.copetti.mtganki

import com.copetti.mtganki.provider.scryfall.model.ScryfallMagicCard
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import java.io.File

@Component
class ScryfallMagicCardFileReader(
    private val objectMapper: ObjectMapper
) {


    fun loadCards(filePath: String): List<ScryfallMagicCard> {
        return objectMapper.readValue(File(filePath), object: TypeReference<List<ScryfallMagicCard>>() {})
    }
}