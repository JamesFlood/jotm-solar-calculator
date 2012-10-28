package backend.location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Location {

	private double lat;
	private double lon;
	
	public double getLat()
	{
		return this.lat;
	}
	
	public double getLon()
	{
		return this.lon;
	}
	
	public void setLat(double lat)
	{
		this.lat = lat;
	}
	
	public void setLon(double lon)
	{
		this.lon = lon;
	}
	
	public String calculateAverageSunlight() throws IOException, ParseException, EntityNotFoundException
	{
		int totalSunlight = 0;
		for (int i = 1; i <= 12; i++)
		{
			int previousYear = Calendar.getInstance().get(Calendar.YEAR);
			String month = "0" + i;
			String day = "01";
			String jsonURL = "http://api.wunderground.com/api/3a152325c1969bdf/history_" + (previousYear-1) + 
					month + day + "/astronomy/q/" + lat + "," + lon  + ".json";
			String jsonResponse = getJSON(jsonURL);
			JsonObject json = new JsonParser().parse(jsonResponse).getAsJsonObject();
			JsonObject moonPhase = json.getAsJsonObject("moon_phase");
			
			JsonObject sunrise = moonPhase.getAsJsonObject("sunrise");
			int sunriseHour = sunrise.get("hour").getAsInt();
			int sunriseMinute = sunrise.get("minute").getAsInt();
			
			JsonObject sunset = moonPhase.getAsJsonObject("sunset");
			int sunsetHour = sunset.get("hour").getAsInt();
			int sunsetMinute = sunset.get("minute").getAsInt();
			
			int hoursOfSunlight = subtractTimes(sunriseHour, sunriseMinute, sunsetHour, sunsetMinute);
			
			totalSunlight += hoursOfSunlight;
		}
		double averageSunlight = totalSunlight / 12;
		
		storeInDatastore(lat, lon, "HoursOfSunlight", averageSunlight);
		double datastoreValue = retrieveFromDatastore(lat, lon, "HoursOfSunlight");
		
		int hours = (int) (datastoreValue / 3600);
		double hoursRemainder = datastoreValue % 3600;
		int minutes = (int) (hoursRemainder / 60);
		double seconds = hoursRemainder % 60;
		String time = hours + ":"+ minutes;
		
		return lat + " " + lon + " " + time;
	}
	
	public int subtractTimes(int sunriseHours, int sunriseMins, int sunsetHours, int sunsetMins)
	{
		int totalSunriseSecs = (sunriseHours * 3600) + (sunriseMins * 60);
		int totalSunsetSecs = (sunsetHours * 3600) + (sunsetMins * 60);
		int difference = totalSunsetSecs - totalSunriseSecs;
		return difference;
	}
	
	public String getJSON(String url) throws IOException
	{
		URL jsonURL = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) jsonURL.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null)
		{
			sb.append(line+"\n");
		}
		br.close();
		String result = sb.toString();
		return result;
	}
	
	public double calculateAverageTemperature() throws IOException, EntityNotFoundException
	{
		int totalTemperature = 0;
		for (int i = 1; i <= 12; i++)
		{
			int previousYear = Calendar.getInstance().get(Calendar.YEAR);
			String month = "0" + i;
			String day = "01";
			String jsonURL = "http://api.wunderground.com/api/3a152325c1969bdf/history_" + (previousYear-1) + 
					month + day + "/q/" + lat + "," + lon  + ".json";
			String jsonResponse = getJSON(jsonURL);
			JsonObject json = new JsonParser().parse(jsonResponse).getAsJsonObject();
			
			JsonObject history = json.getAsJsonObject("history");
			JsonArray dailySummary = history.getAsJsonArray("dailysummary");
			JsonObject summary = dailySummary.get(0).getAsJsonObject();
			
			int meanTemp = summary.get("meantempm").getAsInt();
			
			totalTemperature += meanTemp;
		}
		int averageTemperature = totalTemperature / 12;
		storeInDatastore(lat, lon, "Temperature", averageTemperature);
		double temperature = retrieveFromDatastore(lat, lon, "Temperature");
		
		return temperature;
	}
	
	public void storeInDatastore(double lat, double lon, String name, double value)
	{
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		try {
			Key key = KeyFactory.createKey(name, lat + ", " + lon);
			Entity result = datastore.get(key);
		} catch (EntityNotFoundException e) {
			
			Entity hoursOfSunlight = new Entity(name, lat + ", " + lon);
			
			hoursOfSunlight.setProperty("lat", lat);
			hoursOfSunlight.setProperty("lon", lon);
			hoursOfSunlight.setProperty(name, value);
			
			datastore.put(hoursOfSunlight);
		}
		
	}
	
	public double retrieveFromDatastore(double lat, double lon, String name) throws EntityNotFoundException
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Key key = KeyFactory.createKey(name, lat + ", " + lon);
		Entity result = datastore.get(key);
		
		double sunlightHours = (Double) result.getProperty(name);
		
		return sunlightHours;
	}
}
