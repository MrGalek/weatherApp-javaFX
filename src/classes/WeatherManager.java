package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
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
			weather.setTemperature(parseTemeprature(response));
			weather.setConditions(parseConditions(response));
			weather.setIcon(parseIcon(response));
			
		} catch (IOException e) {
			e.printStackTrace();
		
		}
		
		return weather;
	}

	public Weather getWeather(String prefix, String code) throws ParseException {
		weather = new Weather();
		try {
			String response = apiRequest(prefix, code);
			weather.setTemperature(parseTemeprature(response));
			weather.setConditions(parseConditions(response));
			weather.setIcon(parseIcon(response));
			
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
	
	private String apiRequest(String prefix, String code) throws IOException {
		  StringBuilder result = new StringBuilder();
	      URL url = new URL("http://api.openweathermap.org/data/2.5/weather?zip="+code+","+prefix+"&appid=c07b23c6584c703e30f3b92802e0a6b7&lang=pl&units=metric");
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
		Object obj = parser.parse(json);
		
		jsonObject = (JSONObject) obj;
		obj = parser.parse(jsonObject.get("main").toString());
		
		jsonObject = (JSONObject) obj;
		return Double.parseDouble(jsonObject.get("temp").toString());
	}
	
	private String parseConditions(String json) throws ParseException 
	{	
		Object obj = parser.parse(json);
		
		jsonObject = (JSONObject) obj;
		obj = parser.parse(jsonObject.get("weather").toString());  
		
		JSONArray array = (JSONArray) obj;
		obj = parser.parse(array.get(0).toString());
		
		jsonObject = (JSONObject) obj;
		return jsonObject.get("main").toString();

		
	}
	
	private String parseIcon(String json) throws ParseException
	{
	Object obj = parser.parse(json);
		
		jsonObject = (JSONObject) obj;
		obj = parser.parse(jsonObject.get("weather").toString());  
		
		JSONArray array = (JSONArray) obj;
		obj = parser.parse(array.get(0).toString());
		
		jsonObject = (JSONObject) obj;
		return jsonObject.get("icon").toString();
	}

}
