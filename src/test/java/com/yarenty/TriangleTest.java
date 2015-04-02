/**
 * 
 */
package com.yarenty;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author yarenty
 * Write me a function that receives three integer inputs for the lengths of the sides of a
 * triangle and returns one of four values to determine the triangle type (1=scalene, 2=isosceles, 3=equilateral, 
 * 4=error). Generate test cases for the function assuming another developer coded the function
 * 
 */
public class TriangleTest {

	/**
	 * Test method for {@link com.yarenty.Triangle#checkTriangleType(int, int, int)}.
	 */
	@Test
	public final void withGivenScaleneTriangleGiveMe1() {
		assertEquals(1,Triangle.checkTriangleType(3, 4, 5));
		assertEquals(1,Triangle.checkTriangleType(5, 4, 3));		
		assertEquals(1,Triangle.checkTriangleType(3, 5, 4));
	}

	@Test
	public final void withGivenIsoscelesTriangleGiveMe2() {
		assertEquals(2,Triangle.checkTriangleType(4, 4, 5));
		assertEquals(2,Triangle.checkTriangleType(5, 4, 5));
		assertEquals(2,Triangle.checkTriangleType(4, 5, 5));
	}
	
	@Test
	public final void withGivenEquilateralTriangleGiveMe3() {
		assertEquals(3,Triangle.checkTriangleType(4, 4, 4));
	}
	
	@Test
	public final void withGivenNotTriangleGiveMeError4() {
		assertEquals(4,Triangle.checkTriangleType(4, 4, 10));
		assertEquals(4,Triangle.checkTriangleType(4, 10, 4));
		assertEquals(4,Triangle.checkTriangleType(10, 4, 4));
	}
	
	@Test
	public final void withGivenZerosGiveMeError4() {
		assertEquals(4,Triangle.checkTriangleType(0, 0, 0));
	}
	
	@Test
	public final void withGivenTwoZerosGiveMeError4() {
		assertEquals(4,Triangle.checkTriangleType(4, 0, 0));
		assertEquals(4,Triangle.checkTriangleType(0, 4, 0));
		assertEquals(4,Triangle.checkTriangleType(0, 0, 4));
	}
	
	@Test
	public final void withGivenOneZeroGiveMeError4() {
		assertEquals(4,Triangle.checkTriangleType(4, 0, 10));
		assertEquals(4,Triangle.checkTriangleType(0, 9, 10));
		assertEquals(4,Triangle.checkTriangleType(4, 9, 0));
	}
	
	@Test
	public final void withGivenOneNegativeGiveMeError4() {
		assertEquals(4,Triangle.checkTriangleType(4, -10, 10));
		assertEquals(4,Triangle.checkTriangleType(-7, 9, 10));
		assertEquals(4,Triangle.checkTriangleType(4, 9, -3));
	}
	
	@Test
	public final void withGivenNegativesGiveMeError4() {
		assertEquals(4,Triangle.checkTriangleType(-1, -1, -1));
		assertEquals(4,Triangle.checkTriangleType(-9, -3, -6));
		assertEquals(4,Triangle.checkTriangleType(-10, -1, -10));
		assertEquals(4,Triangle.checkTriangleType(-3, -20, -10));
	}
	
	@Test
	public final void withGivenTwoNegativesGiveMeError4() {
		assertEquals(4,Triangle.checkTriangleType(4, -2, -8));
		assertEquals(4,Triangle.checkTriangleType(-1, 4, -3));
		assertEquals(4,Triangle.checkTriangleType(-4, -6, 4));
	}
	
}
