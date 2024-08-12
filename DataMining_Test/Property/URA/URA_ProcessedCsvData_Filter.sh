#!/bin/bash

FromDate=$1
ToDate=$2

CurrentFolder=$(pwd)
DateRange="${FromDate}_${ToDate}"
ProcessedCsvFileFolder="${CurrentFolder}/ProcessedCsvData/${DateRange}"

PriceSummaryFileName="${ProcessedCsvFileFolder}/URA_CondoEcResults_${DateRange}_PriceSummary.csv"
PriceSummaryFilteredFileName="${ProcessedCsvFileFolder}/URA_CondoEcResults_${DateRange}_PriceSummary_F.csv"

PriceRentRatioFileName="${ProcessedCsvFileFolder}/URA_CondoEcResults_${DateRange}_PriceRentRatio.csv"
PriceRentRatioFilteredFileName="${ProcessedCsvFileFolder}/URA_CondoEcResults_${DateRange}_PriceRentRatio_F.csv"

echo "================================================================================"
echo "Filter processed CSV data ..."
echo "Date: "$(date +"%FT%T%z")

echo "CurrentFolder = "$CurrentFolder
echo "FromDate = "$FromDate
echo "ToDate = "$ToDate
echo "DateRange = "$DateRange
echo "ProcessedCsvFileFolder = "$ProcessedCsvFileFolder
echo "PriceSummaryFileName = "$PriceSummaryFileName
echo "PriceSummaryFilteredFileName = "$PriceSummaryFilteredFileName
echo "PriceRentRatioFileName = "$PriceRentRatioFileName
echo "PriceRentRatioFilteredFileName = "$PriceRentRatioFilteredFileName
echo

grep -h 'POSTAL_DISTRICT' $PriceSummaryFileName > $PriceSummaryFilteredFileName
grep -h 'POSTAL_DISTRICT' $PriceRentRatioFileName > $PriceRentRatioFilteredFileName

condoNameList=(
",Astoria Park,"
",Casablanca,"
",Caspian,"
",Central View,"
",Chiltern Park,"
",Coco Palms,"
",Compass Heights,"
",d'Nest,"
",Double Bay Residences,"
",Eastpoint Green,"
",Eastvale,"
",Esparina Residences,"
",Heritage View,"
",Kerrisdale,"
",Kovan Melody,"
",Kovan Residences,"
",Livia,"
",Maysprings,"
",Melville Park,"
",Mi Casa,"
",Millage,"
",Northoaks,"
",Northvale,"
",Oleander Towers,"
",Orchid Park Condominium,"
",Palm Gardens,"
",Parc Oasis,"
",Parc Vista,"
",Prive,"
",Rafflesia Condominium,"
",Regent Grove,"
",Rivervale Crest,"
",Rosewood,"
",Sea Horizon,"
",Simei Green Condominium,"
",Stratum,"
",Summerdale,"
",Sun Plaza,"
",Sunglade,"
",Tanamera Crest,"
",The Centris,"
",The Dew,"
",The Eden at Tampines,"
",The Esparis,"
",The Estuary,"
",The Jade,"
",The Lakeshore,"
",The Mayfair,"
",The Minton,"
",The Nautical,"
",The Quartz,"
",The Warren,"
",The Woodgrove,"
",Watercolours,"
",White Water,"
",Windermere,"
",Woodgrove Condominium,"
",Woodsvale,"
",Yew Mei Green,"
)

for condoName in "${condoNameList[@]}"
do
	echo $condoName
    grep -ih "$condoName" $PriceSummaryFileName >> $PriceSummaryFilteredFileName
    grep -ih "$condoName" $PriceRentRatioFileName >> $PriceRentRatioFilteredFileName
done

echo
echo "Filter processed CSV data completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
