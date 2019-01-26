package application;
	
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	
	FileChooser mediaChooser; 
	
	Player mediaPlayer;
	
	MenuBar menu;
	Menu fileMenu;
	MenuItem openItem;

	Text body; 
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//instantiates variables to choose media, create menu bar with file tab and open dropdown
			mediaChooser = new FileChooser();
			menu = new MenuBar();
			fileMenu = new Menu("File");
			openItem = new MenuItem("open");
			
			//instantiates text to fill the body of the initial media player window
			body = new Text("Select media (.mp4) a file to play.");
			body.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
			body.setStyle("-fx-text-align: center");
			
			//adds open to file menu and file to the main menu bar
			fileMenu.getItems().add(openItem);
			menu.getMenus().add(fileMenu);
			
			//creates borderpane and scene 
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			
			
			//defines what happens when the open file menu item is clicked
			openItem.setOnAction((e) -> {
				
				try {
					File mediaFile = mediaChooser.showOpenDialog(primaryStage);
					// gets rid of old file if new one selected
					if(mediaPlayer != null) {
						mediaPlayer.player.dispose();
					}
					//System.out.println(mediaFile.getAbsolutePath() + " : " +mediaFile.toURI().toURL().toExternalForm());
					mediaPlayer = new Player(mediaFile.toURI().toURL().toExternalForm());
					
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				root.setCenter(mediaPlayer);
			});
			
			//sets the menu on the top and the body text in the center of the borderpane
			root.setTop(menu);
			root.setAlignment(body, Pos.CENTER);
			root.setMargin(body, new Insets(25,25,25,40));
			
		
			//allows the ability to resize the window and have the media player fit 
			primaryStage.widthProperty().addListener((obs,oldVal,newVal) -> {
				if(mediaPlayer != null)
					mediaPlayer.view.setFitWidth(scene.getWidth());
			});
			
			//uses the Stage parameter to set the scene and title of the window
			primaryStage.setScene(scene);
			primaryStage.setTitle("Mark Borek's Media Player");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
