package application;


import java.util.Optional;

import org.json.simple.parser.ParseException;

import classes.Weather;
import classes.WeatherManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
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
    
    WeatherManager weatherManager;


    public WindowController(){
    		weatherManager = new WeatherManager();
    		
    }
    
	@FXML
	public void initialize() throws ParseException{
		setWeather();
		
		
	}
    
	private void setWeather() throws ParseException{
		Weather weather = weatherManager.getWeather(cityNameLabel.getText());
		temperatureLabel.setText(String.valueOf(weather.getTemperature())+(char)176+"C");
	}
    
	 	@FXML
	    void optionsButtonOnClick(MouseEvent event) throws ParseException {
	 		TextInputDialog dialog = new TextInputDialog(cityNameLabel.getText());
	 		dialog.setTitle("Ustaw miejscowość");
	 		dialog.setContentText("Podaj nazwę miasta:");
	 		dialog.setHeaderText("");
	 		
	 		Optional<String> result = dialog.showAndWait();
	 		if (result.isPresent()){
	 			cityNameLabel.setText(result.get());
	 			setWeather();
	 		}

	    }
}
