package backend.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import backend.calculation.CalculatePower;
import backend.calculation.House;

public class CalculationTest {

	House house1;
	
	@Before
	public void setUp(){
		
		house1 = new House("Australia", "AUD", 1.0, 
				1.0, 12.5, "Energex", 
				0.10, 1000.0);
		int usage[] = {1000, 1000, 1000, 1000};
		house1.setUsage(usage);

		
	}
	
	//----NORTH EAST------
	
	@Test
	public void north0(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(0, 0.0), 0);
	}
	
	@Test
	public void north1(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(0, 1.0), 0);
	}
	
	@Test
	public void north5(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(0, 5.0), 0);
	}
	
	@Test
	public void north6(){
	
		assertEquals(0.95, CalculatePower.efficiencyDirection(0, 6.0), 0);
	}
	
	@Test
	public void north29(){
	
		assertEquals(0.95, CalculatePower.efficiencyDirection(0, 29.0), 0);
	}
	
	@Test
	public void north30(){
	
		assertEquals(0.95, CalculatePower.efficiencyDirection(0, 30.0), 0);
	}
	
	@Test
	public void north31(){
		assertEquals(1, CalculatePower.efficiencyDirection(0, 31.0), 0);
	}
	
	@Test
	public void north34(){
	
		assertEquals(1, CalculatePower.efficiencyDirection(0, 34.0), 0);
	}
	
	@Test
	public void north35(){
	
		assertEquals(0.95, CalculatePower.efficiencyDirection(0, 35.0), 0);
	}
	
	@Test
	public void north59(){
	
		assertEquals(0.95, CalculatePower.efficiencyDirection(0, 59.0), 0);
	}
	
	@Test
	public void north60(){
	
		assertEquals(0.95, CalculatePower.efficiencyDirection(0, 60.0), 0);
	}
	
	@Test
	public void north61(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(0, 61.0), 0);
	}
	
	@Test
	public void north74(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(0, 74.0), 0);
	}
	
	@Test
	public void north75(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(0, 75.0), 0);
	}
	
	@Test
	public void north84(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(0, 84.0), 0);
	}
	
	@Test
	public void north85(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(0, 85.0), 0);
	}
	
	
	//----NORTH EAST------
	
	@Test
	public void ne1(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(1, 1.0), 0);
	}
	
	@Test
	public void ne5(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(1, 5.0), 0);
	}
	
	@Test
	public void ne6(){
	
		assertEquals(0.95, CalculatePower.efficiencyDirection(1, 6.0), 0);
	}
	
	@Test
	public void ne49(){
	
		assertEquals(0.95, CalculatePower.efficiencyDirection(1, 49.0), 0);
	}
	
	@Test
	public void ne50(){
	
		assertEquals(0.95, CalculatePower.efficiencyDirection(1, 50.0), 0);
	}
	
	@Test
	public void ne51(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(1, 51.0), 0);
	}
	
	@Test
	public void ne69(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(1, 69.0), 0);
	}
	
	@Test
	public void ne70(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(1, 70.0), 0);
	}
	
	@Test
	public void ne80(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(1, 80.0), 0);
	}
	
	@Test
	public void ne81(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(1, 81.0), 0);
	}
	
	
	//----NORTH WEST------
	
	
	@Test
	public void nw1(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(7, 1.0), 0);
	}
	
	@Test
	public void nw5(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(7, 5.0), 0);
	}
	
	@Test
	public void nw6(){
	
		assertEquals(0.95, CalculatePower.efficiencyDirection(7, 6.0), 0);
	}
	
	@Test
	public void nw49(){
	
		assertEquals(0.95, CalculatePower.efficiencyDirection(7, 49.0), 0);
	}
	
	@Test
	public void nw50(){
	
		assertEquals(0.95, CalculatePower.efficiencyDirection(7, 50.0), 0);
	}
	
	@Test
	public void nw51(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(7, 51.0), 0);
	}
	
	@Test
	public void nw69(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(7, 69.0), 0);
	}
	
	@Test
	public void nw70(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(7, 70.0), 0);
	}
	
	@Test
	public void nw80(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(7, 80.0), 0);
	}
	
	@Test
	public void nw81(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(7, 81.0), 0);
	}
	
	
	//-----EAST-------
	
	@Test
	public void east0(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(2, 0.0), 0);
	}
	
	@Test
	public void east1(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(2, 1.0), 0);
	}
	
	@Test
	public void east44(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(2, 44.0), 0);
	}
	
	@Test
	public void east45(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(2, 45.0), 0);
	}
	
	@Test
	public void east64(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(2, 64.0), 0);
	}
	
	@Test
	public void east65(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(2, 65.0), 0);
	}
	
	@Test
	public void east66(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(2, 66.0), 0);
	}
	
	@Test
	public void east80(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(2, 80.0), 0);
	}
	
	@Test
	public void east81(){
	
		assertEquals(0.6, CalculatePower.efficiencyDirection(2, 81.0), 0);
	}
	
	@Test
	public void east90(){
	
		assertEquals(0.6, CalculatePower.efficiencyDirection(2, 90.0), 0);
	}
	
	
	//-----WEST-------
	
	@Test
	public void west0(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(6, 0.0), 0);
	}
	
	@Test
	public void west1(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(6, 1.0), 0);
	}
	
	@Test
	public void west44(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(6, 44.0), 0);
	}
	
	@Test
	public void west45(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(6, 45.0), 0);
	}
	
	@Test
	public void west64(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(6, 64.0), 0);
	}
	
	@Test
	public void west65(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(6, 65.0), 0);
	}
	
	@Test
	public void west66(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(6, 66.0), 0);
	}
	
	@Test
	public void west80(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(6, 80.0), 0);
	}
	
	@Test
	public void west81(){
	
		assertEquals(0.6, CalculatePower.efficiencyDirection(6, 81.0), 0);
	}
	
	@Test
	public void west90(){
	
		assertEquals(0.6, CalculatePower.efficiencyDirection(6, 90.0), 0);
	}
	
	
	//-----SOUTH EAST------
	
	@Test
	public void se0(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(3, 0.0), 0);
	}
	
	@Test
	public void se1(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(3, 1.0), 0);
	}
	
	@Test
	public void se15(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(3, 15.0), 0);
	}
	
	@Test
	public void se16(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(3, 16.0), 0);
	}
	
	@Test
	public void se30(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(3, 30.0), 0);
	}
	
	@Test
	public void se31(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(3, 31.0), 0);
	}
	
	@Test
	public void se45(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(3, 45.0), 0);
	}
	
	@Test
	public void se46(){
	
		assertEquals(0.55, CalculatePower.efficiencyDirection(3, 46.0), 0);
	}
	
	@Test
	public void se65(){
	
		assertEquals(0.55, CalculatePower.efficiencyDirection(3, 65.0), 0);
	}
	
	@Test
	public void se66(){
	
		assertEquals(0.45, CalculatePower.efficiencyDirection(3, 66.0), 0);
	}
	
	@Test
	public void se80(){
	
		assertEquals(0.45, CalculatePower.efficiencyDirection(3, 80.0), 0);
	}
	
	@Test
	public void se81(){
	
		assertEquals(0.35, CalculatePower.efficiencyDirection(3, 81.0), 0);
	}
	
	
	//-----SOUTH WEST------
	
	@Test
	public void sw0(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(5, 0.0), 0);
	}
	
	@Test
	public void sw1(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(5, 1.0), 0);
	}
	
	@Test
	public void sw15(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(5, 15.0), 0);
	}
	
	@Test
	public void sw16(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(5, 16.0), 0);
	}
	
	@Test
	public void sw30(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(5, 30.0), 0);
	}
	
	@Test
	public void sw31(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(5, 31.0), 0);
	}
	
	@Test
	public void sw45(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(5, 45.0), 0);
	}
	
	@Test
	public void sw46(){
	
		assertEquals(0.55, CalculatePower.efficiencyDirection(5, 46.0), 0);
	}
	
	@Test
	public void sw65(){
	
		assertEquals(0.55, CalculatePower.efficiencyDirection(5, 65.0), 0);
	}
	
	@Test
	public void sw66(){
	
		assertEquals(0.45, CalculatePower.efficiencyDirection(5, 66.0), 0);
	}
	
	@Test
	public void sw80(){
	
		assertEquals(0.45, CalculatePower.efficiencyDirection(5, 80.0), 0);
	}
	
	@Test
	public void sw81(){
	
		assertEquals(0.35, CalculatePower.efficiencyDirection(5, 81.0), 0);
	}
	
	
	
	//-----SOUTH------
	
	
	@Test
	public void south0(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(4, 0.0), 0);
	}
	
	@Test
	public void south1(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(4, 1.0), 0);
	}
	
	@Test
	public void south10(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(4, 10.0), 0);
	}
	
	@Test
	public void south11(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(4, 11.0), 0);
	}
	
	@Test
	public void south24(){
	
		assertEquals(0.75, CalculatePower.efficiencyDirection(4, 24.0), 0);
	}
	
	@Test
	public void south25(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(4, 25.0), 0);
	}
	
	@Test
	public void south35(){
	
		assertEquals(0.65, CalculatePower.efficiencyDirection(4, 35.0), 0);
	}
	
	@Test
	public void south36(){
	
		assertEquals(0.55, CalculatePower.efficiencyDirection(4, 36.0), 0);
	}
	
	@Test
	public void south49(){
	
		assertEquals(0.55, CalculatePower.efficiencyDirection(4, 49.0), 0);
	}
	
	@Test
	public void south50(){
	
		assertEquals(0.45, CalculatePower.efficiencyDirection(4, 50.0), 0);
	}
	
	@Test
	public void south60(){
	
		assertEquals(0.45, CalculatePower.efficiencyDirection(4, 60.0), 0);
	}
	
	@Test
	public void south61(){
	
		assertEquals(0.35, CalculatePower.efficiencyDirection(4, 61.0), 0);
	}
	
	@Test
	public void south90(){
	
		assertEquals(0.35, CalculatePower.efficiencyDirection(4, 90.0), 0);
	}
	
	
	//----FLAT-----
	
	@Test
	public void flat(){
	
		assertEquals(0.85, CalculatePower.efficiencyDirection(8, 0.0), 0);
	}
	
	
}
