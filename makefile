SINGLE = test/single
MULTIPLE = test/multi

compile:
	javac *.java 

registry:
	rmiregistry &

server: compile
	java CalculatorServer & 

run: compile 
	java CalculatorClient 

shell: compile 
	java CalculatorClient

unit_test: compile
	java -ea TestOperation

int_test_single: compile 
	bash $(SINGLE)/run_test.sh

int_test_multiple: compile 
	bash $(MULTIPLE)/test1/run_test.sh
	bash $(MULTIPLE)/test2/run_test.sh
	bash $(MULTIPLE)/test3/run_test.sh
	bash $(MULTIPLE)/test4/run_test.sh
	bash $(MULTIPLE)/test5/run_test.sh

test: unit_test int_test_single int_test_multiple

clean: 
	python3 cleanUp.py
	rm -rf *.class 