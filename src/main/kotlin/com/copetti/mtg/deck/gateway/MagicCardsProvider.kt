package com.copetti.mtg.deck.gateway

import com.copetti.mtg.deck.domain.model.MagicCard
import java.util.stream.Stream

interface MagicCardsProvider {

    fun loadAll(): Stream<MagicCard>
}