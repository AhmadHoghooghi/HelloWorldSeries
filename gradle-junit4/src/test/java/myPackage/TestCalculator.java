package myPackage;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCalculator {
	
	@Test
	public void addWorks() {
		Calculator calc = new Calculator();
		assertEquals(3, calc.add(1, 2));
	}
}
