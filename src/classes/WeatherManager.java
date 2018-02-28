package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherManager {
	
	Weather weather;
	JSONParser parser;
	JSONObject jsonObject;
	
	public WeatherManager()
	{
		parser = new JSONParser();
	}
	
	public Weather getWeather(String cityName) throws ParseException
	{
		weather = new Weather();
		try {
			String response = apiRequest(cityName);
			weather.temperature = parseTemeprature(response);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return weather;
	}
	
	private String apiRequest(String cityName) throws IOException
	{
		 StringBuilder result = new StringBuilder();
	      URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid=c07b23c6584c703e30f3b92802e0a6b7&lang=pl&units=metric");
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      return result.toString();
	}
	
	private double parseTemeprature(String json) throws ParseException
	{
		System.out.println(json);
		Object obj = parser.parse(json);
		
		jsonObject = (JSONObject) obj;
		obj = parser.parse(jsonObject.get("main").toString());

		jsonObject = (JSONObject) obj;
		double temperature = Double.parseDouble(jsonObject.get("temp").toString());
		return temperature;
	}
}
