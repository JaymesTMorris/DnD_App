import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Platform;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

import com.google.gson.*;

public class DnDInterfaceSpellsController implements Initializable
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
    
   private HttpClient client;
   private Spell spell;
   
   @FXML
   protected void ChangeLabelText(ActionEvent event)
   {
      // SpellNameLabel.setText(SpellChoiceTextField.getText());
//       // Some console output for debugging purposes
//       System.out.println("Spell Found");
   }

   @FXML
   protected void FindSpellinApi(ActionEvent event)
   {
      //updateSpellData(SpellChoiceTextField.getText());
   }
   
   protected void updateUI()
   {
      CastingTimeLabel.setText(this.spell.casting_time);
      ComponentsLabel.setText(commaFlatten(this.spell.components));
      DescriptionLabel.setText(paraFlatten(this.spell.desc));
      DurationLabel.setText(this.spell.duration);
      LevelLabel.setText(Integer.toString(this.spell.level));
      RangeLabel.setText(this.spell.range);
      SchoolLabel.setText(this.spell.school.name);
      SpellNameLabel.setText(this.spell.name);
   }
   
   protected void updateSpellData(String query)
   {
      if(this.client == null)
         this.client = HttpClient.newHttpClient();
   
      try
      {
         HttpClient client = HttpClient.newHttpClient();
         HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://www.dnd5eapi.co/api/spells/" + query.replaceAll(" ","-").toLowerCase() + System.getenv("APIKEY") ))
            .GET()
            .build();
         
         client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(this::processSpellData);
      }
      catch(URISyntaxException e)
      { 
         System.out.println("Request Issue.");
      }
   }
          
   protected void processSpellData(String data)
   {
      System.out.println(data);
      
      Gson gson = new Gson();      
      this.spell = gson.fromJson(data, Spell.class);
      
      Platform.runLater( 
         new Runnable() {
            public void run() {
               updateUI();
            }
         });
   }
   
   private String commaFlatten(String[] input)
   {
      String output = "";
      
      if(input.length != 0)
      {
         output += input[0];
         
         for(int i = 1; i < input.length; i++)
         {
            output += ", " + input[i];
         }
      }
      
      return output;
   }
   
   private String paraFlatten(String[] input)
   {
      String output = "";
      
      if(input.length != 0)
      {
         output += "\t" + input[0];
         
         for(int i = 1; i < input.length; i++)
         {
            output += "\n\t" + input[i];
         }
      }
      
      return output;
   }
   
   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
      updateSpellData("fireball");
   }

}//end of program