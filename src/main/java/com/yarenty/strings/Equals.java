package com.yarenty.strings;
/**
 * Equals:
 * == ::true
 * .equals() ::true
 * 
 * 
 * OUTPUT:
 * <pre>
 * Equals:
Compare string1==string2 returns true.
Compare string1==string3 returns FALSE.
Compare string1==string4 returns FALSE.
Compare string2==string3 returns FALSE.
Compare string2==string4 returns FALSE.
Compare string3==string4 returns FALSE.
Compare  string1.equals(string2)  returns true.
Compare  string1.equals(string3)  returns true.
Compare  string1.equals(string4)  returns true.
Compare  string3.equals(string4)  returns true.
 * </pre>
 * 
 * @author yarenty
 *
 */
public class Equals {

	public static void main(String[] args) {

		String string1 = "Hello World!";
		String string2 = "Hello World!";
		String string3 = new String("Hello World!");
		String string4 = new String(string2);
		String string5 = "Hello World!";

		System.out.println("Equals:");

		if (string1 == string2) {
			System.out.println("Compare string1==string2 returns true.");
		}

		if (string1 == string3) {
			System.out.println("Compare string1==string3 returns true.");
		} else {
			System.out.println("Compare string1==string3 returns FALSE.");
		}

		if (string1 == string4) {
			System.out.println("Compare string1==string4 returns true.");
		} else {
			System.out.println("Compare string1==string4 returns FALSE.");
		}
		if (string2 == string3) {
			System.out.println("Compare string2==string3 returns true.");
		} else {
			System.out.println("Compare string2==string3 returns FALSE.");
		}
		if (string2 == string4) {
			System.out.println("Compare string2==string4 returns true.");
		} else {
			System.out.println("Compare string2==string4 returns FALSE.");
		}
		if (string3 == string4) {
			System.out.println("Compare string3==string4 returns true.");
		} else {
			System.out.println("Compare string3==string4 returns FALSE.");
		}
		
		// vs.

		if (string1.equals(string2)) {
			System.out.println("Compare  string1.equals(string2)  returns true.");
		}
		if (string1.equals(string3)) {
			System.out.println("Compare  string1.equals(string3)  returns true.");
		}
		if (string1.equals(string4)) {
			System.out.println("Compare  string1.equals(string4)  returns true.");
		}
		if (string3.equals(string4)) {
			System.out.println("Compare  string3.equals(string4)  returns true.");
		}
	
	}
	
	
}
