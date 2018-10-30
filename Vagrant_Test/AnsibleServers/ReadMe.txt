================================================================================
Use "centos/7"
================================================================================
vagrant up ansibleCentOS
vagrant up ansibleDbServer
vagrant up ansibleApplicationServer
vagrant up ansibleJbossServer
vagrant up ansibleDockerDbServer
vagrant up ansibleDockerApplicationServer
vagrant up ansibleDockerServer
vagrant up ansibleDockerServer2
================================================================================
This config file:

1. Use private network Host-Only with fixed IP in same subnet.
   Netmask 192.168.10.0/24

2. Install "net-tools" package.
  config.vm.provision "shell", inline: "sudo yum install -y net-tools"

3. Enable SSH password login.
  config.vm.provision "shell", inline: <<-END
    sudo sed -i "/^PasswordAuthentication.*$/d" /etc/ssh/sshd_config
    sudo sed -i "/^#PasswordAuthentication.*$/a PasswordAuthentication yes" /etc/ssh/sshd_config
    sudo systemctl restart sshd
  END

4. Configure IP manually.
    xxx.vm.network "private_network", ip: "192.168.10.11"
================================================================================
