package application;
	
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
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
			
			mediaChooser = new FileChooser();
			menu = new MenuBar();
			fileMenu = new Menu("File");
			openItem = new MenuItem("open");
			
			body = new Text("Select media a file to play.");
			body.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
			
			fileMenu.getItems().add(openItem);
			menu.getMenus().add(fileMenu);
			
			BorderPane root = new BorderPane(body);
			Scene scene = new Scene(root,400,400);
			
			openItem.setOnAction((e) -> {
				
				try {
					File mediaFile = mediaChooser.showOpenDialog(primaryStage);
					if(mediaPlayer != null) {
						mediaPlayer.player.dispose();
					}
					System.out.println(mediaFile.getAbsolutePath() + " : " +mediaFile.toURI().toURL().toExternalForm());
					mediaPlayer = new Player(mediaFile.toURI().toURL().toExternalForm());
					
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				root.setCenter(mediaPlayer);
			});
			
			root.setTop(menu);
		
			
			primaryStage.widthProperty().addListener((obs,oldVal,newVal) -> {
				if(mediaPlayer != null)
					mediaPlayer.view.setFitWidth(scene.getWidth());
			});
			
		
			primaryStage.setScene(scene);
			primaryStage.setTitle("Mark Borek's Media Player");
			body.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
