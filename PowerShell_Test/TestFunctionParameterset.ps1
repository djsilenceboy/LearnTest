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

  Write-Host '$country= '$country
  Write-Host '$city= '$city
  Write-Host '$street= '$street
  Write-Host '$block= '$block
  Write-Host '$name= '$name

  Switch($PSCmdlet.ParameterSetName)
  {
    "Country" {"$Name lives in $Country, $City."}
    "Street" {"$Name lives in $Street, $block."}
  }
}

# Cannot pass in parameter belonging to different ParameterSetName!
Sample1 -Country China -Name Jerry
Write-Host '----------------------------------------'
Sample1 -Street ChangAnJie -Name Jerry
Write-Host '----------------------------------------'
