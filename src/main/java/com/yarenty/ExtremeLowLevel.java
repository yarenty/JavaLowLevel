package com.yarenty;

import org.junit.Test;


/**
 * OUTPUT:
<pre>

</pre>
 * @author yarenty
 *
 */
public class ExtremeLowLevel {

	
	@Test
	public void testExtremeLowLevel() {
		int x = 2;
		System.out.println("x="+x);
		System.out.println("-------------------");

		int a = x++;   // a=2; x=3;
		System.out.println("x="+x);
		System.out.println("a="+a);
		System.out.println("-------------------");

		
		int b = ++x;   // b=4; x=4
		System.out.println("x="+x);
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("-------------------");

		
		int y = a * b; // y = 8
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("y="+y);
		System.out.println("-------------------");
		
		x = 2;
		y = x++ * ++x; 
		System.out.println("x="+x);
		System.out.println("y="+y);
		System.out.println("-------------------");
		
		System.out.println("-------------------");
		
	}
}
