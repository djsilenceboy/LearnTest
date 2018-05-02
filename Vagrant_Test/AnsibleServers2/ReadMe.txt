================================================================================
Use "ubuntu/xenial64"
================================================================================
vagrant up ansibleUbuntu
================================================================================
This config file:

1. Use DHCP gateway.
  config.vm.network "public_network", use_dhcp_assigned_default_route: true

2. But not use DHCP IP, will configure IP manually.
  config.vm.network "public_network", auto_config: false

3. Map a local folder as default "/vagrant" folder. Please change it accordingly.
  config.vm.synced_folder "F:/Download/Shared", "/vagrant", owner: "root", group: "root"

4. In order to config IP manually, need to install "net-tools" package.
  config.vm.provision "shell", inline: "sudo apt install -y net-tools"

5. Also enable SSH password login.
  config.vm.provision "shell", inline: <<-END
    sudo sed -i "/^PasswordAuthentication.*$/d" /etc/ssh/sshd_config
    sudo sed -i "/^#PasswordAuthentication.*$/a PasswordAuthentication yes" /etc/ssh/sshd_config
    sudo systemctl restart sshd
  END

6. To determine the default network interface,
a) not add that "ifconfig" line first;
b) start VM and "vagrant ssh" login;
c) use "ip a" to find the default network interface, such as "eth1" or "enp0s8".

7. Configure IP manually. Please change network interface, IP and netmask accordingly.
    ansibleCentOS.vm.provision "shell", run: "always", inline: "ifconfig eth1 192.168.0.41 netmask 255.255.255.0 up"

8. Configure default gateway. Please change gateway IP and network interface accordingly.
    ansibleUbuntu.vm.provision "shell", run: "always", inline: "route add default gw 192.168.0.1 enp0s8"
================================================================================
