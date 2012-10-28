package backend.location;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.tariff.Tariff;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SubmitLocationServlet extends HttpServlet {

	Location location = new Location();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/ plain");
		
		double lat = Float.parseFloat(req.getParameter("lat"));
		double lon = Float.parseFloat(req.getParameter("lon"));
		
		location.setLat(lat);
		location.setLon(lon);
		
		
		
		String JSONoutput;
		
		String hoursOfSunlight = null;
		double temperature = 0;

		try {
			hoursOfSunlight = location.calculateAverageSunlight();
			temperature = location.calculateAverageTemperature();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (hoursOfSunlight == null || temperature == 0)
		{
			JSONoutput = "{" + " \"status\":" + "\"invalid\"" + " }";
		}
		else
		{
			JSONoutput = "{" + " \"status\":" + "\"valid\"" + " }";
		}
		PrintWriter out = resp.getWriter();
		out.println(JSONoutput);
	}
}
