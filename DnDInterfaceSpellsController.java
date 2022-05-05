import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

import com.google.gson.Gson;

public class DnDInterfaceSpellsController
{
   
   @FXML
   private Text CastingTimeLabel;
   
   @FXML
   private Text ComponentsLabel;
   
   @FXML
   private Text DescriptionLabel;
   
   @FXML
   private Text DurationLabel;
   
   @FXML
   private Button FindSpellButton;
   
   @FXML
   private Text LevelLabel;
   
   @FXML
   private Text RangeLabel;
   
   @FXML
   private Text SchoolLabel;
   
   @FXML
   private TextField SpellChoiceTextField;
   
   @FXML
   private Text SpellNameLabel;
   
   @FXML
   void ChangeLabelText(ActionEvent event)
   {
      SpellNameLabel.setText(SpellChoiceTextField.getText());
      // Some console output for debugging purposes
      System.out.println("Spell Found");
   }

   @FXML
   void FindSpellinApi(ActionEvent event) {}
    
   private HttpClient client;
   private Spell spell;
   protected void updateSpellData(String query)
   {
      if(this.client == null)
         this.client = HttpClient.newHttpClient();

      try
      {
         HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://www.dnd5eapi.co/api/spells/" + query.replaceAll(" ","-").toLowerCase() ))
            .GET()
            .build();
         
         client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(this::processSpellData);
      }
      catch(URISyntaxException e)
      { 
         System.out.println("Issue with request");
      }
   }
          
   protected void processSpellData(String data)
   {
      System.out.println(data);
      
      Gson gson = new Gson();      
      Spell s = gson.fromJson(data, Spell.class);
   }

}//end of program