#!/bin/bash -e

function log() {
    MESSAGE="$1"
    echo "[mtg-anki] $MESSAGE"
}

EXPORT_FILE="$1"

log "Beginning..."

log "Building application..."

mvn clean install

if [ $? -ne 0 ]; then
    log "Build the application failed. Please see log for more details"
    exit 1;
fi

log "Running application..."

mvn spring-boot:run -Dspring-boot.run.arguments="$EXPORT_FILE mtg-anki-deck-v0.0.3.apkg"

if [ $? -ne 0 ]; then
    log "Generating the deck failed. Please see log for more details"
    exit 1;
fi

log "Deck generated successfully!"

log "End..."

