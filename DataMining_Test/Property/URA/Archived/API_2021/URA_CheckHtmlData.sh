#!/bin/bash

CURRENT_FOLDER=$(pwd)

echo "================================================================================"
echo "Date: "$(date +"%FT%T%z")
echo "CURRENT_FOLDER = "$CURRENT_FOLDER
echo "Start checking <title> ..."

grep -L '<title>Private Residential Property Transactions</title>\|<title>Rental contracts of private residential properties</title>' ${CURRENT_FOLDER}/HtmlData/*/*.html

echo "Start checking <html> ..."

grep -L '<html>\|</html>' ${CURRENT_FOLDER}/HtmlData/*/*.html

echo "Checking completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
