Write-Host '================================================================================'
Get-WmiObject Win32_Service | Select -First 10 | Format-Table -AutoSize

Write-Host '================================================================================'
Get-WmiObject Win32_OperatingSystem | Format-Table -AutoSize

Write-Host '================================================================================'
Get-WmiObject Win32_LogicalDisk | Format-Table -AutoSize

Write-Host '================================================================================'
Get-WmiObject Win32_NetworkAdapterConfiguration | Format-Table -Property IPEnabled, DHCPEnabled, IPAddress, IPSubNet, DefaultIPGateway, Description -AutoSize
