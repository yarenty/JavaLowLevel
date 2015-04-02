package com.yarenty.list;

import java.util.Arrays;

public class XXEmptySinglyLinkedListSublistTestCase extends XXAbstractSinglyLinkedListTestCase {
        
        void allocateSingleLinkedLists() {
                list = (SinglyLinkedList<Integer>) new SinglyLinkedList<Integer>().subList(0, 0);
                oneElement = (SinglyLinkedList<Integer>) new SinglyLinkedList<Integer>(Arrays.asList(12, 11)).subList(0, 1);
                empty = (SinglyLinkedList<Integer>) new SinglyLinkedList<Integer>().subList(0, 0);          
        }                       
}