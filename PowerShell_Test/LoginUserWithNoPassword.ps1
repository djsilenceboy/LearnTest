# Usage:
#   Input parameters: Directory_List_Separated_By_Space
# Sample input:
#   "localhost"
foreach ($i in $args)
{
  Write-Host "Connecting to" $i "......"
  Get-WmiObject -ComputerName $i -Class win32_UserAccount | Select Name, Disable, PasswordRequired | Where {$_.PasswordRequired -eq 0} | Sort Name | Write-Host
}
