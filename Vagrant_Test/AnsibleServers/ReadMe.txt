================================================================================
Use "centos/8"
================================================================================
vagrant up ansibleCentOS
vagrant up ansibleDbServer
vagrant up ansibleApplicationServer
vagrant up ansibleJbossServer
vagrant up ansibleDockerDbServer
vagrant up ansibleDockerApplicationServer
vagrant up ansibleDockerServer
vagrant up ansibleDockerServer2
vagrant up ansibleOpenShiftServer
================================================================================
This config file:

1. Use private network Host-Only with fixed IP in same subnet.
   Netmask 192.168.18.0/24

2. Enable SSH password login.
  config.vm.provision "shell", inline: <<-END
    sudo sed -i "/^PasswordAuthentication.*$/d" /etc/ssh/sshd_config
    sudo sed -i "/^#PasswordAuthentication.*$/a PasswordAuthentication yes" /etc/ssh/sshd_config
    sudo systemctl restart sshd
  END

3. Configure IP manually.
    xxx.vm.network "private_network", ip: "192.168.18.11"
================================================================================
