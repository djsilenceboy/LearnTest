Try
{
  [int]$a = "Hello"
  Write-Host '$a type = '$a.GetType()
}
Catch [System.Exception]
{
  Write-Host 'Catch exception = '
  $Error[0] | Format-List * -Force
}
Finally
{
  Write-Host 'Finally.'
}
Write-Host '----------------------------------------'

Try
{
  [int]$a = "Hello"
  Write-Host '$a type = '$a.GetType()
}
Catch [System.Management.Automation.PSInvalidCastException]
{
  Write-Host 'Catch System.Management.Automation.PSInvalidCastException = '
  $Error[0] | Format-List * -Force
}
Catch [System.Exception]
{
  Write-Host 'Catch exception = '
  $Error[0] | Format-List * -Force
}
Finally
{
  Write-Host 'Finally.'
}
Write-Host '----------------------------------------'
