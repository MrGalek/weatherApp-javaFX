package application;

import java.io.IOException;

import classes.Weather;
import classes.WeatherManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class WindowController {

    @FXML
    private Label cityNameLabel;

    @FXML
    private ImageView weatherGraph;

    @FXML
    private Label temperatureLabel;
    
    WeatherManager weatherManager;


    public WindowController() 
    {
    		weatherManager = new WeatherManager();
    }
    
	@FXML
	public void initialize()
	{
		setWeather();
		
		
	}
    
	private void setWeather()
	{
		Weather weather = weatherManager.getWeather(cityNameLabel.getText());
		cityNameLabel.setText(weather.getCity());
		temperatureLabel.setText(String.valueOf(weather.getTemperature())+(char)176+"C");
	}
    
}
