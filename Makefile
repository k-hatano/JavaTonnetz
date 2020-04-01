# Makefile
JavaTonnetz: JavaTonnetz.java
	javac JavaTonnetz.java
	jar cvfm JavaTonnetz.jar mani.mf *.class
clean:
	rm *.class
	rm *.jar
