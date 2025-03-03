import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import  javafx.stage.Stage;

public class AdLogController {

    @FXML
    Label unLbl; 

    @FXML
    Label pwLbl; 

    @FXML
    TextField unTxt; 

    @FXML
    TextField pwTxt; 

    @FXML
    Button loginBtn; 
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void loginBtnHandler(ActionEvent event)  throws IOException{

        String uname = unTxt.getText();
        String pword = pwTxt.getText();

        if(DatabaseHandler.validateAdLog(uname, pword)){
            System.out.println("Login successful!");

            FXMLLoader loader  = new FXMLLoader(getClass().getResource("AdminView.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Cannot login... Please try again!");
            unTxt.setText("");
            pwTxt.setText("");
        }
    }
}