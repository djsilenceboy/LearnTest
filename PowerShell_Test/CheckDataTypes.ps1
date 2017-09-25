$a1 = 2
Write-Host '$a1 = '$a1
Write-Host '$a1 type = '$a1.GetType()
$a2 = $a1 + 4
$a2++
Write-Host '$a2 = '$a2
Write-Host "----------------------------------------"

$b1 = 123456789123456789
Write-Host '$b1 = '$b1
Write-Host '$b1 type = '$b1.GetType()

$b2 = [byte]::MaxValue
Write-Host '[byte]::MaxValue = '$b2
Write-Host '[byte]::MaxValue type = '$b2.GetType()

$b2 = [int]::MaxValue
Write-Host '[int]::MaxValue = '$b2
Write-Host '[int]::MaxValue type = '$b2.GetType()

$b2 = [int32]::MaxValue
Write-Host '[Int32]::MaxValue = '$b2
Write-Host '[Int32]::MaxValue type = '$b2.GetType()

$b2 = [int64]::MaxValue
Write-Host '[Int64]::MaxValue = '$b2
Write-Host '[Int64]::MaxValue type = '$b2.GetType()

$b2 = [long]::MaxValue
Write-Host '[long]::MaxValue = '$b2
Write-Host '[long]::MaxValue type = '$b2.GetType()

$b2 = [decimal]::MaxValue
Write-Host '[decimal]::MaxValue = '$b2
Write-Host '[decimal]::MaxValue type = '$b2.GetType()

$b2 = [single]::MaxValue
Write-Host '[single]::MaxValue = '$b2
Write-Host '[single]::MaxValue type = '$b2.GetType()

$b2 = [double]::MaxValue
Write-Host '[double]::MaxValue = '$b2
Write-Host '[double]::MaxValue type = '$b2.GetType()
Write-Host "----------------------------------------"

[int]$c1 = 123456789123456789
[int]$c1 = 123456789
Write-Host '[int]$c1 = '$c1

[long]$c2 = 123456789123456789
Write-Host '[long]$c2 = '$c2

[byte]$c3 = 123
Write-Host '[byte]$c3 = '$c3
Write-Host "----------------------------------------"

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
Write-Host "----------------------------------------"

[boolean]$e1 = 0
Write-Host '[boolean]$e1 = '$e1
Write-Host '[boolean]$e1 type = '$e1.GetType()
[boolean]$e1 = 1
Write-Host '[boolean]$e1 = '$e1
Write-Host "----------------------------------------"

$f1 = 10, "ab", 30, 40, 50
Write-Host '$f1 = '$f1
Write-Host '$f1 type = '$f1.GetType()

[array]$f1 = 10, "ab", 30, 40, 50
Write-Host '$f1 = '$f1
Write-Host '$f1 type = '$f1.GetType()

[int[]]$f1 = 10, "ab", 30, 40, 50
Write-Host '$f1 = '$f1
Write-Host '$f1 type = '$f1.GetType()

$f2 = 1..5
Write-Host '$f2 = '$f2
Write-Host '$f2[3] = '$f2[3]

$f3 = [char]66
Write-Host '$f3 = '$f3
Write-Host '$f3 type = '$f3.GetType()
Write-Host "----------------------------------------"

# Constant variable. Cannot modify.
Set-Variable g1 -Value 123 -Option Constant
Write-Host '$g1 = '$g1
$g1 = 456
Write-Host '$g1 = '$g1
Write-Host "----------------------------------------"

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
Write-Host "----------------------------------------"

[wmi]"Win32_LogicalDisk.DeviceId='D:'"
$i1 = ([wmi]"Win32_LogicalDisk.DeviceId='D:'").FreeSpace
Write-Host '$i1 = '$i1
Write-Host "----------------------------------------"

[string]$j1 = "Hello"
Write-Host '$j1 = '$j1
Write-Host '$j1 type = '$j1.GetType()
Write-Host "----------------------------------------"

$h1 = Get-Date
Write-Host '$h1 = '$h1
Write-Host '$h1 type = '$h1.GetType()
Write-Host "----------------------------------------"
