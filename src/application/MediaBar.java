package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class MediaBar extends HBox {
	
	//time and volume sliders, pause button, volume label, and media player
	Slider time; 
	Slider vol; 
	
	Button pause;
	
	Label volumeLBL;
	
	MediaPlayer player; 
	
	public MediaBar(MediaPlayer play) {
		
		//instantiates variables
		player = play;
		
		vol = new Slider();
		time = new Slider();
		
		//sets the style for the media bar
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10,10,10,10));
		setStyle("-fx-background-color:white");
		
		pause = new Button("||");
		volumeLBL = new Label("Volume : ");
		
		//adds these to the parent node, which in this case is the Scene object that the window is showing
		getChildren().add(pause);
		getChildren().add(time);
		
		getChildren().add(volumeLBL);
		getChildren().add(vol);
		
		
		vol.setPrefWidth(70);
		vol.setMinWidth(30);
		vol.setValue(100);
		

		
		//adds listener to allow the player to run more smoothly 
		player.currentTimeProperty().addListener((o)-> {
			time.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis()*100);
		});
		//listener to create the time slider functionality 
		time.valueProperty().addListener((o) -> {
			if(time.isPressed())
				player.seek(player.getMedia().getDuration().multiply(time.getValue()/100));
		});
		//adds the pause/play functionality 
		pause.setOnAction((e) -> {
			Status status = player.getStatus();
			
			if(status == Status.PLAYING) {
				player.pause();
				pause.setText(">");
			}
			else if(status == Status.PAUSED) {
				player.play();
				pause.setText("||");
			}
		});
		
		// volume listener
		vol.valueProperty().addListener((o) -> {
			System.out.println(vol.getValue());
			player.setVolume(vol.getValue()/100);
		});
		
		
	}
	
}
