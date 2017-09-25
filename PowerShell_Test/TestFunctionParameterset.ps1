Function Sample1
{
  Param(
    [Parameter(ParameterSetName="Country",Mandatory=$True)]
    $country,
    [Parameter(ParameterSetName="Country")]
    $city,
    [Parameter(ParameterSetName="Street",Mandatory=$True)]
    $street,
    [Parameter(ParameterSetName="Street")]
    $block,
	[Parameter(Mandatory=$True)]
    $name
  )

  Write-Host '$country = '$country
  Write-Host '$city = '$city
  Write-Host '$street = '$street
  Write-Host '$block = '$block
  Write-Host '$name = '$name

  Switch($PSCmdlet.ParameterSetName)
  {
    "Country" {"$Name lives in $Country, $City."}
    "Street" {"$Name lives in $Street, $block."}
  }
}

# Cannot pass in parameter belonging to different ParameterSetName!
Sample1 -Country China -Name Jerry
Write-Host '--------------------'
Sample1 -Street ChangAnJie -Name Jerry
Write-Host '----------------------------------------'

Function Sample2
{
  Param(
    [Parameter(Mandatory=$True, HelpMessage="Please enter the name:")]
    $name
  )

  Write-Host '$name = '$name
}

Sample2
Write-Host '----------------------------------------'

Function Sample3
{
  Param(
    [ValidateSet("Tom", "Jerry")]
    [string]$name,
    [ValidateRange(8, 10)]
    [int]$age
  )

  Write-Host '$name = '$name
  Write-Host '$age = '$age
}

Sample3 "Tom" 7
Write-Host '--------------------'
Sample3 "Tom" 8
Write-Host '--------------------'
Sample3 "Mary" 8
Write-Host '--------------------'
Sample3 "Jerry" 10
Write-Host '----------------------------------------'
