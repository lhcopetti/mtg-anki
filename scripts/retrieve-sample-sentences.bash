#!/bin/bash -e

VOCAB="$1"

jq '{ oracle_text, printed_text } | 
    select(.printed_text | contains("'"$VOCAB"'"))' |\
    jq -s '.' |\
    jq 'min_by(.printed_text | length) | [ { sentence: .printed_text, translation: .oracle_text } ]'


