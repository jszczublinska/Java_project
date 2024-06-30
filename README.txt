========================
BASIC INFORMATIONS
========================
Author: Joanna Szczublińska

The purpose of the program:
At the beginning some Users, Channels and Videos are created then randomly chosen Videos are added to list to watched by User.
The User can change the Icon and watch Stream (from Channel) or Movie(from Video) at one time.
If Stream or Movie will end the new one will be load from the list.
If any new Movie is added to the database or a stream starts then is added to the User's list.
The User can subsribe or not and this has impact on watched Videos.
Additionaly information can be saved and loaded to the system.

Short program instructions:
1.To start a simulation you have to first press button "CREATION" (if you have already saved verion you can just click "LOAD").
2.To stop adding new videos or streams to channel and updating user you have to click "STOP" then the button changes the
color to green ( it should help you if simulation works or it is stopped). 
3.Of coure if you want to resume simulation just pressed the button again and it changes color to blue.
4.If you want to save simulation you do not have to stop first. It is enough to just click.

5. Every simulation is write into "simulation.ser" in the folder with application. It is always automatically also write from this file.

6. If you want to open html documentation jus open folder "javadoc" and then open file with name "index"


========================
BUILD OUTPUT DESCRIPTION
========================

When you build an Java application project that has a main class, the IDE
automatically copies all of the JAR
files on the projects classpath to your projects dist/lib folder. The IDE
also adds each of the JAR files to the Class-Path element in the application
JAR files manifest file (MANIFEST.MF).

To run the project from the command line, go to the dist folder and
type the following:

java -jar "Youtube.jar" 

To distribute this project, zip up the dist folder (including the lib folder)
and distribute the ZIP file.

Notes:

* If two JAR files on the project classpath have the same name, only the first
JAR file is copied to the lib folder.
* Only JAR files are copied to the lib folder.
If the classpath contains other types of files or folders, these files (folders)
are not copied.
* If a library on the projects classpath also has a Class-Path element
specified in the manifest,the content of the Class-Path element has to be on
the projects runtime path.
* To set a main class in a standard Java project, right-click the project node
in the Projects window and choose Properties. Then click Run and enter the
class name in the Main Class field. Alternatively, you can manually type the
class name in the manifest Main-Class element.
