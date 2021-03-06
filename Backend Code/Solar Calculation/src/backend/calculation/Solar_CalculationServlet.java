package backend.calculation;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

import com.google.gson.*;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SuppressWarnings("serial")
public class Solar_CalculationServlet extends HttpServlet {
	
	House house;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/ plain");
		
		//Receiving the JSON parameter and forming a jsonObject from the house
		JsonObject json = (JsonObject) new JsonParser().parse(req.getParameter("jsonParameter"));
		JsonObject jsonHouse = json.getAsJsonObject("house");
		
		//Setting all the house attributes to our house object
		house = new House(jsonHouse.get("country").toString(), jsonHouse.get("currency").toString(),
				jsonHouse.get("lat").getAsDouble(), jsonHouse.get("lon").getAsDouble(), jsonHouse.get("wire").getAsDouble(),
				jsonHouse.get("electricityCompany").toString(), jsonHouse.get("tariff").getAsDouble(),
				jsonHouse.get("cost").getAsDouble());

		//Building the usagePerQuarter array
		JsonArray jsonUsage = jsonHouse.getAsJsonArray("usagePerQuarter");
		int usage[] = new int[5];
		
		for (int i = 0; i < 4; i++)
		{
			usage[i] = jsonUsage.get(i).getAsInt();
		}
		
		usage[4] = usage[0] + usage[1] + usage[2] + usage[3];
		
		//Setting the usagePerQuarter array
		house.setUsage(usage);
			
		//Preparing to add the roof sections
		JsonArray jsonRoof = jsonHouse.getAsJsonArray("roofSection");
		
		Roof roofSection[] = new Roof[jsonRoof.size()];
		
		//Iterate through each roof section, creating its object and adding it to the array of roofs
		for (int i = 0; i < jsonRoof.size(); i++){
			
			JsonObject currentRoof = (JsonObject) jsonRoof.get(i);
			
			roofSection[i] = new Roof(i, currentRoof.get("sectionName").getAsString(), 
					currentRoof.get("length").getAsDouble(), currentRoof.get("width").getAsDouble(), currentRoof.get("angle").getAsDouble(),
					currentRoof.get("direction").getAsInt(), currentRoof.get("numberOfCurrentPanels").getAsInt(),
					currentRoof.get("ageOfCurrentPanels").getAsInt(), currentRoof.get("sizeOfCurrentPanels").getAsDouble(),
					currentRoof.get("typeOfCurrentPanels").getAsInt());
		}
		
		//Setting the roof sections of the house
		house.setRoof(roofSection);
		
		
		String actualJson = "{\"result\": [";
		
		//Setting the scenarios
		JsonArray jsonScenarios = json.getAsJsonArray("scenarios");
		Scenario scenarios[] = new Scenario[jsonScenarios.size()];
		
		//JsonObject jsonResponse = new JsonObject();
		//JsonArray results = new JsonArray();
		
