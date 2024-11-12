package com.copetti.mtg.deck.provider.scryfall

import com.copetti.mtg.deck.common.configuration.JsonConfiguration
import com.copetti.mtg.deck.domain.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class ScryfallLoadMagicCardsExportProviderTest {

    private lateinit var scryfallLoadMagicCardsExportProvider: ScryfallLoadMagicCardsExportProvider

    @BeforeEach
    fun setUp() {
        scryfallLoadMagicCardsExportProvider = ScryfallLoadMagicCardsExportProvider(
            objectMapper = JsonConfiguration().objectMapper(),
            scryfallMagicCardMapper = ScryfallMagicCardMapper()
        )
    }

    @Test
    fun `should correctly read all contents from the input stream and covert it to magic cards`() {
        val temporaryFile = File.createTempFile("scryfall-export-reader", "json")
        temporaryFile.writeText(EXPORT_FILE_SAMPLE)
        temporaryFile.deleteOnExit()

        val actual = scryfallLoadMagicCardsExportProvider.loadAll(temporaryFile.path)
        val expected = listOf(
            MagicCard(
                id = "0000419b-0bba-4488-8f7a-6194544ce91e",
                set = "blb",
                lang = "en",
                cardFaces = listOf(
                    MagicCardFace(
                        name = DualLanguageText(original = "Forest", translation = "Forest"),
                        text = DualLanguageText(
                            original = "({T}: Add {G}.)",
                            translation = "({T}: Add {G}.)"
                        ),
                        manaCost = ""
                    )
                ),
                legality = FormatLegality(
                    standard = Legality.LEGAL
                ),
                games = setOf(GameLegality.MTG_ONLINE, GameLegality.MAGIC_ARENA, GameLegality.PAPER)
            ),
            MagicCard(
                id = "0000579f-7b35-4ed3-b44c-db2a538066fe",
                set = "tsp",
                lang = "en",
                cardFaces = listOf(
                    MagicCardFace(
                        name = DualLanguageText(original = "Fury Sliver", translation = "Fury Sliver"),
                        text = DualLanguageText(
                            original = "All Sliver creatures have double strike.",
                            translation = "All Sliver creatures have double strike."
                        ),
                        manaCost = "{5}{R}"
                    ),
                ),
                legality = FormatLegality(
                    standard = Legality.NOT_LEGAL
                ),
                games = setOf(GameLegality.MTG_ONLINE, GameLegality.PAPER)
            )
        )

        assertThat(actual).isEqualTo(expected)
    }


    companion object {

        private val EXPORT_FILE_SAMPLE = """
[
  {
    "object": "card",
    "id": "0000419b-0bba-4488-8f7a-6194544ce91e",
    "oracle_id": "b34bb2dc-c1af-4d77-b0b3-a0fb342a5fc6",
    "multiverse_ids": [
      668564
    ],
    "mtgo_id": 129825,
    "arena_id": 91829,
    "tcgplayer_id": 558404,
    "name": "Forest",
    "lang": "en",
    "released_at": "2024-08-02",
    "uri": "https://api.scryfall.com/cards/0000419b-0bba-4488-8f7a-6194544ce91e",
    "scryfall_uri": "https://scryfall.com/card/blb/280/forest?utm_source=api",
    "layout": "normal",
    "highres_image": true,
    "image_status": "highres_scan",
    "image_uris": {
      "small": "https://cards.scryfall.io/small/front/0/0/0000419b-0bba-4488-8f7a-6194544ce91e.jpg?1721427487",
      "normal": "https://cards.scryfall.io/normal/front/0/0/0000419b-0bba-4488-8f7a-6194544ce91e.jpg?1721427487",
      "large": "https://cards.scryfall.io/large/front/0/0/0000419b-0bba-4488-8f7a-6194544ce91e.jpg?1721427487",
      "png": "https://cards.scryfall.io/png/front/0/0/0000419b-0bba-4488-8f7a-6194544ce91e.png?1721427487",
      "art_crop": "https://cards.scryfall.io/art_crop/front/0/0/0000419b-0bba-4488-8f7a-6194544ce91e.jpg?1721427487",
      "border_crop": "https://cards.scryfall.io/border_crop/front/0/0/0000419b-0bba-4488-8f7a-6194544ce91e.jpg?1721427487"
    },
    "mana_cost": "",
    "cmc": 0,
    "type_line": "Basic Land — Forest",
    "oracle_text": "({T}: Add {G}.)",
    "colors": [],
    "color_identity": [
      "G"
    ],
    "keywords": [],
    "produced_mana": [
      "G"
    ],
    "legalities": {
      "standard": "legal",
      "future": "legal",
      "historic": "legal",
      "timeless": "legal",
      "gladiator": "legal",
      "pioneer": "legal",
      "explorer": "legal",
      "modern": "legal",
      "legacy": "legal",
      "pauper": "legal",
      "vintage": "legal",
      "penny": "legal",
      "commander": "legal",
      "oathbreaker": "legal",
      "standardbrawl": "legal",
      "brawl": "legal",
      "alchemy": "legal",
      "paupercommander": "legal",
      "duel": "legal",
      "oldschool": "not_legal",
      "premodern": "legal",
      "predh": "legal"
    },
    "games": [
      "paper",
      "mtgo",
      "arena"
    ],
    "reserved": false,
    "foil": true,
    "nonfoil": true,
    "finishes": [
      "nonfoil",
      "foil"
    ],
    "oversized": false,
    "promo": false,
    "reprint": true,
    "variation": false,
    "set_id": "a2f58272-bba6-439d-871e-7a46686ac018",
    "set": "blb",
    "set_name": "Bloomburrow",
    "set_type": "expansion",
    "set_uri": "https://api.scryfall.com/sets/a2f58272-bba6-439d-871e-7a46686ac018",
    "set_search_uri": "https://api.scryfall.com/cards/search?order=set&q=e%3Ablb&unique=prints",
    "scryfall_set_uri": "https://scryfall.com/sets/blb?utm_source=api",
    "rulings_uri": "https://api.scryfall.com/cards/0000419b-0bba-4488-8f7a-6194544ce91e/rulings",
    "prints_search_uri": "https://api.scryfall.com/cards/search?order=released&q=oracleid%3Ab34bb2dc-c1af-4d77-b0b3-a0fb342a5fc6&unique=prints",
    "collector_number": "280",
    "digital": false,
    "rarity": "common",
    "card_back_id": "0aeebaf5-8c7d-4636-9e82-8c27447861f7",
    "artist": "David Robert Hovey",
    "artist_ids": [
      "22ab27e3-6476-48f1-a9f7-9a9e86339030"
    ],
    "illustration_id": "fb2b1ca2-7440-48c2-81c8-84da0a45a626",
    "border_color": "black",
    "frame": "2015",
    "full_art": true,
    "textless": false,
    "booster": true,
    "story_spotlight": false,
    "prices": {
      "usd": "0.22",
      "usd_foil": "0.40",
      "usd_etched": null,
      "eur": null,
      "eur_foil": null,
      "tix": "0.02"
    },
    "related_uris": {
      "gatherer": "https://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=668564&printed=false",
      "tcgplayer_infinite_articles": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&trafcat=infinite&u=https%3A%2F%2Finfinite.tcgplayer.com%2Fsearch%3FcontentMode%3Darticle%26game%3Dmagic%26partner%3Dscryfall%26q%3DForest",
      "tcgplayer_infinite_decks": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&trafcat=infinite&u=https%3A%2F%2Finfinite.tcgplayer.com%2Fsearch%3FcontentMode%3Ddeck%26game%3Dmagic%26partner%3Dscryfall%26q%3DForest",
      "edhrec": "https://edhrec.com/route/?cc=Forest"
    },
    "purchase_uris": {
      "tcgplayer": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&u=https%3A%2F%2Fwww.tcgplayer.com%2Fproduct%2F558404%3Fpage%3D1",
      "cardmarket": "https://www.cardmarket.com/en/Magic/Products/Search?referrer=scryfall&searchString=Forest&utm_campaign=card_prices&utm_medium=text&utm_source=scryfall",
      "cardhoarder": "https://www.cardhoarder.com/cards/129825?affiliate_id=scryfall&ref=card-profile&utm_campaign=affiliate&utm_medium=card&utm_source=scryfall"
    }
  },
  {
    "object": "card",
    "id": "0000579f-7b35-4ed3-b44c-db2a538066fe",
    "oracle_id": "44623693-51d6-49ad-8cd7-140505caf02f",
    "multiverse_ids": [
      109722
    ],
    "mtgo_id": 25527,
    "mtgo_foil_id": 25528,
    "tcgplayer_id": 14240,
    "cardmarket_id": 13850,
    "name": "Fury Sliver",
    "lang": "en",
    "released_at": "2006-10-06",
    "uri": "https://api.scryfall.com/cards/0000579f-7b35-4ed3-b44c-db2a538066fe",
    "scryfall_uri": "https://scryfall.com/card/tsp/157/fury-sliver?utm_source=api",
    "layout": "normal",
    "highres_image": true,
    "image_status": "highres_scan",
    "image_uris": {
      "small": "https://cards.scryfall.io/small/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe.jpg?1562894979",
      "normal": "https://cards.scryfall.io/normal/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe.jpg?1562894979",
      "large": "https://cards.scryfall.io/large/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe.jpg?1562894979",
      "png": "https://cards.scryfall.io/png/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe.png?1562894979",
      "art_crop": "https://cards.scryfall.io/art_crop/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe.jpg?1562894979",
      "border_crop": "https://cards.scryfall.io/border_crop/front/0/0/0000579f-7b35-4ed3-b44c-db2a538066fe.jpg?1562894979"
    },
    "mana_cost": "{5}{R}",
    "cmc": 6,
    "type_line": "Creature — Sliver",
    "oracle_text": "All Sliver creatures have double strike.",
    "power": "3",
    "toughness": "3",
    "colors": [
      "R"
    ],
    "color_identity": [
      "R"
    ],
    "keywords": [],
    "legalities": {
      "standard": "not_legal",
      "future": "not_legal",
      "historic": "not_legal",
      "timeless": "not_legal",
      "gladiator": "not_legal",
      "pioneer": "not_legal",
      "explorer": "not_legal",
      "modern": "legal",
      "legacy": "legal",
      "pauper": "not_legal",
      "vintage": "legal",
      "penny": "legal",
      "commander": "legal",
      "oathbreaker": "legal",
      "standardbrawl": "not_legal",
      "brawl": "not_legal",
      "alchemy": "not_legal",
      "paupercommander": "restricted",
      "duel": "legal",
      "oldschool": "not_legal",
      "premodern": "not_legal",
      "predh": "legal"
    },
    "games": [
      "paper",
      "mtgo"
    ],
    "reserved": false,
    "foil": true,
    "nonfoil": true,
    "finishes": [
      "nonfoil",
      "foil"
    ],
    "oversized": false,
    "promo": false,
    "reprint": false,
    "variation": false,
    "set_id": "c1d109bc-ffd8-428f-8d7d-3f8d7e648046",
    "set": "tsp",
    "set_name": "Time Spiral",
    "set_type": "expansion",
    "set_uri": "https://api.scryfall.com/sets/c1d109bc-ffd8-428f-8d7d-3f8d7e648046",
    "set_search_uri": "https://api.scryfall.com/cards/search?order=set&q=e%3Atsp&unique=prints",
    "scryfall_set_uri": "https://scryfall.com/sets/tsp?utm_source=api",
    "rulings_uri": "https://api.scryfall.com/cards/0000579f-7b35-4ed3-b44c-db2a538066fe/rulings",
    "prints_search_uri": "https://api.scryfall.com/cards/search?order=released&q=oracleid%3A44623693-51d6-49ad-8cd7-140505caf02f&unique=prints",
    "collector_number": "157",
    "digital": false,
    "rarity": "uncommon",
    "flavor_text": "\"A rift opened, and our arrows were abruptly stilled. To move was to push the world. But the sliver's claw still twitched, red wounds appeared in Thed's chest, and ribbons of blood hung in the air.\"\n—Adom Capashen, Benalish hero",
    "card_back_id": "0aeebaf5-8c7d-4636-9e82-8c27447861f7",
    "artist": "Paolo Parente",
    "artist_ids": [
      "d48dd097-720d-476a-8722-6a02854ae28b"
    ],
    "illustration_id": "2fcca987-364c-4738-a75b-099d8a26d614",
    "border_color": "black",
    "frame": "2003",
    "full_art": false,
    "textless": false,
    "booster": true,
    "story_spotlight": false,
    "edhrec_rank": 8316,
    "penny_rank": 10610,
    "prices": {
      "usd": "0.31",
      "usd_foil": "1.90",
      "usd_etched": null,
      "eur": "0.20",
      "eur_foil": "1.02",
      "tix": "0.03"
    },
    "related_uris": {
      "gatherer": "https://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=109722&printed=false",
      "tcgplayer_infinite_articles": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&trafcat=infinite&u=https%3A%2F%2Finfinite.tcgplayer.com%2Fsearch%3FcontentMode%3Darticle%26game%3Dmagic%26partner%3Dscryfall%26q%3DFury%2BSliver",
      "tcgplayer_infinite_decks": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&trafcat=infinite&u=https%3A%2F%2Finfinite.tcgplayer.com%2Fsearch%3FcontentMode%3Ddeck%26game%3Dmagic%26partner%3Dscryfall%26q%3DFury%2BSliver",
      "edhrec": "https://edhrec.com/route/?cc=Fury+Sliver"
    },
    "purchase_uris": {
      "tcgplayer": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&u=https%3A%2F%2Fwww.tcgplayer.com%2Fproduct%2F14240%3Fpage%3D1",
      "cardmarket": "https://www.cardmarket.com/en/Magic/Products/Singles/Time-Spiral/Fury-Sliver?referrer=scryfall&utm_campaign=card_prices&utm_medium=text&utm_source=scryfall",
      "cardhoarder": "https://www.cardhoarder.com/cards/25527?affiliate_id=scryfall&ref=card-profile&utm_campaign=affiliate&utm_medium=card&utm_source=scryfall"
    }
  }
]

            """.trimIndent()
    }
}