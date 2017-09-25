# Test this script:
# .\TestFunction2.ps1 "Tom" 7
# .\TestFunction2.ps1 "Tom" 8
# .\TestFunction2.ps1 "Mary" 8
# .\TestFunction2.ps1 "Jerry" 10

Param(
  [ValidateSet("Tom", "Jerry")]
  [string]$name,
  [ValidateRange(8, 10)]
  [int]$age
)

Function Parm-Test($name, $age)
{
  Write-Host '$name = '$name
  Write-Host '$age = '$age
}

Parm-Test $name $age
