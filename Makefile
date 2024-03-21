default:
	mvn compile

run:
	mvn exec:java

pure:
	rm -f Makefile

clean:
	mvn clean
	rm -f Makefile

test:
	mvn test

format:
	mvn spotless:apply
