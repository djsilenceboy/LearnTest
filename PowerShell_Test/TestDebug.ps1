# Without "cmdletbinding[]", there is no effect for "Write-Debug" and "-Debug"!
Function Sample1
{
  Write-Host 'Can you see following debug message?'
  Write-Debug 'The debug message.'
}

Sample1
Write-Host '--------------------'
Sample1 -Debug
Write-Host '----------------------------------------'

# Use "cmdletbinding[]", in order to use "Write-Debug" and "-Debug"!
Function Sample2
{
  [cmdletbinding()]
  Param()
  Write-Host 'Can you see following debug message?'
  Write-Debug 'The debug message.'
}

Sample2
Write-Host '--------------------'
Sample2 -Debug
Write-Host '----------------------------------------'

# Set-StrictMode is local/child scope.
Set-StrictMode -Version 1
Write-Host "`$a can be expended $a."
Write-Host '$a does not exist: '$a
# Set-StrictMode -Off
Write-Host '----------------------------------------'
