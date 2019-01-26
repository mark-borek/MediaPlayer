package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

public class Player extends BorderPane {
	//variables include a media file, a media player, a view of the media, 
	//a pane to display the media, and a media bar 
	Media media;
	MediaPlayer player; 
	MediaView view; 
	Pane mpane;
	MediaBar mediaBar;
	 
	
	public Player(String file) {
		media = new Media(file);
		player = new MediaPlayer(media);
		view = new MediaView(player);
		mpane = new Pane();
		mediaBar = new MediaBar(player);
		

		
		mpane.getChildren().add(view);
		
		
		setCenter(mpane);
		setBottom(mediaBar);
		
		player.play();
	}

}
