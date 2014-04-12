default:
	mkdir -p classes
	scalac -sourcepath ./src -d ./classes ./src/*
#	javac -cp classes -sourcepath ./src -d ./classes ./src/*.java
run:
	scala -cp ./classes JWarsMain

clean:
	rm -rf classes
