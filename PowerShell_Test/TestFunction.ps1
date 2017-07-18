# Function Script:Get-OSVersion
Function Get-OSVersion
{
  (Get-CimInstance -Class Win32_OperatingSystem).Version
}

Write-Host 'OS Version = '$(Get-OSVersion)
Write-Host '----------------------------------------'

# Function Get-TextStatistics($path)
Function Get-TextStatistics($path)
{
  Get-ChildItem $path
}

Get-TextStatistics("D:")
Write-Host '----------------------------------------'

Function Count-Test([string[]]$arr)
{
  $arr | Measure-Object -Line -Word -Character
}

Count-Test("Hello", "World", "Next")
Write-Host '----------------------------------------'

Function Switch-Test([switch]$show, [switch]$help)
{
  if ($show)
  {
    Write-Host 'There is switch Show.'
  }

  if ($help)
  {
    Write-Host 'There is switch Help.'
  }
}

Switch-Test -Show
Write-Host '--------------------'
Switch-Test -Help
Write-Host '----------------------------------------'

Function Option-Test($cat, $dog)
{
  if ($cat)
  {
    Write-Host 'There is cat: '$cat
  }

  if ($dog)
  {
    Write-Host 'There is dog: '$dog
  }
}

Option-Test "Tom" "Bob"
Write-Host '--------------------'
Option-Test -cat "Tom" "Bob"
Write-Host '--------------------'
Option-Test -dog "Bob"
Write-Host '----------------------------------------'

# Another way for input parameters.
Function Option-Test2
{
  Param{$cat, $dog}

  if ($cat)
  {
    Write-Host 'There is cat: '$cat
  }

  if ($dog)
  {
    Write-Host 'There is dog: '$dog
  }
}

Option-Test "Tom" "Bob"
Write-Host '--------------------'
Option-Test -dog "Bob"
Write-Host '----------------------------------------'

Function ReturnValue-Test($a, $b)
{
  Return $a + $b
}

$c = ReturnValue-Test("AA", "BB")

Write-Host '$c = '$c
Write-Host '----------------------------------------'
