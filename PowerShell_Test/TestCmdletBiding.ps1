Function Sample1
{
  Write-Host '$Args = '$Args
}

Sample1 -Verbose
Write-Host '----------------------------------------'

Function Sample2
{
  [cmdletbinding()]
  Param()
  Write-Host '$Args = '$Args
}

Sample2 -Verbose
Write-Host '----------------------------------------'

Function Sample3
{
  [cmdletbinding()]
  Param()
  Write-Verbose 'Find -Verbose flag.'
}

Sample3 -Verbose
Write-Host '----------------------------------------'

Function Sample4
{
  [cmdletbinding(SupportsShouldProcess=$True)]
  Param()
  md Temp
}

Sample4 -WhatIf
Write-Host '----------------------------------------'

Function Sample5
{
  [cmdletbinding(SupportsShouldProcess=$True)]
  Param()
  md Temp
}

Sample5 -Confirm
Write-Host '----------------------------------------'
