package com.copetti.mtg.deck.common.extensions

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun <T: Any> T.getLogger(): Logger = LoggerFactory.getLogger(this::class.java)