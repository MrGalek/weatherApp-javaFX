package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherManager {
	
	Weather weather;
	
	public Weather getWeather(String cityName)
	{
		weather = new Weather();
		try {
			apiRequest(cityName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		weather.setCity("Moskwa");
		weather.setTemperature(-12);
		return weather;
	}
	
	private void apiRequest(String cityName) throws IOException
	{
		 StringBuilder result = new StringBuilder();
	      URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid=c07b23c6584c703e30f3b92802e0a6b7&mode=xml&lang=pl&units=metric");
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      System.out.println(result.toString());
	}
}
