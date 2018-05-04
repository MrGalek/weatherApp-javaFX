package application;


import java.util.Optional;

import org.json.simple.parser.ParseException;

import classes.Weather;
import classes.WeatherManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class WindowController {

    @FXML
    private Label cityNameLabel;

    @FXML
    private ImageView weatherGraph;

    @FXML
    private Label temperatureLabel;
    
    @FXML
    private Button optionsButton;
    
    @FXML
    private Button showWeatherButton;
    
    @FXML
    private ToggleGroup search;
    
    @FXML
    private TextField zipCodeTF;
    
    @FXML
    private TextField prefixTF;
    
    @FXML
    private TextField cityTF;
    
    @FXML
    private RadioButton byZipCode;
    
    @FXML
    private RadioButton byCity;
    
    
    WeatherManager weatherManager;


    public WindowController(){
    		weatherManager = new WeatherManager();
    		
    }
    
	@FXML
	public void initialize() throws ParseException{
		showWeather(null);
		
		
	} 	
	 	@FXML
	 	void showWeather(MouseEvent event) throws ParseException {
	 		Weather weather;
	 		if(byZipCode.isSelected()) {
	 			weather = weatherManager.getWeather(prefixTF.getText(), zipCodeTF.getText());
	 			cityNameLabel.setText("Miejscowość o kodzie: "+zipCodeTF.getText());
	 		}
			else {
				cityNameLabel.setText(cityTF.getText());
				weather = weatherManager.getWeather(cityNameLabel.getText());
			}
	 		
			temperatureLabel.setText(String.valueOf(weather.getTemperature())+(char)176+"C");
			weatherGraph.setImage(new Image("http://openweathermap.org/img/w/"+ weather.getIcon() +".png"));
	 	}
}
