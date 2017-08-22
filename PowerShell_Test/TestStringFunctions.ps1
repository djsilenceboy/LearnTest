$env:PSModulePath
Write-Host '----------------------------------------'

$env:PSModulePath.Split(";")
Write-Host '----------------------------------------'

$Paths = $env:PSModulePath.Split(";")
$Paths[0]
Write-Host '----------------------------------------'
