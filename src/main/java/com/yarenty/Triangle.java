/**
 * 
 */
package com.yarenty;

/**
 * @author yarenty
 *
 */
public class Triangle {


/**
 * Write me a function that receives three integer inputs for the lengths of the sides of a
 * triangle and returns one of four values to determine the triangle type (1=scalene, 2=isosceles, 3=equilateral, 
 * 4=error). Generate test cases for the function assuming another developer coded the function
 * 
 * 
 * @param x
 * @param y
 * @param z
 * @return
 */
	public static int checkTriangleType(int x, int y, int z) {
		//check for obvious error - one of sides are 0 or negative
		//in case of Integer instead int - check for null here
		if (x<=0 || y<=0 || z<=0) return 4;
		//check if this is equilateral;
		if (x==y && y==z) return 3;
		//check if this is triangle;
		if (x+y>z  && x+z>y && y+z>x) {
			//isosceles
			if (x==y || y==z || x==z) return 2;
			return 1;		
		}
		//otherwise not triangle 
		return 4;
	}
	
	
}
