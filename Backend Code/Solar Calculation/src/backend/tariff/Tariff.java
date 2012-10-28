package backend.tariff;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Tariff {

	public double tariffRate;
	public String city;
	public String country;
	
	public void setTariffRate(double tariff) 
	{
		tariffRate = tariff;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public double getTariffRate()
	{
		return tariffRate;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	
	public void storeTariffRate(String city, String country, double tariffRate)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		try {
			Key key = KeyFactory.createKey("TariffRate", city + ", " + country);
			Entity result = datastore.get(key);
		} catch (EntityNotFoundException e) {
			
			Entity hoursOfSunlight = new Entity("TariffRate", city + ", " + country);
			
			hoursOfSunlight.setProperty("lat", city);
			hoursOfSunlight.setProperty("lon", country);
			hoursOfSunlight.setProperty("Tariff", tariffRate);
			
			datastore.put(hoursOfSunlight);
		}
	}
	
	public double retrieveTariffRate(String city, String country) throws EntityNotFoundException
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Key key = KeyFactory.createKey("TariffRate", city + ", " + country);
		Entity result = datastore.get(key);
		
		double tariff = (Double) result.getProperty("Tariff");
		
		return tariff; //temporarily make it work
	}
}
