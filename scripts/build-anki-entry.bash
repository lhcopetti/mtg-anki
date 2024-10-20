#!/bin/bash -e


MAGIC_EXPORT_FILE="$1"
VOCAB="$2"
NEW_LINE="<br>"


DEFINITION="$(bash scripts/retrieve-definition.bash "$VOCAB")"
SAMPLE_SENTENCES="$(bash scripts/retrieve-sample-sentences.bash "$VOCAB" < "$MAGIC_EXPORT_FILE")"

READING="$(echo $DEFINITION | jq -r '.reading')"
DEFINITIONS="$(echo $DEFINITION | jq -r '.definitions | join("'"$NEW_LINE"'")')"
SAMPLE_SENTENCES="$(echo $SAMPLE_SENTENCES | jq -r 'map(.sentence + "'"$NEW_LINE"'" + .translation) | join("'"${NEW_LINE}${NEW_LINE}"'")')"

KEY="$VOCAB"
VALUE="${READING}${NEW_LINE}${DEFINITIONS}${NEW_LINE}${NEW_LINE}${SAMPLE_SENTENCES}"

KEY=${KEY//$'\t'//}
VALUE=${VALUE//$'\t'//}

ENTRY="$KEY\t$VALUE"
ENTRY="${ENTRY//$'\n'/$NEW_LINE}"
echo -e "$ENTRY"
