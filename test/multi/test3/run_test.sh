PWD=$(pwd)/test/multi/test3

java CalculatorClient -d < "${PWD}/test0"
java CalculatorClient -d < "${PWD}/test11" 
java CalculatorClient -d < "${PWD}/test12" | diff -wEB "${PWD}/output12" -
java CalculatorClient -d < "${PWD}/test13" | diff -wEB "${PWD}/output13" -