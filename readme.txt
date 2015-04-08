To run test:
1) import project into eclipse and use “Run with jUnit”(dependency is jUnit 4.x)
2) run maven from command line - pom.xml attached

(!) Important: use java 1.7 



There are two directories:
- src/main/java which contains com.yarenty.list.SinglyLinkedList implementation
- src/test/java which contains 5 test classes:
	com.yarenty.list.SinlyLinkedListStandardMethodsTest -  with standard methods test like add/get/size
	com.yarenty.list.SinlyLinkedListIteratorTest - with tests connected to iterator 
	com.yarenty.list.SinlyLinkedListReverseIterativeTest - test for iterative operation
	com.yarenty.list.SinlyLinkedListReverseREcursiveTEst - test for recursive operation version
	com.yarenty.list.SinlyLinkedListReverseMethodComparation - simple performance test to compare 3 reverse methods (third one is from standard java Collections)

Quesitons: yarenty@gmail.com