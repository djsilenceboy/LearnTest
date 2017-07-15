$a1 = 2
Write-Host '$a1 = '$a1
$a2 = $a1 + 4
$a2++
Write-Host '$a2 = '$a2

$b = 123456789123456789
Write-Host '$b is string = '$b

[int]$c1 = 123456789123456789
[int]$c1 = 123456789
Write-Host '[int]$c1 = '$c1

[long]$c2 = 123456789123456789
Write-Host '[long]$c2 = '$c2

[byte]$c3 = 123
Write-Host '[byte]$c3 = '$c3

[single]$d1 = 1.2345678
Write-Host '[single]$d1 = '$d1
[single]$d1 = 123456780
Write-Host '[single]$d1 = '$d1

[double]$d2 = 1.234567890123456
Write-Host '[double]$d2 = '$d2
[double]$d2 = 12345678901234560
Write-Host '[double]$d2 = '$d2

[decimal]$d3 = 1.234567890123456
Write-Host '[decimal]$d3 = '$d3
[decimal]$d3 = 12345678901234567890123456789
Write-Host '[decimal]$d3 = '$d3

[boolean]$e1 = 0
Write-Host '[boolean]$e1 = '$e1
[boolean]$e1 = 1
Write-Host '[boolean]$e1 = '$e1

$f1 = 10, "20", 30, 40, 50
Write-Host '$f1 = '$f1
[array]$f1 = 10, "20", 30, 40, 50
Write-Host '$f1 = '$f1

$f2 = 1..5
Write-Host '$f2 = '$f2
Write-Host '$f2[3] = '$f2[3]

$f3 = [char]66
Write-Host '$f3 = '$f3

# Constant variable. Cannot modify.
Set-Variable g1 -Value 123 -Option Constant
Write-Host '$g1 = '$g1
$g1 = 456
Write-Host '$g1 = '$g1
