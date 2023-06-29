#!/bin/bash

FromDate=$1
ToDate=$2

CurrentFolder=$(pwd)
DateRange="${FromDate}_${ToDate}"
ProcessedCsvFileFolder="${CurrentFolder}/ProcessedCsvData/${DateRange}"

TransPriceSummaryFileName="${ProcessedCsvFileFolder}/URA_CondoEcResults_${DateRange}_PriceSummary.csv"
TransPriceSummaryFilteredFileName="${ProcessedCsvFileFolder}/URA_CondoEcResults_${DateRange}_PriceSummary_F.csv"

echo "================================================================================"
echo "Filter processed CSV data ..."
echo "Date: "$(date +"%FT%T%z")

echo "CurrentFolder = "$CurrentFolder
echo "FromDate = "$FromDate
echo "ToDate = "$ToDate
echo "DateRange = "$DateRange
echo "ProcessedCsvFileFolder = "$ProcessedCsvFileFolder
echo "TransPriceSummaryFileName = "$TransPriceSummaryFileName
echo "TransPriceSummaryFilteredFileName = "$TransPriceSummaryFilteredFileName
echo

grep -h 'POSTAL_DISTRICT' $TransPriceSummaryFileName > $TransPriceSummaryFilteredFileName

condoNameList=(
",Casablanca,"
",Central View,"
",EastPoint Green,"
",Maysprings,"
",Northoaks,"
",Northvale,"
",Orchid Park Condominium,"
",Palm Gardens,"
",Parc Vista,"
",Regent Grove,"
",Rivervale Crest,"
",Rosewood,"
",Simei Green,"
",The Eden At Tampines,"
",The Esparis,"
",The MayFair,"
",The Warren,"
",Windermere,"
",Woodgrove Condo,"
",Woodsvale,"
",Yew Mei Green,"
)

for condoName in "${condoNameList[@]}"
do
	echo $condoName
    grep -ih "$condoName" $TransPriceSummaryFileName >> $TransPriceSummaryFilteredFileName
done

echo
echo "Filter processed CSV data completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
