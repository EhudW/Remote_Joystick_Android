# Remote_Joystick_Android

# How to find my Local Ip version 4 address?
* In linux bash: 	  ip -c addr

* In windows cmd:   ipconfig /all

* In both cases, search for ip start with "10." 
   ,which usually in used by home loacl-net for inner ips.
   
* You can get small output length by using:

   ipconfig /all | find /i "ipv4"

   ip -o -c -f inet addr
