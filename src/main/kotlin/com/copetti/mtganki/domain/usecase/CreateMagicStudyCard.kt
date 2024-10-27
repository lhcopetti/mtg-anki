package com.copetti.mtganki.domain.usecase

import com.copetti.mtganki.common.extensions.getLogger
import com.copetti.mtganki.domain.model.MagicCard
import com.copetti.mtganki.domain.model.VocabularyStudyCard
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