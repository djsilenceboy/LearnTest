Workflow Sample1
{
  $whole = ""

  ForEach -Parallel ($i in 0..9)
  {
    $Workflow:whole += $i
  }

  return $whole
}

$ret = Sample1
Write-Host '$ret = '$ret
Write-Host "----------------------------------------"

Workflow Sample2
{
  $whole = ""

  ForEach ($i in 0..9)
  {
    $whole += $i
  }

  return $whole
}

$ret = Sample2
Write-Host '$ret = '$ret
Write-Host "----------------------------------------"

Workflow Sample3
{
  $whole = ""

  Parallel
  {
    $Workflow:whole += "a"
    $Workflow:whole += "b"
    $Workflow:whole += "c"

    Sequence
    {
      $Workflow:whole += "d"
      $Workflow:whole += "e"
    }
  }

  return $whole
}

$ret = Sample3
Write-Host '$ret = '$ret
Write-Host "----------------------------------------"
