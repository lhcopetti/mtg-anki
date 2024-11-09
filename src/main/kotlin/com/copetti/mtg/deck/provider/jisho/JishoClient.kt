package com.copetti.mtg.deck.provider.jisho

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "jishoClient", url = "\${jisho.url}")
interface JishoClient {

    @GetMapping("/api/v1/search/words")
    fun searchWord(
        @RequestParam("keyword") keyword: String
    ): JishoSearchResponse
}