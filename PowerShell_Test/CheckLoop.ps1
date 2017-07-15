$max = 5

$i = 1
while ($i -le $max)
{
  Write-Host "$i"
  $i++
}

Write-Host "**********"

$i = 1
do
{
  Write-Host "$i"
  $i++
}
while ($i -le $max)

Write-Host "**********"

$i = 1
do
{
  Write-Host "$i"
  $i++
}
until ($i -gt $max)

Write-Host "**********"

for($i = 1; $i -le $max; $i++)
{
  Write-Host "$i"
}

Write-Host "**********"

$i = 1
for(;$i -le $max;)
{
  Write-Host "$i"
  $i++
}

Write-Host "**********"

foreach ($i in 1..10)
{
  Write-Host "$i"

  If ($i -ge $max) { break }
}

Write-Host "**********"

$a = "Hello"

If ($a -Like "Hello")
{
  Write-Host "It is Hello."
}
Else
{
  Write-Host "It is not Hello."
}

If ($a -NotLike "World")
{
  Write-Host "It is not World."
}

If ($a -Match "ll")
{
  Write-Host "It contains ll."
}

If ($a -NotMatch "ld")
{
  Write-Host "It contains no ld."
}

Write-Host "**********"

If ($b = 0)
{
  Write-Host "$b"
}

If ($b = 1)
{
  Write-Host "$b"
}

Write-Host "**********"

$c = 2
Switch ($c)
{
  1 {Write-Host "1:Cat"}
  2 {Write-Host "2:Dog"}
  Default {Write-Host "0:Null"}
}

Write-Host "**********"

$c = 1, 3, 2
Switch ($c)
{
  1 {Write-Host "1:Cat"}
  2 {Write-Host "2:Dog"}
  Default {Write-Host "0:Null"}
}
