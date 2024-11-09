package com.copetti.mtg.deck.domain.usecase

import com.copetti.mtg.deck.domain.model.MagicCard
import com.copetti.mtg.deck.domain.model.MagicSet
import com.copetti.mtg.deck.gateway.MagicSetDataProvider
import org.springframework.stereotype.Service

@Service
class LoadMagicCardSets(
    private val magicSetDataProvider: MagicSetDataProvider
) {

    fun load(magicCard: List<MagicCard>): List<MagicSet> {
        return magicCard
            .map (MagicCard::set)
            .toSet()
            .map (magicSetDataProvider::retrieveSet)
    }
}