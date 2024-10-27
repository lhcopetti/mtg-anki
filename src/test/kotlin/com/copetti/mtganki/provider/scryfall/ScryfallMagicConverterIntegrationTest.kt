package com.copetti.mtganki.provider.scryfall

import com.copetti.mtganki.JsonConfiguration
import com.copetti.mtganki.domain.model.DualLanguageText
import com.copetti.mtganki.domain.model.MagicCard
import com.copetti.mtganki.provider.scryfall.model.ScryfallMagicCard
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ScryfallMagicConverterIntegrationTest {


    private val objectMapper = JsonConfiguration().objectMapper()

    @InjectMocks
    private lateinit var scryfallMagicMapper: ScryfallMagicMapper

    @Test
    fun `should convert rooms correctly`() {

        val scryfallMagicCard = objectMapper.readValue(UNDERWATER_TUNNEL_ROOM, ScryfallMagicCard::class.java)
        val magicCard = scryfallMagicMapper.toMagicCard(scryfallMagicCard)

        val expected = MagicCard(
            id = "19021333-b52a-46e4-b059-0f21804cc507",
            set = "dsk",
            lang = "ja",
            names = listOf(
                DualLanguageText("Underwater Tunnel", "Underwater Tunnel // Slimy Aquarium"),
                DualLanguageText("Slimy Aquarium", "Underwater Tunnel // Slimy Aquarium")
            ),
            texts = listOf(
                DualLanguageText(
                    "When you unlock this door, surveil 2. (Look at the top two cards of your library, then put any number of them into your graveyard and the rest on top of your library in any order.)\n" +
                            "(You may cast either half. That door unlocks on the battlefield. As a sorcery, you may pay the mana cost of a locked door to unlock it.)",
                    "あなたがこのドアを開放したとき、諜報２を行う。（あなたのライブラリーの一番上にあるカード２枚を見、そのうちの望む枚数をあなたの墓地に、残りをあなたのライブラリーの一番上に望む順番で置く。）\n" +
                            "（どちらの半分でも唱えることができる。そのドアは戦場で開放される。閉鎖されているドアのマナ・コストを支払って、それを開放してもよい。これはソーサリーとして行う。"
                ),
                DualLanguageText(
                    "When you unlock this door, manifest dread, then put a +1/+1 counter on that creature.\n" +
                            "(You may cast either half. That door unlocks on the battlefield. As a sorcery, you may pay the mana cost of a locked door to unlock it.)",
                    "泥濘（ぬかる）んだ水（すい）族（ぞく）館（かん）\n" +
                            "o3oU\n" +
                            "あなたがこのドアを開放したとき、戦慄予示し、その後そのクリーチャーの上に＋１/＋１カウンター１個を置く。"
                )
            )
        )
        assertThat(magicCard).isEqualTo(expected)
    }


    val UNDERWATER_TUNNEL_ROOM = """
{
  "object": "card",
  "id": "19021333-b52a-46e4-b059-0f21804cc507",
  "oracle_id": "2b46394e-d337-4b1a-88e6-7fdac2ee4ac4",
  "multiverse_ids": [
    674839
  ],
  "name": "Underwater Tunnel // Slimy Aquarium",
  "lang": "ja",
  "released_at": "2024-09-27",
  "uri": "https://api.scryfall.com/cards/19021333-b52a-46e4-b059-0f21804cc507",
  "scryfall_uri": "https://scryfall.com/card/dsk/79/ja/underwater-tunnel-slimy-aquarium-underwater-tunnel-slimy-aquarium?utm_source=api",
  "layout": "split",
  "highres_image": false,
  "image_status": "lowres",
  "image_uris": {
    "small": "https://cards.scryfall.io/small/front/1/9/19021333-b52a-46e4-b059-0f21804cc507.jpg?1726869598",
    "normal": "https://cards.scryfall.io/normal/front/1/9/19021333-b52a-46e4-b059-0f21804cc507.jpg?1726869598",
    "large": "https://cards.scryfall.io/large/front/1/9/19021333-b52a-46e4-b059-0f21804cc507.jpg?1726869598",
    "png": "https://cards.scryfall.io/png/front/1/9/19021333-b52a-46e4-b059-0f21804cc507.png?1726869598",
    "art_crop": "https://cards.scryfall.io/art_crop/front/1/9/19021333-b52a-46e4-b059-0f21804cc507.jpg?1726869598",
    "border_crop": "https://cards.scryfall.io/border_crop/front/1/9/19021333-b52a-46e4-b059-0f21804cc507.jpg?1726869598"
  },
  "mana_cost": "{U} // {3}{U}",
  "cmc": 5,
  "type_line": "Enchantment — Room // Enchantment — Room",
  "colors": [
    "U"
  ],
  "color_identity": [
    "U"
  ],
  "keywords": [
    "Surveil",
    "Manifest",
    "Manifest dread"
  ],
  "card_faces": [
    {
      "object": "card_face",
      "name": "Underwater Tunnel",
      "printed_name": "Underwater Tunnel // Slimy Aquarium",
      "mana_cost": "{U}",
      "type_line": "Enchantment — Room",
      "printed_type_line": "エンチャント — 部屋",
      "oracle_text": "When you unlock this door, surveil 2. (Look at the top two cards of your library, then put any number of them into your graveyard and the rest on top of your library in any order.)\n(You may cast either half. That door unlocks on the battlefield. As a sorcery, you may pay the mana cost of a locked door to unlock it.)",
      "printed_text": "あなたがこのドアを開放したとき、諜報２を行う。（あなたのライブラリーの一番上にあるカード２枚を見、そのうちの望む枚数をあなたの墓地に、残りをあなたのライブラリーの一番上に望む順番で置く。）\n（どちらの半分でも唱えることができる。そのドアは戦場で開放される。閉鎖されているドアのマナ・コストを支払って、それを開放してもよい。これはソーサリーとして行う。",
      "artist": "Titus Lunter",
      "artist_id": "b11e6b3a-f4ca-4cf1-8fd7-475696de5f5c",
      "illustration_id": "1aee59e4-70f9-42ff-a8ba-cda108e6ff71"
    },
    {
      "object": "card_face",
      "name": "Slimy Aquarium",
      "printed_name": "Underwater Tunnel // Slimy Aquarium",
      "mana_cost": "{3}{U}",
      "type_line": "Enchantment — Room",
      "printed_type_line": "エンチャント — 部屋",
      "oracle_text": "When you unlock this door, manifest dread, then put a +1/+1 counter on that creature.\n(You may cast either half. That door unlocks on the battlefield. As a sorcery, you may pay the mana cost of a locked door to unlock it.)",
      "printed_text": "泥濘（ぬかる）んだ水（すい）族（ぞく）館（かん）\no3oU\nあなたがこのドアを開放したとき、戦慄予示し、その後そのクリーチャーの上に＋１/＋１カウンター１個を置く。",
      "artist": "Titus Lunter",
      "artist_id": "b11e6b3a-f4ca-4cf1-8fd7-475696de5f5c"
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
  "rulings_uri": "https://api.scryfall.com/cards/19021333-b52a-46e4-b059-0f21804cc507/rulings",
  "prints_search_uri": "https://api.scryfall.com/cards/search?order=released&q=oracleid%3A2b46394e-d337-4b1a-88e6-7fdac2ee4ac4&unique=prints",
  "collector_number": "79",
  "digital": false,
  "rarity": "common",
  "card_back_id": "0aeebaf5-8c7d-4636-9e82-8c27447861f7",
  "artist": "Titus Lunter",
  "artist_ids": [
    "b11e6b3a-f4ca-4cf1-8fd7-475696de5f5c"
  ],
  "illustration_id": "1aee59e4-70f9-42ff-a8ba-cda108e6ff71",
  "border_color": "black",
  "frame": "2015",
  "full_art": false,
  "textless": false,
  "booster": true,
  "story_spotlight": false,
  "edhrec_rank": 14255,
  "penny_rank": 2876,
  "prices": {
    "usd": null,
    "usd_foil": null,
    "usd_etched": null,
    "eur": null,
    "eur_foil": null,
    "tix": null
  },
  "related_uris": {
    "gatherer": "https://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=674839&printed=true",
    "tcgplayer_infinite_articles": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&trafcat=infinite&u=https%3A%2F%2Finfinite.tcgplayer.com%2Fsearch%3FcontentMode%3Darticle%26game%3Dmagic%26partner%3Dscryfall%26q%3DUnderwater%2BTunnel%2B%252F%252F%2BSlimy%2BAquarium",
    "tcgplayer_infinite_decks": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&trafcat=infinite&u=https%3A%2F%2Finfinite.tcgplayer.com%2Fsearch%3FcontentMode%3Ddeck%26game%3Dmagic%26partner%3Dscryfall%26q%3DUnderwater%2BTunnel%2B%252F%252F%2BSlimy%2BAquarium",
    "edhrec": "https://edhrec.com/route/?cc=Underwater+Tunnel+%2F%2F+Slimy+Aquarium"
  },
  "purchase_uris": {
    "tcgplayer": "https://tcgplayer.pxf.io/c/4931599/1830156/21018?subId1=api&u=https%3A%2F%2Fwww.tcgplayer.com%2Fsearch%2Fmagic%2Fproduct%3FproductLineName%3Dmagic%26q%3DUnderwater%2BTunnel%2B%252F%252F%2BSlimy%2BAquarium%26view%3Dgrid",
    "cardmarket": "https://www.cardmarket.com/en/Magic/Products/Search?referrer=scryfall&searchString=Underwater+Tunnel+%2F%2F+Slimy+Aquarium&utm_campaign=card_prices&utm_medium=text&utm_source=scryfall",
    "cardhoarder": "https://www.cardhoarder.com/cards?affiliate_id=scryfall&data%5Bsearch%5D=Underwater+Tunnel+%2F%2F+Slimy+Aquarium&ref=card-profile&utm_campaign=affiliate&utm_medium=card&utm_source=scryfall"
  }
}
        """.trimIndent()

}

