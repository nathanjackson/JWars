default:
	javac -sourcepath ./src -d ./classes ./src/JWarsMain.java
run:
	java -cp ./classes JWarsMain
