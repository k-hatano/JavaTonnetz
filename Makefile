# Makefile
JavaTonnetz: src/JavaTonnetz/JavaTonnetz.java
	javac -d classes src/JavaTonnetz/*.java
	jar cvfm JavaTonnetz.jar mani.mf -C classes/ .
clean:
	rm *.jar
	rm classes/JavaTonnetz/*.class
