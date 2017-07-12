# Usage:
#   Input parameters: Directory_List_Separated_By_Space
foreach ($i in $args)
{
  Get-ChildItem $i | Where Length -gt 13 | Sort Name
}
