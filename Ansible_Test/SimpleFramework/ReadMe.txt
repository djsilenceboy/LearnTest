================================================================================
Modify "ansible.cfg" file.
------------------------------------------------------------
At least, update SSH user related values accordingly:

remote_user=jiangdu
remote_port=2222
private_key_file=../../Resource/Keys/jiangdu.rsa

The "private_key_file" defines where to find SSH private key file. It could be an absolute path or a relative path to "Ansible\SimpleFramework".
------------------------------------------------------------
Update SSH config values, if not using Cgywin and want to turn on SSH multiplexing.

ssh_args = -o ControlMaster=no
================================================================================
Work flow
------------------------------------------------------------
"Linux_RemoteProcess.yml" is main playbook.
It includes other sub-playbooks in order in "./Playbooks":

1. Common_DeleteTempSubfolder_Pre.yml
Delete previous temp folder with all output files inside, if any.

2. Common_ExecuteRemoteScript.yml
Execute function script defined in "./Scripts" on remote host.
Use "forks=" defined in "ansible.cfg" to control concurrency.

3. Common_PrepareOutputSubfolder.yml
Create temp folder to save output from remote hosts. One file for one host.

4. Common_CombineOutputFile.yml
Combine all output files from temp folder into one output file.

5. Common_DeleteTempSubfolder_Post.yml
Delete current temp folder with all output files inside.
================================================================================
Modify log and temp file path.
------------------------------------------------------------
Inside "vars_override" folder, some YAML config files define following log and temp file path:

local_output_folder: ../../../Log
local_temp_folder: ../../../Log

Change them, if necessary. And they should be created before running playbooks.
They could be absolute paths or relative paths to "Ansible\SimpleFramework\vars_override".
================================================================================
Add new host(s)
------------------------------------------------------------
To add a new set of hosts, such as "Production".

1. Define "[Production:children]" in "Hosts_Production".

2. Add its children and/or child groups.

3. Create a related text file with same name "Production" in "./group_vars".
================================================================================
Add new function
------------------------------------------------------------
To add a new function, such as "Linux_CheckUserIdAge".

1. Create a script file with same name "Linux_CheckUserIdAge.sh" in "./Scripts".

2. Create a YAML file with same name in "Linux_CheckUserIdAge.yml" in "./vars_override".

3. Create a usage file with same name in "Linux_CheckUserIdAge.txt" in "./Functions".
------------------------------------------------------------
To generate output as CSV file

Refer to sample "Linux_CheckSshLogin" sh and yml.
------------------------------------------------------------
To generate output as log file

Refer to sample "Linux_CheckUserIdAge" sh and yml.
================================================================================
