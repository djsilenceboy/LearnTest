#!/bin/bash

FromDate=$1
ToDate=$2

CurrentFolder=$(pwd)
DateRange="${FromDate}_${ToDate}"
ProcessedCsvFileFolder="${CurrentFolder}/ProcessedCsvData/${DateRange}"

PriceSummaryFileName="${ProcessedCsvFileFolder}/URA_CondoEcResults_${DateRange}_PriceSummary.csv"
PriceSummaryFilteredFileName="${ProcessedCsvFileFolder}/URA_CondoEcResults_${DateRange}_PriceSummary_F.csv"

PriceSummaryChangeFileName="${ProcessedCsvFileFolder}/URA_CondoEcResults_${DateRange}_PriceSummaryChange.csv"
PriceSummaryChangeFilteredFileName="${ProcessedCsvFileFolder}/URA_CondoEcResults_${DateRange}_PriceSummaryChange_F.csv"

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
echo "PriceSummaryChangeFileName = "$PriceSummaryChangeFileName
echo "PriceSummaryChangeFilteredFileName = "$PriceSummaryChangeFilteredFileName
echo "PriceRentRatioFileName = "$PriceRentRatioFileName
echo "PriceRentRatioFilteredFileName = "$PriceRentRatioFilteredFileName
echo

grep -h 'POSTAL_DISTRICT' $PriceSummaryFileName > $PriceSummaryFilteredFileName
grep -h 'POSTAL_DISTRICT' $PriceSummaryChangeFileName > $PriceSummaryChangeFilteredFileName
grep -h 'POSTAL_DISTRICT' $PriceRentRatioFileName > $PriceRentRatioFilteredFileName

condoNameList=(
",Boathouse Residences,"
",eCO,"
",euhabitat,"
",Hillsta,"
",Kingsford Waterbay,"
",La Casa,"
",Nine Residences,"
",Parc Rosewood,"
",Regent Grove,"
",Rivervale Crest,"
",Sea Horizon,"
",Seastrand,"
",Signature At Yishun,"
",Sol Acres,"
",Stratum,"
",Symphony Suites,"
",The Estuary,"
",The Minton,"
",The Nautical,"
",The Tennery,"
",The Verve,"
",The Wisteria,"
",The Woodgrove,"
",Urban Vista,"
",Watercolours,"
",Woodhaven,"
)

for condoName in "${condoNameList[@]}"
do
	echo $condoName
    grep -ih "$condoName" $PriceSummaryFileName >> $PriceSummaryFilteredFileName
    grep -ih "$condoName" $PriceSummaryChangeFileName >> $PriceSummaryChangeFilteredFileName
    grep -ih "$condoName" $PriceRentRatioFileName >> $PriceRentRatioFilteredFileName
done

echo
echo "Filter processed CSV data completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
