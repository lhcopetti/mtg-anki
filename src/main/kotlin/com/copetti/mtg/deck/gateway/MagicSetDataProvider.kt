package com.copetti.mtg.deck.gateway

import com.copetti.mtg.deck.domain.model.MagicSet


interface MagicSetDataProvider {

    fun retrieveSet(setCode: String): MagicSet
}