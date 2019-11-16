/*

 */
package playfaircipher;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author SS940
 */
public class FXMLDocumentController implements Initializable {

    /**
     * Initializes the controller class.
     */
  
    PlayFair playFairObject;
    @FXML
    private TextField keyBased;
    @FXML
    private TextField soucreText;
    @FXML
    private TextField targetText;
    @FXML
    private Button encrptButton;
    @FXML
    private Button decrptButton;
    @FXML
    private ComboBox PlayfairCipher;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     PlayfairCipher.getItems().addAll("PlayFair");
        encrptButton.setOnAction((ActionEvent event) -> {
            String cryptoTech;
            cryptoTech = (String) PlayfairCipher.getValue();
              
              if(cryptoTech=="PlayFair")
              {
                  String key = keyBased.getText();
                  System.out.println(key);
                  String matrixBased = playFairObject.keyFormation(key);
                  System.out.println("===============================================");
                  //System.out.print(playFairObject.alphabetInitialized(matrixBased));
                  char[][] finalMatrix = playFairObject.alphabetInitialized(matrixBased);
                 playFairObject.printAlphabetMatrix(finalMatrix);
                 System.out.println("===============================================");
                 String sourceTexts=playFairObject.plainTextFormation(soucreText.getText());
                  System.out.println(sourceTexts);
                 String finalEncyptedText = playFairObject.encyptedAlogirthm(sourceTexts, finalMatrix);
                 targetText.setText(finalEncyptedText);
                 
                 
                 System.out.println("Done!");
              }
        });
        decrptButton.setOnAction((ActionEvent event) -> {
            String cryptoTech;
            cryptoTech = (String) PlayfairCipher.getValue();
            
              if(cryptoTech=="PlayFair")
              {
                  String key = keyBased.getText();
                  System.out.println(key);
                  String matrixBased = playFairObject.keyFormation(key);
                  System.out.println("===============================================");
                  //System.out.print(playFairObject.alphabetInitialized(matrixBased));
                  char[][] finalMatrix = playFairObject.alphabetInitialized(matrixBased);
                 playFairObject.printAlphabetMatrix(finalMatrix);
                 System.out.println("===============================================");
                 String sourceTexts=playFairObject.plainTextFormation(soucreText.getText());
                  System.out.println(sourceTexts);
                  targetText.setText(playFairObject.decyptedAlogirthm(soucreText.getText(), finalMatrix));
                 
                 
                 System.out.println("Done!");
              }
        });
     
    }    
    
}
