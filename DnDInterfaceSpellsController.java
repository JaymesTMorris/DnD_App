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

public class SpellController {

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
    void ChangeLabelText(ActionEvent event) {
   
       SpellNameLabel.setText(SpellChoiceTextField.getText());
      // Some console output for debugging purposes
      System.out.println("Spell Found");

    }

    @FXML
    void FindSpellinApi(ActionEvent event) {

    }
    
    private HttpClient client;
    private SpellController spell;
    protected void updateWeatherData() {
   
      if(this.client == null)
         this.client = HttpClient.newHttpClient();

}
     try {
            
            HttpRequest request = HttpRequest.newBuilder()            
              .uri(new URI("https://www.dnd5eapi.co/" + System.getenv("APIKEY") ))
              .GET()
              .build();
              
          
           client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::processSpellData);     
           
          }
          
          
  protected void SpellContent(String data) {   
      
       String data = """
                    {"_id":"624f9e31538f97fd48552937","index":"fireball","name":"Fireball","desc":["A bright streak flashes from your pointing finger to a point you choose within range and then blossoms with a low roar into an explosion of flame. Each creature in a 20-foot-radius sphere centered on that point must make a dexterity saving throw. A target takes 8d6 fire damage on a failed save, or half as much damage on a successful one.","The fire spreads around corners. It ignites flammable objects in the area that aren't being worn or carried."],"higher_level":["When you cast this spell using a spell slot of 4th level or higher, the damage increases by 1d6 for each slot level above 3rd."],"range":"150 feet","components":["V","S","M"],"material":"A tiny ball of bat guano and sulfur.","ritual":false,"duration":"Instantaneous","concentration":false,"casting_time":"1 action","level":3,"damage":{"damage_type":{"index":"fire","name":"Fire","url":"/api/damage-types/fire"},"damage_at_slot_level":{"3":"8d6","4":"9d6","5":"10d6","6":"11d6","7":"12d6","8":"13d6","9":"14d6"}},"dc":{"dc_type":{"index":"dex","name":"DEX","url":"/api/ability-scores/dex"},"dc_success":"half"},"area_of_effect":{"type":"sphere","size":20},"school":{"index":"evocation","name":"Evocation","url":"/api/magic-schools/evocation"},"classes":[{"index":"sorcerer","name":"Sorcerer","url":"/api/classes/sorcerer"},{"index":"wizard","name":"Wizard","url":"/api/classes/wizard"}],"subclasses":[{"index":"lore","name":"Lore","url":"/api/subclasses/lore"},{"index":"fiend","name":"Fiend","url":"/api/subclasses/fiend"}],"url":"/api/spells/fireball"}
                    """;
                    
      Gson gson = new Gson();      
      SpellController s = gson.fromJson(data, SpellController.class);    
   }   
            
     

}//end of program

