Function Sample
{
  Begin
  {
    "Print once in begin."
  }
  Process
  {
    "Print each time."
  }
  End
  {
    "Print once in end."
  }
}

1..5 | Sample
Write-Host '----------------------------------------'

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
  $arr | Measure -Line -Word -Character
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

Function Option-Test($cat, $dog, $fruit="Apple")
{
  if ($cat)
  {
    Write-Host 'There is cat: '$cat
  }

  if ($dog)
  {
    Write-Host 'There is dog: '$dog
  }

  if ($fruit)
  {
    Write-Host 'There is fruit: '$fruit
  }
}

Option-Test "Tom" "Bob"
Write-Host '--------------------'
Option-Test -dog "Bob" "Tom"
Write-Host '--------------------'
Option-Test -dog "Bob"
Write-Host '--------------------'
Option-Test -fruit "Orange"
Write-Host '----------------------------------------'

# Another way for input parameters.
Function Option-Test2
{
  Param($cat, $dog)

  if ($cat)
  {
    Write-Host 'There is cat: '$cat
  }

  if ($dog)
  {
    Write-Host 'There is dog: '$dog
  }
}

Option-Test2 "Tom" "Bob"
Write-Host '--------------------'
Option-Test2 -dog "Bob"
Write-Host '----------------------------------------'

Function Option-Test3
{
  Param(
    $cat,
    [Parameter(Mandatory=$True)]
    $dog
  )

  if ($cat)
  {
    Write-Host 'There is cat: '$cat
  }

  if ($dog)
  {
    Write-Host 'There is dog: '$dog
  }
}

Option-Test3 "Tom" "Bob"
Write-Host '--------------------'
# It will pause and ask for value of $dog.
Option-Test3 "Tom"
Write-Host '----------------------------------------'

Function ReturnValue-Test($a, $b)
{
  Return $a + $b
}

$c = ReturnValue-Test("AA", "BB")

Write-Host '$c = '$c
Write-Host '----------------------------------------'

Function Default-Input
{
  # $input is default variable for pipe line.
  Write-Host '$input = '$input
  while ($input.MoveNext())
  {
    Write-Host 'input data = '$input.Current
  }
}

Default-Input
Write-Host '--------------------'
1..5 | Default-Input

Write-Host '----------------------------------------'
