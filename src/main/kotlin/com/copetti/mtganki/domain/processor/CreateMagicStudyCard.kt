package com.copetti.mtganki.domain.processor

import com.copetti.mtganki.common.extensions.getLogger
import com.copetti.mtganki.domain.card.MagicCard
import com.copetti.mtganki.domain.card.VocabularyStudyCard
import com.copetti.mtganki.gateway.DictionaryProvider
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class CreateMagicStudyCard(
    private val dictionaryProvider: DictionaryProvider
) {

    private val logger: Logger = getLogger()

    fun create(vocabulary: String, cards: Set<MagicCard>): VocabularyStudyCard? {

        val definition = dictionaryProvider.lookup(vocabulary)

        if (definition == null) {
            logger.info("Vocabulary [$vocabulary] has no definition, will not create a study card")
            return null
        }

        return VocabularyStudyCard(
            vocabulary = vocabulary,
            definition = definition,
            cards = cards
        )
    }
}