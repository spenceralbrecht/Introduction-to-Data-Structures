#------------------------------------------------------------------------------
# Spencer Albrecht
# salbrech
# Lab 7
# Makefile for Dictionary BST
#------------------------------------------------------------------------------

# Variables
JARFILE    = DictionaryClient
MAINCLASS  = DictionaryClient
SOURCES    = Dictionary.java DictionaryClient.java DuplicateKeyException.java\
             KeyNotFoundException.java DictionaryInterface.java
CLASSES    = Dictionary.class DictionaryClient.class DuplicateKeyException.class\
             KeyNotFoundException.class DictionaryInterface.class

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)

clean:
	rm -f $(CLASSES) $(JARFILE)
