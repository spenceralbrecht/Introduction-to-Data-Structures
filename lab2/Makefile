# Spencer Albrecht
# salbrech
# 12M
# Makefile for lab2
FileCopy: FileCopy.class
	echo Main-class: FileCopy > Manifest
	jar cvfm FileCopy Manifest FileCopy.class
	rm Manifest
	chmod +x FileCopy
FileCopy.class: FileCopy.java
	javac FileCopy.java
clean:
	rm -f FileCopy.class
