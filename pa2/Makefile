# Makefile for pa2
Search:Search.class
	echo Main-class: Search > Manifest
	jar cvfm Search Manifest Search.class
	rm Manifest
	chmod +x Search
Search.class:Search.java
	javac Search.java
clean:
	rm Search.class Search
