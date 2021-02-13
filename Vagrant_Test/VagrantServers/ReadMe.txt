================================================================================
For JBoss EAP.

Download file, need login.
https://developers.redhat.com/download-manager/file/jboss-eap-7.1.0-installer.jar

Upload to "/tmp" on VM.
Then run:
Install_JBossEAP.sh
================================================================================
This config file:

1. Use private network Host-Only with fixed IP in same subnet.
   Netmask 192.168.18.0/24

2. Install software.
  config.vm.provision "shell", path: "Scripts/Prepare_Yum.sh"
  config.vm.provision "shell", path: "Scripts/Prepare_Python.sh"
  config.vm.provision "shell", path: "Scripts/Prepare_System.sh"

3. Enable SSH password login.
  config.vm.provision "shell", inline: <<-END
    sudo sed -i "/^PasswordAuthentication.*$/d" /etc/ssh/sshd_config
    sudo sed -i "/^#PasswordAuthentication.*$/a PasswordAuthentication yes" /etc/ssh/sshd_config
    sudo systemctl restart sshd
  END

4. Configure IP manually.
    xxx.vm.network "private_network", ip: "192.168.18.11"
================================================================================
