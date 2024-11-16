#!/bin/bash
set -e


if [ "$#" -gt 0 ]; then
    echo "Build script does not support any arguments"
    echo "Eg: bash build.sh"
    exit 1;
fi

function log() {
    MESSAGE="$1"
    echo "[mtg-anki] $MESSAGE"
}

log "Beginning..."

log "Building application..."

mvn clean install

log "Running application..."

ARTIFACT_VERSION="$(mvn -q -Dexec.executable="echo" -Dexec.args='${project.version}' --non-recursive exec:exec)"
MTG_ANKI_DECK_VERSION="v${ARTIFACT_VERSION//-SNAPSHOT}"
mvn spring-boot:run -Dspring-boot.run.arguments="mtg-anki-deck-${MTG_ANKI_DECK_VERSION}.apkg"

if [ $? -ne 0 ]; then
    log "Generating the deck failed. Please see log for more details"
    exit 1;
fi

log "Deck generated successfully!"

log "End..."

