package com.copetti.mtg.deck.provider.kuromoji

import com.atilika.kuromoji.ipadic.Tokenizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KuromojiConfiguration {

    @Bean
    fun tokenizer() = Tokenizer()
}