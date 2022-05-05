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

public class TestApiJsonParsing {

   public static void main(String[] args) 
   {
      String query = "Acid Arrow";
      
      HttpClient client = HttpClient.newHttpClient();
      
      try
      {
         HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://www.dnd5eapi.co/api/spells/" + query.replaceAll(" ","-").toLowerCase() ))
            .GET()
            .build();
         
         client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println);
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

}