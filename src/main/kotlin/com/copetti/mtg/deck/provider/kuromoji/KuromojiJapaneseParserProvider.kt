package com.copetti.mtg.deck.provider.kuromoji

import com.atilika.kuromoji.ipadic.Token
import com.atilika.kuromoji.ipadic.Tokenizer
import com.copetti.mtg.deck.gateway.JapaneseParserProvider
import org.springframework.stereotype.Component

@Component
class KuromojiJapaneseParserProvider(
    private val tokenizer: Tokenizer
): JapaneseParserProvider {
    override fun parse(input: String): List<String> {
        return tokenizer
            .tokenize(input)
            .map (Token::getBaseForm)
        }
    }