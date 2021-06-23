# Remote_Joystick_Android

# Introduction
You can clone the project to intellij.<br/>
When the intellij notice you about the gradle, you should confirm.<br>
On your local machine project root folder create file named "local.properties"<br>
Here is my file for example (my username here is "EhudV")

>\## This file is automatically generated by Android Studio.<br>
>\# Do not modify this file -- YOUR CHANGES WILL BE ERASED!<br>
>\#<br>
>\# This file should *NOT* be checked into Version Control Systems,<br>
>\# as it contains information specific to your local configuration.<br>
>\#<br>
>\# Location of the SDK. This is only used by Gradle.<br>
>\# For customization when using a Version Control System, please read the<br>
>\# header note.<br>
><p>sdk.dir=C\:\\Users\\EhudV\\AppData\\Local\\Android\\Sdk</p>

Afterwards, next to the build icon (green hammer icon) enter edit configuration for run/debug.<br>
click on the plus icon at the top-left("add new configuration") and choose android-app(template).<br>
In the new configuration ("Unnamed") set in the tab "General" the module to be "app".<br>
when you now will try to run it within the computer using the emulator(=green play button), it should work.<br>
If you don't have installed emulator + android within, it will prompt you a message about it.<br>
We tested on Galaxy Nexus API 23 Android 6.0 (Google APIs). however it should work on other devices. (with at least 720x1280 resolution)
<br>
Notice the joystick is based on touch events, so in order to make the joystick work and start the chain that will end in the Flight gear in the pc, you must touch/ move the joystick.
<br>
<br>
<b>Downloading and installing flight gear is due to their license.</b> [Flight Gear Download site](https://www.flightgear.org/download/)

# Folder structure
* [source folder](app/src/main/),<br>contains java/com/example/remotejoysick/ folder which has all the *.java files,<br>and res/ folder which has all resources such as layout/ folder of xml which describes the gui of the views
* [presentation](Android_Remote_Joystick.pptx), which also contains uml-class diagram

# How to find my Local Ip version 4 address?
* In linux bash:     ip -c addr<br>
* In windows cmd:    ipconfig /all<br>
* In both cases, search for ip start with "10."<br> 
which usually in used by home loacl-net for inner ips.
* You can get small output length by using:<br>
  ipconfig /all | find /i "ipv4"<br>
  ip -o -c -f inet addr
* You can discover IP address of yourdomain.com by:<br>
  ping yourdomain.com<br>
  (Ctrl+C to stop the command)
