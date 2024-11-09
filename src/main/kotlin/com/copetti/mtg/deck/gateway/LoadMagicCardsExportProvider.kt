package com.copetti.mtg.deck.gateway

import com.copetti.mtg.deck.domain.model.MagicCard


interface LoadMagicCardsExportProvider {

    fun loadAll(filePath: String): List<MagicCard>
}