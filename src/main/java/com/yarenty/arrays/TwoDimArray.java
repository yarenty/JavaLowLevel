package com.yarenty.arrays;

public class TwoDimArray {

	public static void main(String[] args) {
		
		    int column;
		    int row;

		    String[][] newArray = {{"Poland","Red"},{"Ireland","Green"}};

		    for (String line[]:newArray){
		    	for(String element:line){
		    		System.out.print(element+"\t");
		    	}
		    	System.out.println();
		    }
		    
		    
		    for(row = 0; row < newArray.length; row++){
		        for(column = 0; column < newArray.length;column++){
		            System.out.print(newArray[row][column]+"\t");
		        }
		        System.out.println();
		    }
		           
	}
}