#!/bin/bash -e

MAGIC_EXPORT_FILE="$1"


cat "$MAGIC_EXPORT_FILE" | bash scripts/process-magic-card-data.bash | xargs -I{} bash scripts/build-anki-entry.bash "$MAGIC_EXPORT_FILE" "{}"




