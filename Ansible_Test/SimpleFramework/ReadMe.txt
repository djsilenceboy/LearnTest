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
Modify log and temp file path.
------------------------------------------------------------
Inside "vars_override" folder, some YAML config files define following log and temp file path:

local_output_folder: ../../../Log
local_temp_folder: ../../../Log

Change them, if necessary. And they should be created before running playbooks.
They could be absolute paths or relative paths to "Ansible\SimpleFramework\vars_override".
================================================================================
