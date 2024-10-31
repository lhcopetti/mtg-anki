package com.copetti.mtganki.provider.scryfall

import com.copetti.mtganki.common.configuration.JsonConfiguration
import com.copetti.mtganki.domain.model.DualLanguageText
import com.copetti.mtganki.domain.model.MagicCard
import com.copetti.mtganki.domain.model.MagicCardFace
import com.copetti.mtganki.provider.scryfall.model.ScryfallMagicCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ScryfallMagicCardMapperTest {

    @InjectMocks
    private lateinit var mapper: ScryfallMagicCardMapper

    private val jsonConfiguration = JsonConfiguration().objectMapper()

    @Test
    fun `should correctly map a single-face card`() {
        val scryfallCard = jsonConfiguration.readValue(SINGLE_FACED_CARD, ScryfallMagicCard::class.java)

        val expected = MagicCard(
            id = "20d20b8d-a6e5-4cd2-b558-9acb1ad74f13",
            set = "dsk",
            lang = "ja",
            cardFaces = listOf(
                MagicCardFace(
                    name = DualLanguageText(original = "Final Vengeance", translation = "最後の復讐"),
                    texts = DualLanguageText(
                        original = "As an additional cost to cast this spell, sacrifice a creature or enchantment.\nExile target creature.",
                        translation = "この呪文を唱えるための追加コストとして、エンチャントやクリーチャーである１つを生け贄に捧げる。\nクリーチャー１体を対象とする。それを追放する。",
                    ),
                    manaCost = "{B}"
                )
            )
        )

        assertThat(mapper.toMagicCard(scryfallCard)).isEqualTo(expected)
    }

    @Test
    fun `should correctly map two-face cards`() {
        val scryfallCard = jsonConfiguration.readValue(MULTI_FACED_CARD, ScryfallMagicCard::class.java)

        val expected = MagicCard(
            id = "29b9d103-a1af-4c8f-860a-402d72150dda",
            set = "dsk",
            lang = "ja",
            cardFaces = listOf(
                MagicCardFace(
                    name =
                    DualLanguageText(original = "Meat Locker", translation = "Meat Locker // Drowned Diner"),
                    texts =
                    DualLanguageText(
                        original = "When you unlock this door, tap up to one target creature and put two stun counters on it. (If a permanent with a stun counter would become untapped, remove one from it instead.)\n(You may cast either half. That door unlocks on the battlefield. As a sorcery, you may pay the mana cost of a locked door to unlock it.)",
                        translation = "あなたがこのドアを開放したとき、クリーチャー最大１体を対象とする。それをタップし、それの上に麻痺カウンター２個を置く。（麻痺カウンターが置かれているパーマネントがアンタップ状態になるなら、代わりにそれの上から麻痺カウンター１個を取り除く。）\n（どちらの半分でも唱えることができる。そのドアは戦場で開放される。閉鎖されているドアのマナ・コストを支払って、それを開放してもよい。これはソーサリーとして行う。）",
                    ),
                    manaCost = "{2}{U}"
                ),
                MagicCardFace(
                    name = DualLanguageText(original = "Drowned Diner", translation = "Meat Locker // Drowned Diner"),
                    texts =
                    DualLanguageText(
                        original = "When you unlock this door, draw three cards, then discard a card.\n(You may cast either half. That door unlocks on the battlefield. As a sorcery, you may pay the mana cost of a locked door to unlock it.)",
                        translation = "冠（かん）水（すい）した食（しょく）堂（どう）\no3oUoU\nあなたがこのドアを開放したとき、カード３枚を引き、その後、カード１枚を捨てる。",
                    ),
                    manaCost = "{3}{U}{U}"
                )
            ),
        )

        assertThat(mapper.toMagicCard(scryfallCard)).isEqualTo(expected)
    }

    companion object {

        private val SINGLE_FACED_CARD = """
{
  "object": "card",
  "id": "20d20b8d-a6e5-4cd2-b558-9acb1ad74f13",
  "oracle_id": "cdba7f43-df9e-4bfb-9480-721c787ad051",
  "multiverse_ids": [
    674859
  ],
  "name": "Final Vengeance",
  "printed_name": "最後の復讐",
  "lang": "ja",
  "released_at": "2024-09-27",
  "uri": "https://api.scryfall.com/cards/20d20b8d-a6e5-4cd2-b558-9acb1ad74f13",
  "scryfall_uri": "https://scryfall.com/card/dsk/99/ja/%E6%9C%80%E5%BE%8C%E3%81%AE%E5%BE%A9%E8%AE%90?utm_source=api",
  "layout": "normal",
  "highres_image": false,
  "image_status": "lowres",
  "image_uris": {
    "small": "https://cards.scryfall.io/small/front/2/0/20d20b8d-a6e5-4cd2-b558-9acb1ad74f13.jpg?1726869996",
    "normal": "https://cards.scryfall.io/normal/front/2/0/20d20b8d-a6e5-4cd2-b558-9acb1ad74f13.jpg?1726869996",
    "large": "https://cards.scryfall.io/large/front/2/0/20d20b8d-a6e5-4cd2-b558-9acb1ad74f13.jpg?1726869996",
    "png": "https://cards.scryfall.io/png/front/2/0/20d20b8d-a6e5-4cd2-b558-9acb1ad74f13.png?1726869996",
    "art_crop": "https://cards.scryfall.io/art_crop/front/2/0/20d20b8d-a6e5-4cd2-b558-9acb1ad74f13.jpg?1726869996",
    "border_crop": "https://cards.scryfall.io/border_crop/front/2/0/20d20b8d-a6e5-4cd2-b558-9acb1ad74f13.jpg?1726869996"
  },
  "mana_cost": "{B}",
  "cmc": 1,
  "type_line": "Sorcery",
  "printed_type_line": "ソーサリー",
  "oracle_text": "As an additional cost to cast this spell, sacrifice a creature or enchantment.\nExile target creature.",
  "printed_text": "この呪文を唱えるための追加コストとして、エンチャントやクリーチャーである１つを生け贄に捧げる。\nクリーチャー１体を対象とする。それを追放する。",
  "colors": [
    "B"
  ],
  "color_identity": [
    "B"
  ],
  "keywords": [],
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
    "premodern": "not_legal",
    "predh": "not_legal"
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
  "reprint": false,
  "variation": false,
  "set_id": "a111d8a9-b647-48ec-afab-2b78f92173f5",
  "set": "dsk",
  "set_name": "Duskmourn: House of Horror",
  "set_type": "expansion",
  "set_uri": "https://api.scryfall.com/sets/a111d8a9-b647-48ec-afab-2b78f92173f5",
  "set_search_uri": "https://api.scryfall.com/cards/search?order=set&q=e%3Adsk&unique=prints",
  "scryfall_set_uri": "https://scryfall.com/sets/dsk?utm_source=api",
  "rulings_uri": "https://api.scryfall.com/cards/20d20b8d-a6e5-4cd2-b558-9acb1ad74f13/rulings",
  "prints_search_uri": "https://api.scryfall.com/cards/search?order=released&q=oracleid%3Acdba7f43-df9e-4bfb-9480-721c787ad051&unique=prints",
  "collector_number": "99",
  "digital": false,
  "rarity": "common",
  "flavor_text": "彼らが底知れぬ深淵へと落ちていくと、生存者と館底種の叫び声が一つの恐ろしい轟音に混ざり合った。",
  "card_back_id": "0aeebaf5-8c7d-4636-9e82-8c27447861f7",
  "artist": "David Szabo",
  "artist_ids": [
    "4b065a8b-3d15-45cf-bd6f-f3736df6292b"
  ],
  "illustration_id": "69848733-1439-40dc-9470-357bcba94de9",
  "border_color": "black",
  "frame": "2015",
  "full_art": false,
  "textless": false,
  "booster": true,
  "story_spotlight": false,
  "edhrec_rank": 15315,
  "penny_rank": 4069,
  "prices": {
    "usd": null,
    "usd_foil": null,
    "usd_etched": null,
    "eur": null,
    "eur_foil": null,
    "tix": null
  },
  "related_uris": {
    "gatherer": "https://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=674859&printed=true",
    "tcgplayer_infinite_articles": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&trafcat=infinite&u=https%3A%2F%2Finfinite.tcgplayer.com%2Fsearch%3FcontentMode%3Darticle%26game%3Dmagic%26partner%3Dscryfall%26q%3DFinal%2BVengeance",
    "tcgplayer_infinite_decks": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&trafcat=infinite&u=https%3A%2F%2Finfinite.tcgplayer.com%2Fsearch%3FcontentMode%3Ddeck%26game%3Dmagic%26partner%3Dscryfall%26q%3DFinal%2BVengeance",
    "edhrec": "https://edhrec.com/route/?cc=Final+Vengeance"
  },
  "purchase_uris": {
    "tcgplayer": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&u=https%3A%2F%2Fwww.tcgplayer.com%2Fsearch%2Fmagic%2Fproduct%3FproductLineName%3Dmagic%26q%3DFinal%2BVengeance%26view%3Dgrid",
    "cardmarket": "https://www.cardmarket.com/en/Magic/Products/Search?referrer=scryfall&searchString=Final+Vengeance&utm_campaign=card_prices&utm_medium=text&utm_source=scryfall",
    "cardhoarder": "https://www.cardhoarder.com/cards?affiliate_id=scryfall&data%5Bsearch%5D=Final+Vengeance&ref=card-profile&utm_campaign=affiliate&utm_medium=card&utm_source=scryfall"
  }
}

""".trimIndent()

        private val MULTI_FACED_CARD = """
{
  "object": "card",
  "id": "29b9d103-a1af-4c8f-860a-402d72150dda",
  "oracle_id": "897eef47-3e99-4a5f-8a3e-ddb06bc95e9a",
  "multiverse_ids": [
    674825
  ],
  "name": "Meat Locker // Drowned Diner",
  "lang": "ja",
  "released_at": "2024-09-27",
  "uri": "https://api.scryfall.com/cards/29b9d103-a1af-4c8f-860a-402d72150dda",
  "scryfall_uri": "https://scryfall.com/card/dsk/65/ja/meat-locker-drowned-diner-meat-locker-drowned-diner?utm_source=api",
  "layout": "split",
  "highres_image": false,
  "image_status": "lowres",
  "image_uris": {
    "small": "https://cards.scryfall.io/small/front/2/9/29b9d103-a1af-4c8f-860a-402d72150dda.jpg?1726869363",
    "normal": "https://cards.scryfall.io/normal/front/2/9/29b9d103-a1af-4c8f-860a-402d72150dda.jpg?1726869363",
    "large": "https://cards.scryfall.io/large/front/2/9/29b9d103-a1af-4c8f-860a-402d72150dda.jpg?1726869363",
    "png": "https://cards.scryfall.io/png/front/2/9/29b9d103-a1af-4c8f-860a-402d72150dda.png?1726869363",
    "art_crop": "https://cards.scryfall.io/art_crop/front/2/9/29b9d103-a1af-4c8f-860a-402d72150dda.jpg?1726869363",
    "border_crop": "https://cards.scryfall.io/border_crop/front/2/9/29b9d103-a1af-4c8f-860a-402d72150dda.jpg?1726869363"
  },
  "mana_cost": "{2}{U} // {3}{U}{U}",
  "cmc": 8,
  "type_line": "Enchantment — Room // Enchantment — Room",
  "colors": [
    "U"
  ],
  "color_identity": [
    "U"
  ],
  "keywords": [],
  "card_faces": [
    {
      "object": "card_face",
      "name": "Meat Locker",
      "printed_name": "Meat Locker // Drowned Diner",
      "mana_cost": "{2}{U}",
      "type_line": "Enchantment — Room",
      "printed_type_line": "エンチャント — 部屋",
      "oracle_text": "When you unlock this door, tap up to one target creature and put two stun counters on it. (If a permanent with a stun counter would become untapped, remove one from it instead.)\n(You may cast either half. That door unlocks on the battlefield. As a sorcery, you may pay the mana cost of a locked door to unlock it.)",
      "printed_text": "あなたがこのドアを開放したとき、クリーチャー最大１体を対象とする。それをタップし、それの上に麻痺カウンター２個を置く。（麻痺カウンターが置かれているパーマネントがアンタップ状態になるなら、代わりにそれの上から麻痺カウンター１個を取り除く。）\n（どちらの半分でも唱えることができる。そのドアは戦場で開放される。閉鎖されているドアのマナ・コストを支払って、それを開放してもよい。これはソーサリーとして行う。）",
      "artist": "Sergey Glushakov",
      "artist_id": "8df4596a-1d88-4b6a-9ce8-5c8089c3946c",
      "illustration_id": "8d783c0a-fdc2-4648-9056-ea2ac301120e"
    },
    {
      "object": "card_face",
      "name": "Drowned Diner",
      "printed_name": "Meat Locker // Drowned Diner",
      "mana_cost": "{3}{U}{U}",
      "type_line": "Enchantment — Room",
      "printed_type_line": "エンチャント — 部屋",
      "oracle_text": "When you unlock this door, draw three cards, then discard a card.\n(You may cast either half. That door unlocks on the battlefield. As a sorcery, you may pay the mana cost of a locked door to unlock it.)",
      "printed_text": "冠（かん）水（すい）した食（しょく）堂（どう）\no3oUoU\nあなたがこのドアを開放したとき、カード３枚を引き、その後、カード１枚を捨てる。",
      "artist": "Sergey Glushakov",
      "artist_id": "8df4596a-1d88-4b6a-9ce8-5c8089c3946c"
    }
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
    "premodern": "not_legal",
    "predh": "not_legal"
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
  "reprint": false,
  "variation": false,
  "set_id": "a111d8a9-b647-48ec-afab-2b78f92173f5",
  "set": "dsk",
  "set_name": "Duskmourn: House of Horror",
  "set_type": "expansion",
  "set_uri": "https://api.scryfall.com/sets/a111d8a9-b647-48ec-afab-2b78f92173f5",
  "set_search_uri": "https://api.scryfall.com/cards/search?order=set&q=e%3Adsk&unique=prints",
  "scryfall_set_uri": "https://scryfall.com/sets/dsk?utm_source=api",
  "rulings_uri": "https://api.scryfall.com/cards/29b9d103-a1af-4c8f-860a-402d72150dda/rulings",
  "prints_search_uri": "https://api.scryfall.com/cards/search?order=released&q=oracleid%3A897eef47-3e99-4a5f-8a3e-ddb06bc95e9a&unique=prints",
  "collector_number": "65",
  "digital": false,
  "rarity": "common",
  "card_back_id": "0aeebaf5-8c7d-4636-9e82-8c27447861f7",
  "artist": "Sergey Glushakov",
  "artist_ids": [
    "8df4596a-1d88-4b6a-9ce8-5c8089c3946c"
  ],
  "illustration_id": "8d783c0a-fdc2-4648-9056-ea2ac301120e",
  "border_color": "black",
  "frame": "2015",
  "full_art": false,
  "textless": false,
  "booster": true,
  "story_spotlight": false,
  "edhrec_rank": 12857,
  "penny_rank": 5535,
  "prices": {
    "usd": null,
    "usd_foil": null,
    "usd_etched": null,
    "eur": null,
    "eur_foil": null,
    "tix": null
  },
  "related_uris": {
    "gatherer": "https://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=674825&printed=true",
    "tcgplayer_infinite_articles": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&trafcat=infinite&u=https%3A%2F%2Finfinite.tcgplayer.com%2Fsearch%3FcontentMode%3Darticle%26game%3Dmagic%26partner%3Dscryfall%26q%3DMeat%2BLocker%2B%252F%252F%2BDrowned%2BDiner",
    "tcgplayer_infinite_decks": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&trafcat=infinite&u=https%3A%2F%2Finfinite.tcgplayer.com%2Fsearch%3FcontentMode%3Ddeck%26game%3Dmagic%26partner%3Dscryfall%26q%3DMeat%2BLocker%2B%252F%252F%2BDrowned%2BDiner",
    "edhrec": "https://edhrec.com/route/?cc=Meat+Locker+%2F%2F+Drowned+Diner"
  },
  "purchase_uris": {
    "tcgplayer": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&u=https%3A%2F%2Fwww.tcgplayer.com%2Fsearch%2Fmagic%2Fproduct%3FproductLineName%3Dmagic%26q%3DMeat%2BLocker%2B%252F%252F%2BDrowned%2BDiner%26view%3Dgrid",
    "cardmarket": "https://www.cardmarket.com/en/Magic/Products/Search?referrer=scryfall&searchString=Meat+Locker+%2F%2F+Drowned+Diner&utm_campaign=card_prices&utm_medium=text&utm_source=scryfall",
    "cardhoarder": "https://www.cardhoarder.com/cards?affiliate_id=scryfall&data%5Bsearch%5D=Meat+Locker+%2F%2F+Drowned+Diner&ref=card-profile&utm_campaign=affiliate&utm_medium=card&utm_source=scryfall"
  }
}
            
        """.trimIndent()
    }
}