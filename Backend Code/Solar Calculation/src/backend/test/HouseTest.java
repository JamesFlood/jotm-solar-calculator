package backend.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


import backend.calculation.House;

public class HouseTest {

	House House1;
	
	@Before
	public void setUp(){
		
		House1 = new House("Australia", "AUD", 100.0, 50.0, 12.5, "Energex", 0.5, 19000.0);
		
	}
	
	
	@Test
	public void getCountry(){
		assertTrue(House1.getCountry()=="Australia");
	}
	
	@Test
	public void getCurency(){
		assertTrue(House1.getCurrency()=="AUD");
	}
	
	@Test
	public void getLat(){
		assertTrue(House1.getLat()==100.0);
	}
	
	@Test
	public void getLon(){
		assertTrue(House1.getLon()==50.0);
	}
	
	@Test
	public void getWire(){
		assertTrue(House1.getWire()==12.5);
	}
	
	@Test
	public void getComapny(){
		assertTrue(House1.getElectricityCompany()=="Energex");
	}
	
	@Test
	public void getTariff(){
		assertTrue(House1.getTariff()==0.5);
	}
	
	@Test
	public void getCost(){
		assertTrue(House1.getCost()==19000.0);
	}
	
	
	
	
	
}

