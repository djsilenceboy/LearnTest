Function SomeTest([int]$arr)
{
  Write-Host 'In SomeTest().'
}

Trap [SystemException]
{
  Write-Host 'In Trap().'
  Continue
}

Write-Host 'Before SomeTest.'
SomeTest "Wrong"
Write-Host 'After SomeTest.'
Write-Host '----------------------------------------'
