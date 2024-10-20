#!/bin/bash -e

VOCAB="$1"

JISHO_SEARCH_API_URL="https://jisho.org/api/v1/search/words"
RESPONSE="$(curl -s -X GET --data-urlencode "keyword=$VOCAB" $JISHO_SEARCH_API_URL)"

echo "$RESPONSE" |\
    jq  '{ 
    reading: .data[0].japanese[0].reading,
    definitions: ([.data[0].senses[]] | map(.english_definitions | join(", "))) 
}'
