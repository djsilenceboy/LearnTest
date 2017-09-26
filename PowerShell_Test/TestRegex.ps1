$text = "abcdab12"
Write-Host '$text = '$text
Write-Host "----------------------------------------"

$regex = [regex]"ab"
Write-Host '$$regex = '$regex
Write-Host '$$regex type = '$regex.GetType()

$match = $regex.Matches($text)
Write-Host '$match = '$match
Write-Host '$match.Count = '$match.Count
Write-Host "----------------------------------------"

$regex = [regex]"[ab]"
Write-Host '$$regex = '$regex

$match = $regex.Matches($text)
Write-Host '$match = '$match
Write-Host '$match.Count = '$match.Count
Write-Host "----------------------------------------"

$regex = [regex]"[^ab]"
Write-Host '$$regex = '$regex

$match = $regex.Matches($text)
Write-Host '$match = '$match
Write-Host '$match.Count = '$match.Count
Write-Host "----------------------------------------"

$regex = [regex]"[\d]"
Write-Host '$$regex = '$regex

$match = $regex.Matches($text)
Write-Host '$match = '$match
Write-Host '$match.Count = '$match.Count
Write-Host "----------------------------------------"

$regex = [regex]"[\D]"
Write-Host '$$regex = '$regex

$match = $regex.Matches($text)
Write-Host '$match = '$match
Write-Host '$match.Count = '$match.Count
Write-Host "----------------------------------------"
