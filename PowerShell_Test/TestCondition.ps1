# -contains test case-insensitive.
# -ccontains test case-sensitive.

$arr1 = "ab", "cd", "ef"
Write-Host '$arr1 = '$arr1

$test = $arr1 -contains "gh"
Write-Host '$test -contains "gh" = '$test

$test = $arr1 -contains "cd"
Write-Host '$test -contains "cd" = '$test
$test = $arr1 -contains "CD"
Write-Host '$test -contains "CD" = '$test
$test = $arr1 -ccontains "CD"
Write-Host '$test -ccontains "CD" = '$test
Write-Host '----------------------------------------'

$a = $True
$b = $False
Write-Host '$a = '$a', $b = '$b
Write-Host '$a -and $b = ' ($a -and $b)
Write-Host '$b -or $a = ' ($b -or $a)
Write-Host '----------------------------------------'
