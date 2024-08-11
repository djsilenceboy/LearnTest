#!/bin/bash

SaleYearFrom=$1
SaleMonthFrom=$2
SaleYearTo=$3
SaleMonthTo=$4
OutputFolder=$5
OutputFileMainName=$6

echo "------------------------------------------------------------"
echo "SaleYearFrom = "$SaleYearFrom
echo "SaleMonthFrom = "$SaleMonthFrom
echo "SaleYearTo = "$SaleYearTo
echo "SaleMonthTo = "$SaleMonthTo
echo "OutputFolder = "$OutputFolder
echo "OutputFileMainName = "$OutputFileMainName

MainServiceUrl="https://www.ura.gov.sg/property-market-information/pmiSearchResidentialTransactionDownload"
echo "MainServiceUrl = "$MainServiceUrl

make_form_data_postal_list()
{
    postalDistrictList=$*
    postalDistrictListResults="%5B%22postalDistrict%22"

    for postalDistrict in ${postalDistrictList}
    do
        postalDistrictListResults="${postalDistrictListResults}%2C%22${postalDistrict}%22"
    done

    postalDistrictListResults="${postalDistrictListResults}%5D"

    echo  $postalDistrictListResults
}

make_form_data_full()
{
    postalDistrictListPart=$1
    propertyTypeGroupNo=$2

    fullFormDataResults="downloadType=downloadCSV&locationDetails=${postalDistrictListPart}&saleYearFrom=${SaleYearFrom}&saleMonthFrom=${SaleMonthFrom}&saleYearTo=${SaleYearTo}&saleMonthTo=${SaleMonthTo}&propertyTypeGroupNo=${propertyTypeGroupNo}&saleType%5B0%5D=1&saleType%5B1%5D=2&saleType%5B2%5D=3&sortBy=5&sortAsc=1&selectColumn=1&selectColumn=2&selectColumn=5&selectColumn=6&selectColumn=7&selectColumn=8&selectColumn=9&selectColumn=10&selectColumn=11&selectColumn=12&selectColumn=13&selectColumn=14&selectColumn=15&selectColumn=16&selectColumn=17"

    echo  $fullFormDataResults
}

M=1
for postalDistrictList in "D01 D02 D03 D04" "D05 D06 D07 D08" "D09 D10 D11 D12" "D13 D14 D15 D16" "D17 D18 D19 D20" "D21 D22 D23 D24" "D25 D26 D27 D28"
do
    actualShFile=${OutputFolder}/${OutputFileMainName}_${M}.sh
    echo $M": actualShFile = "$actualShFile

    echo "#!/bin/bash" > $actualShFile
    echo >> $actualShFile

    N=1
    for propertyTypeGroupNo in 1 2 3 4
    do
        csvDataFile="${OutputFileMainName}_${M}_${N}.csv"
        echo $M"-"$N": csvDataFile = "$csvDataFile

        formDataPostalDistrictList=$(make_form_data_postal_list ${postalDistrictList})
        echo $M"-"$N": formDataPostalDistrictList = "$formDataPostalDistrictList
        fullFormData=$(make_form_data_full ${formDataPostalDistrictList} ${propertyTypeGroupNo})
        echo $M"-"$N": fullFormData = "$fullFormData

        actionCommand="curl -ksS '${MainServiceUrl}' -H 'Connection: keep-alive' -H 'Content-Type: application/x-www-form-urlencoded' -H 'Accept-Encoding: gzip, deflate, br' -d '${fullFormData}' --compressed -o '${csvDataFile}'"
        echo $M"-"$N": actionCommand = "$actionCommand
        echo $actionCommand >> $actualShFile
        echo >> $actualShFile

        N=$(($N + 1))
    done
    M=$(($M + 1))
done

echo "------------------------------------------------------------"
