Filter Sample
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

# Pass whole array to function.
Function AddA
{
  "Print once."
  while ($input.MoveNext())
  {
    Write-Host '$input = '$input.Current
  }
}

# Pass each element to filter through pipeline.
Filter AddB
{
  "Print each time."
  Write-Host '$_ = '$_
}

# A function works as a filter.
Function AddC
{
  # Do not add extra lines here.
  Process
  {
    "Print each time."
    Write-Host '$_ = '$_
  }
}

1..5 | AddA

Write-Host '--------------------'
1..5 | AddB

Write-Host '--------------------'
1..5 | AddC

Write-Host '----------------------------------------'
