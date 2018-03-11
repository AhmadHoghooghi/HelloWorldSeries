package myPackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorWithJunit5Test {
	
	@Test
	public void addWorksJunit5() {
		Calculator calc = new Calculator();
		assertEquals(3, calc.add(1, 2));
	}
}
