
package com.yarenty;

import org.junit.Test;

import static com.yarenty.Calculations.calculateCombinations;
import static org.junit.Assert.assertEquals;

/**
 * @author yarenty
 */
public class CalculationsTest {


	@Test
	public final void testOrginalQuest() {
		assertEquals (3, calculateCombinations(new int[] {5, 5, 15, 10}, 15));
	}

	@Test
	public final void withGivenSameValuesAlways4Combinations() {
		assertEquals (4, calculateCombinations(new int[] {15, 15, 15, 15}, 15));
		assertEquals (4, calculateCombinations(new int[] {1, 1, 1, 1}, 1));
		assertEquals (4, calculateCombinations(new int[] {-5, -5, -5, -5}, -5));
	
	}

	@Test
	public final void withGivenDifferentPositionsFor3Combinations() {
		assertEquals (3, calculateCombinations(new int[] {5, 5, 5, 10}, 15));
		assertEquals (3, calculateCombinations(new int[] {5, 10, 5, 5}, 15));
		assertEquals (3, calculateCombinations(new int[] {5, 5, 10, 5}, 15));
		assertEquals (3, calculateCombinations(new int[] {5, 5, 5, 10}, 15));
		assertEquals (3, calculateCombinations(new int[] {10, 5, 5, 5}, 15));
	}
	

	@Test
	public final void withGivenDifferentPositionsFor4Combinations() {
		assertEquals (4, calculateCombinations(new int[] {5, 5, 10, 10}, 15));
		assertEquals (4, calculateCombinations(new int[] {5, 10, 5, 10}, 15));
		assertEquals (4, calculateCombinations(new int[] {5, 10, 10, 5}, 15));
		assertEquals (4, calculateCombinations(new int[] {10, 5, 5, 10}, 15));
		assertEquals (4, calculateCombinations(new int[] {10, 5, 10, 5}, 15));
		assertEquals (4, calculateCombinations(new int[] {10, 10, 5, 5}, 15));
	}
	
	@Test
	public final void testZeroScenario() {
		assertEquals (10, calculateCombinations(new int[] {0, 0, 0, 0}, 0));
		assertEquals (6, calculateCombinations(new int[] {15, 15, 15, 0}, 15));
		assertEquals (6, calculateCombinations(new int[] {15, 15, 0, 0}, 15));
		assertEquals (4, calculateCombinations(new int[] {15, 0, 0, 0}, 15));
		assertEquals (1, calculateCombinations(new int[] {0, 0, 5, 5}, 10));
	}
	

	
	@Test
	public final void testwithNegativeNumbers() {
		assertEquals (2, calculateCombinations(new int[] {-5, 5, 15, 20}, 15));
		assertEquals (1, calculateCombinations(new int[] {-5, 5, 15, 20}, -5));
		assertEquals (2, calculateCombinations(new int[] {-5, -5, -10, 20}, -10));
		assertEquals (1, calculateCombinations(new int[] {-5, 5, 15, 20}, 0));
	}	

	@Test
	public final void testwithMaxIntNumbers() {
		assertEquals (2, calculateCombinations(new int[] {Integer.MAX_VALUE, 1, 0, -1}, 0));
		assertEquals (2, calculateCombinations(new int[] {Integer.MAX_VALUE, 1, 0, -1}, 1));
		assertEquals (2, calculateCombinations(new int[] {Integer.MAX_VALUE, 1, 0, -1}, -1));
	}
	
	
	@Test
	public final void testwithMinIntNumbers() {
		assertEquals (2, calculateCombinations(new int[] {Integer.MIN_VALUE, 1, 0, -1}, 0));
		assertEquals (2, calculateCombinations(new int[] {Integer.MIN_VALUE, 1, 0, -1}, 1));
		assertEquals (2, calculateCombinations(new int[] {Integer.MIN_VALUE, 1, 0, -1}, -1));

	}
	
}
