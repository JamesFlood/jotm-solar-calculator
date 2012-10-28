package backend.calculation;



public class CalculatePower {

	//Direction is [North, NE, East, SE, South, SW, West, NW, Flat]
	private static int NORTH = 0;
	private static int NE = 1;
	private static int EAST = 2;
	private static int SE = 3;
	private static int SOUTH = 4;
	private static int SW = 5;
	private static int WEST = 6;
	private static int NW = 7;
	private static int FLAT = 8;
	
	private static double HOURS_OF_SUNLIGHT = 4.5;
	
	private static Double EFFICIENCY_LOSS_PER_YEAR = 0.007;
	private static Double EFFICIENCY_LOSS_FROM_INVERTER = 0.96;
	
	//Average Daily Solar Generation = (System Size * 
	//Efficiency Loss from Roof, Age, Inverter) *  
	//Average Hours of Sunlight
	
	//Daily power generation
	public static double calculate(House house, Scenario scenario){
		
		Double roofPower[] = new Double[house.numberOfRoofs()];
		Double totalPower = 0.0;
		
		for (int i = 0; i < house.numberOfRoofs(); i++){
			
			roofPower[i] = ((house.getRoof(i).getSizeOfCurrentPanels() * house.getRoof(i).getNumberOfCurrentPanels()) * roofEfficiency(house.getRoof(i))) * HOURS_OF_SUNLIGHT;
			totalPower = totalPower + roofPower[i];
		}
		
		for (int z=0; z < scenario.numberOfNewPanels(); z++) {
			
			totalPower = totalPower + ((scenario.getNumberOfPanelsPerSection(z) * roofEfficiency(house.getRoof(z))) * HOURS_OF_SUNLIGHT);
		}
		
		return totalPower;
	}
	
	public static double roofEfficiency(Roof roof){
		
		Double efficiency = efficiencyDirection(roof.getDirection(), roof.getAngle()) * 
				(1 - (roof.getAgeOfCurrentPanels() * EFFICIENCY_LOSS_PER_YEAR)) * EFFICIENCY_LOSS_FROM_INVERTER;
		
		return efficiency;
	}
	
	public static double efficiencyDirection(int direction, double angle){
		
		double efficiency = 0;
		
		if (direction == NORTH){
			
			if (angle > 30 && angle < 35){
				efficiency = 1;
			}
			
			if ((angle > 5 && angle <= 30) || (angle >= 35 && angle <= 60)){
				efficiency = 0.95;
			}
			
			if ((angle >= 0 && angle <= 5) || (angle > 60 && angle < 75)){
				efficiency = 0.85;
			}
			
			if (angle >= 75 && angle < 85){
				efficiency = 0.75;
			}
			
			if (angle >= 85){
				efficiency = 0.65;
			}
		}
		
		if (direction == NE || direction == NW){
			
			if (angle > 5 && angle <= 50){
				efficiency = 0.95;
			}
			
			if (angle <= 5 || (angle > 50 && angle < 70)){
				efficiency = 0.85;
			}
			
			if (angle >= 70 && angle <= 80){
				efficiency = 0.75;
			}
			
			if (angle > 80){
				efficiency = 0.65;
			}
		}
		
		if (direction == EAST || direction == WEST){
			
			if (angle >= 0 && angle < 45){
				efficiency = 0.85;
			}
			
			if (angle >= 45 && angle <=65){
				efficiency = 0.75;
			}
			
			if (angle > 65 && angle <= 80){
				efficiency = 0.65;
			}
			
			if (angle > 80){
				efficiency = 0.6;
			}
		}
		
		if (direction == SE || direction == SW){
			
			if (angle >= 0 && angle <= 15){
				efficiency = 0.85;
			}
			
			if (angle > 15 && angle <= 30){
				efficiency = 0.75;
			}
			
			if (angle >30  && angle <= 45){
				efficiency = 0.65;
			}
			
			if (angle > 45 && angle <= 65){
				efficiency = 0.55;
			}
			
			if (angle > 65 && angle <= 80){
				efficiency = 0.45;
			}
			
			if (angle > 80){
				efficiency = 0.35;
			}
		}
		
		if (direction == SOUTH){
			
			if (angle >= 0 && angle <= 10){
				efficiency = 0.85;
			}
			
			if (angle > 10 && angle < 25){
				efficiency = 0.75;
			}
			
			if (angle >= 25 && angle <= 35){
				efficiency = 0.65;
			}
			
			if (angle > 35 && angle < 50){
				efficiency = 0.55;
			}
			
			if (angle >= 50 && angle <= 60){
				efficiency = 0.45;
			}
			
			if (angle > 60){
				efficiency = 0.35;
			}
		}
		
		if (direction == FLAT){
			efficiency = 0.85;
		}
		
		return efficiency;
	}
	
	
}
