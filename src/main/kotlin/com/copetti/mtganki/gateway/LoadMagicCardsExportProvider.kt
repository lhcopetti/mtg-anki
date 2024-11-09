package com.copetti.mtganki.gateway

import com.copetti.mtganki.domain.model.MagicCard

interface LoadMagicCardsExportProvider {

    fun loadAll(filePath: String): List<MagicCard>
}