#!/bin/bash -e

#jq -c '.[] | select(.lang == "ja" and (.set == "blb" or .set == "dsk" or .set == "lci"))'
#jq -c '.[] | select(.lang == "ja")'
jq -c '.[] | select(.lang == "ja" and .set == "dsk")'
