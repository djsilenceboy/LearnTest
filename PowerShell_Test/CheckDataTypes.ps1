$a1 = 2
Write-Host '$a1 = '$a1
$a2 = $a1 + 4
$a2++
Write-Host '$a2 = '$a2

$b1 = 123456789123456789
Write-Host '$b1 is string = '$b1

$b2 = [byte]::MaxValue
Write-Host '[byte]::MaxValue = '$b2
$b2 = [int]::MaxValue
Write-Host '[int]::MaxValue = '$b2
$b2 = [int32]::MaxValue
Write-Host '[Int32]::MaxValue = '$b2
$b2 = [int64]::MaxValue
Write-Host '[Int64]::MaxValue = '$b2
$b2 = [long]::MaxValue
Write-Host '[long]::MaxValue = '$b2
$b2 = [decimal]::MaxValue
Write-Host '[decimal]::MaxValue = '$b2
$b2 = [single]::MaxValue
Write-Host '[single]::MaxValue = '$b2
$b2 = [double]::MaxValue
Write-Host '[double]::MaxValue = '$b2

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

$f1 = 10, "ab", 30, 40, 50
Write-Host '$f1 = '$f1
[array]$f1 = 10, "ab", 30, 40, 50
Write-Host '$f1 = '$f1
[int[]]$f1 = 10, "ab", 30, 40, 50
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

# Both @" and "@ should be stand-alone in separated lines.
$h1 = @"
Line1
Line2
Line3
"@
Write-Host '$h1 = '$h1
$h2 = "Line1
Line2
Line3"
Write-Host '$h2 = '$h2

[wmi]"Win32_LogicalDisk.DeviceId='D:'"
$i1 = ([wmi]"Win32_LogicalDisk.DeviceId='D:'").FreeSpace
Write-Host '$i1 = '$i1