		for (int i = 0; i < jsonScenarios.size(); i++){
			
			JsonObject jsonScenario = jsonScenarios.get(i).getAsJsonObject();
			JsonArray jsonPanelsPerSection = jsonScenario.getAsJsonArray("numberOfPanelsPerSection");
			int numberOfPanelsPerSection[] = new int[jsonPanelsPerSection.size()];
			
			for (int n = 0; n < jsonPanelsPerSection.size(); n++){
				
				numberOfPanelsPerSection[n] = jsonPanelsPerSection.get(n).getAsInt();
			}
			
			scenarios[i] = new Scenario(jsonScenario.get("name").getAsString(), jsonScenario.get("typeOfNewPanels").getAsInt(),
					jsonScenario.get("sizeOfNewPanels").getAsInt(), numberOfPanelsPerSection, 
					jsonScenario.get("quotedPrice").getAsDouble());
			
			//JsonObject result = new JsonObject();
			//result.addProperty("name", scenarios[i].getName());
			//result.addProperty("quotedPrice", 0);
			
			double powerResult = CalculatePower.calculate(house, scenarios[i]);
			double powerYearly = powerResult * 365;
			double powerQuart = powerYearly / 4;
			//double 
			
			
			//Find out how long till profit break even
			double accumulatedProfit = 0;
			int months = 0;
			int years = 0;
			while (accumulatedProfit <= scenarios[i].getQuotedPrice()){
				
				months = months + 3;
				accumulatedProfit = (accumulatedProfit + (powerQuart * house.getTariff()));				
			}
			
			while (months >= 12) {
				
				years++;
				months = months - 12;
			}
			
			String graphURL = "https://chart.googleapis.com/chart?chs=600x225&cht=p3&chco=3072F3&chdl=Solar|Grid&chtt=Power+Usage&chd=t:";
			graphURL += powerQuart + "," + powerBought(powerQuart) + "&chl=" + (int)powerQuart + "+kw|" + (int)powerBought(powerQuart) + "+kw";
			graphURL += "&chds=0," + (powerQuart + powerBought(powerQuart));
			
			actualJson = actualJson + "{" +
			      "\"name\": \"" + scenarios[i].getName() + "\"," +
			      "\"quotedPrice\": 0," +
			      "\"quarterlyResults\": [" +
			        "{" +
			          "\"powerGenerated\": " + powerQuart + "," +
			          "\"powerUsed\": " + house.getUsage(0) + "," +
			          "\"powerBought\": " + powerBought(powerQuart) + "," +
			          "\"excessPowerGenerated\": " + powerGen((powerQuart - house.getUsage(0))) + "" +
			        "}," +
			        "{" +
			          "\"powerGenerated\": " + powerQuart + "," +
			          "\"powerUsed\": " + house.getUsage(1) + "," +
			          "\"powerBought\": " + powerBought(powerQuart) + "," +
			          "\"excessPowerGenerated\": " + powerGen((powerQuart - house.getUsage(1))) + "" +
			        "}," +
			        "{" +
			          "\"powerGenerated\": " + powerQuart + "," +
			          "\"powerUsed\": " + house.getUsage(2) + "," +
			          "\"powerBought\": " + powerBought(powerQuart) + "," +
			          "\"excessPowerGenerated\": " + powerGen((powerQuart - house.getUsage(2))) + "" +
			        "}," +
			        "{" +
			          "\"powerGenerated\": " + powerQuart + "," +
			          "\"powerUsed\": " + house.getUsage(3) + "," +
			          "\"powerBought\": " + powerBought(powerQuart) + "," +
			          "\"excessPowerGenerated\": " + powerGen((powerQuart - house.getUsage(3))) + "" +
			        "}" +
			      "]," +
			      "\"powerGeneratedPerYear\": " + powerYearly + "," +
			      "\"powerUsedPerYear\": " + house.getUsage(4) + "," +
			      "\"powerBoughtPerYear\": " + powerBought(((powerQuart - house.getUsage(0)) + (powerQuart - house.getUsage(1))
					      + (powerQuart - house.getUsage(2)) + (powerQuart - house.getUsage(3)))) + "," +
			      "\"excessPowerGeneratedPerYear\": " + powerGen(((powerQuart - house.getUsage(0)) + (powerQuart - house.getUsage(1))
			      + (powerQuart - house.getUsage(2)) + (powerQuart - house.getUsage(3)))) + "," +
			      "\"breakEvenTime\": {" +
			        "\"years\": " + years + "," +
			        "\"months\": " + months + "" +
			      "}," +
			      "\"moneySavedAfter1Year\": " + (powerYearly * house.getTariff()) + "," +
			      "\"moneySavedAfter5Years\": " + ((powerYearly * house.getTariff()) * 5) + "," +
			      "\"graphURL\": " + graphURL +
			    "}";
			
			if ((i + 1) < jsonScenarios.size()){
				
				actualJson = actualJson + ",";
			}
			
		}//End Scenario For Loop
		
		actualJson += "],\"status\": \"valid\"}";
		

		PrintWriter out = resp.getWriter();
		out.println(actualJson);

	}
	
	//Modifies power generated to correct format
	public double powerGen(double d){
		
		if (d < 0){
			
			return 0;
		}
		else {
			
			return d;
		}
	}
	
	//Calculates excess power bought
	public double powerBought(double power){
		
		if (power < 0){
			
			return -(power);
		}
		else{
			
			return 0;
		}
		
	}
}
