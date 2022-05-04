import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class DnDInterfaceScene1 extends Application{
public static void main (String[] args){

launch(args);

}//end of main


public void start(Stage stage) throws Exception
{

Parent root = FXMLLoader.load(getClass().getResource("DnDBootUpMenu.fxml"));    
      Scene scene = new Scene(root);      
      stage.setTitle("Demo JavaFX with Scene BUilder");      
      stage.setScene(scene);
      stage.show();

}

}//end of program
