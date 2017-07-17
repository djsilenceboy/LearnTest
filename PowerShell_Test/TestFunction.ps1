# Function Script:Get-OSVersion
Function Get-OSVersion
{
  (Get-CimInstance -Class Win32_OperatingSystem).Version
}

Write-Host 'OS Version = '$(Get-OSVersion)

# Function Get-TextStatistics($path)
Function Get-TextStatistics($path)
{
  Get-ChildItem $path
}

Get-TextStatistics("D:")

Function Count-Test([string[]]$arr)
{
  $arr | Measure-Object -Line -Word -Character
}

Count-Test("Hello", "World", "Next")

Function WithOption-Test([switch]$show, [switch]$help)
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

WithOption-Test -Show
WithOption-Test -Help
