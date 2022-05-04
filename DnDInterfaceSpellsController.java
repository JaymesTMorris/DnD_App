import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DnDInterfaceSpellsController {

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

}
