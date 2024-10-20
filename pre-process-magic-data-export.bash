#!/bin/bash -e

jq -c '.[] | select(.lang == "ja" and .set == "dsk")'
