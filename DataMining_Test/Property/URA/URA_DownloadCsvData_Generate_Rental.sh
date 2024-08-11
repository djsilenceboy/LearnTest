#!/bin/bash

ContractYearFrom=$1
ContractMonthFrom=$2
ContractYearTo=$3
ContractMonthTo=$4
OutputFolder=$5
OutputFileMainName=$6

echo "------------------------------------------------------------"
echo "ContractYearFrom = "$ContractYearFrom
echo "ContractMonthFrom = "$ContractMonthFrom
echo "ContractYearTo = "$ContractYearTo
echo "ContractMonthTo = "$ContractMonthTo
echo "OutputFolder = "$OutputFolder
echo "OutputFileMainName = "$OutputFileMainName

MainServiceUrl="https://www.ura.gov.sg/property-market-information/pmiSearchResidentialRentalDownload"
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

    fullFormDataResults="downloadType=downloadCSV&locationDetails=${postalDistrictListPart}&contractYearFrom=${ContractYearFrom}&contractMonthFrom=${ContractMonthFrom}&contractYearTo=${ContractYearTo}&contractMonthTo=${ContractMonthTo}&propertyTypeGroupNo=${propertyTypeGroupNo}&sortBy=9&sortAsc=1&selectColumn=1&selectColumn=2&selectColumn=3&selectColumn=4&selectColumn=5&selectColumn=6&selectColumn=7&selectColumn=8&selectColumn=9"

    echo  $fullFormDataResults
}

M=1
for postalDistrictList in D01 D02 D03 D04 D05 D06 D07 D08 D09 D10 D11 D12 D13 D14 D15 D16 D17 D18 D19 D20 D21 D22 D23 D24 D25 D26 D27 D28
# for postalDistrictList in "D01 D02 D03 D04" "D05 D06 D07 D08" "D09 D10 D11 D12" "D13 D14 D15 D16" "D17 D18 D19 D20" "D21 D22 D23 D24" "D25 D26 D27 D28"
do
    actualShFile=${OutputFolder}/${OutputFileMainName}_${M}.sh
    echo $M": actualShFile = "$actualShFile

    echo "#!/bin/bash" > $actualShFile
    echo >> $actualShFile

    N=1
    for propertyTypeGroupNo in 1 2 3
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

        echo 'LineCount=$(cat '${csvDataFile}' | wc -l)' >> $actualShFile
        echo 'if [ $LineCount -ge 10001 ]; then' >> $actualShFile

        csvDataFile="${OutputFileMainName}_${M}_${N}b.csv"
        fullFormData="${fullFormData}&gotoPage=2"
        actionCommand="curl -ksS '${MainServiceUrl}' -H 'Connection: keep-alive' -H 'Content-Type: application/x-www-form-urlencoded' -H 'Accept-Encoding: gzip, deflate, br' -d '${fullFormData}' --compressed -o '${csvDataFile}'"
        echo $M"-"$N": actionCommand = "$actionCommand
        echo $actionCommand >> $actualShFile

        echo "fi" >> $actualShFile
        echo >> $actualShFile

        N=$(($N + 1))
    done
    M=$(($M + 1))
done

echo "------------------------------------------------------------"
