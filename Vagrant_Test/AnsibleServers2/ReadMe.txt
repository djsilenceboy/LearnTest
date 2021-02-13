================================================================================
Use "ubuntu/xenial64"
================================================================================
vagrant up ansibleUbuntu
================================================================================
This config file:

1.
  # Adapter 2: Use private network Host-Only with fixed IP in same subnet.
  # Netmask 192.168.18.0/24
  # Host can access them.
  # They can access each other, host and Internet.

2. Map a local folder as default "/vagrant" folder. Please change it accordingly.
  config.vm.synced_folder "F:/Download/Shared", "/vagrant", owner: "root", group: "root"

3. In order to config IP manually, need to install "net-tools" package.
  config.vm.provision "shell", inline: "sudo apt install -y net-tools"

4. Also enable SSH password login.
  config.vm.provision "shell", inline: <<-END
    sudo sed -i "/^PasswordAuthentication.*$/d" /etc/ssh/sshd_config
    sudo sed -i "/^#PasswordAuthentication.*$/a PasswordAuthentication yes" /etc/ssh/sshd_config
    sudo systemctl restart sshd
  END
===============================================================================
