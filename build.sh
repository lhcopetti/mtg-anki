#!/bin/bash
set -e


if [ "$#" -lt 1 ]; then
    echo "Build script should be called with the export file as argument"
    echo "Eg: bash build.sh exports/all-cards-20241017091854.json"
    exit 1;
fi


function log() {
    MESSAGE="$1"
    echo "[mtg-anki] $MESSAGE"
}

EXPORT_FILE="$1"

log "Beginning..."

log "Building application..."

mvn clean install

log "Running application..."

mvn spring-boot:run -Dspring-boot.run.arguments="$EXPORT_FILE mtg-anki-deck-v0.0.3.apkg"

if [ $? -ne 0 ]; then
    log "Generating the deck failed. Please see log for more details"
    exit 1;
fi

log "Deck generated successfully!"

log "End..."

