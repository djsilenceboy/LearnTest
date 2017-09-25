Workflow Sample1
{
  $total = 0

  ForEach -Parallel ($i in 1..5000)
  {
    $Workflow:total += $i
  }

  return $total
}

$t1 = Get-Date
$ret = Sample1
$t2 = Get-Date
Write-Host '$ret = '$ret
Write-Host 'Time = '($t2 - $t1)
Write-Host "----------------------------------------"

Workflow Sample2
{
  $total = 0

  ForEach ($i in 1..5000)
  {
    $total += $i
  }

  return $total
}

$t1 = Get-Date
$ret = Sample2
$t2 = Get-Date
Write-Host '$ret = '$ret
Write-Host 'Time = '($t2 - $t1)
Write-Host "----------------------------------------"

Function Sample3
{
  $total = 0

  ForEach ($i in 1..5000)
  {
    $total += $i
  }

  return $total
}

$t1 = Get-Date
$ret = Sample3
$t2 = Get-Date
Write-Host '$ret = '$ret
Write-Host 'Time = '($t2 - $t1)
Write-Host "----------------------------------------"
