package com.copetti.mtganki.provider.kuromoji

import com.atilika.kuromoji.ipadic.Token
import com.atilika.kuromoji.ipadic.Tokenizer
import com.copetti.mtganki.gateway.JapaneseParserProvider
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