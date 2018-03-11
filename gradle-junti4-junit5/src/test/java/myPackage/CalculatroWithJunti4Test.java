package myPackage;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatroWithJunti4Test {
	
	@Test
	public void addWorksWithJunit4() {
		Calculator calc = new Calculator();
		assertEquals(3, calc.add(1, 2));
	}
}
