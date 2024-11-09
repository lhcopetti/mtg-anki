package com.copetti.mtganki.provider.scryfall

import com.copetti.mtganki.domain.model.MagicCard
import com.copetti.mtganki.gateway.LoadMagicCardsExportProvider
import com.copetti.mtganki.provider.scryfall.model.ScryfallMagicCard
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileInputStream

@Component
class ScryfallLoadMagicCardsExportProvider(
    private val objectMapper: ObjectMapper,
    private val scryfallMagicCardMapper: ScryfallMagicCardMapper
) : LoadMagicCardsExportProvider {
    override fun loadAll(filePath: String): List<MagicCard> {
        val inputStream = FileInputStream(File(filePath))
        return objectMapper.readValue(inputStream, object : TypeReference<List<ScryfallMagicCard>>() {})
            .map(scryfallMagicCardMapper::toMagicCard)
    }
}