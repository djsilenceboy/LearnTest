$caption = "Please select an action"
$message = "Select an action"
$choices = [System.Management.Automation.Host.ChoiceDescription[]]@("&Load", "Sa&ve", "&Quit")
[int]$defaultChoice = 2
$choice = $host.ui.PromptForChoice($caption, $message, $choices, $defaultChoice)

switch($choice)
{
  0 {"Loading"}
  1 {"Saving"}
  2 {"Quiting"}
}
