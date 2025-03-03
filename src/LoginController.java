import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import  javafx.stage.Stage;

public class LoginController {

    @FXML
    TextField emailTxt; 

    @FXML
    TextField passwTxt; 

    @FXML
    Button adminBtn; 

    @FXML
    Button loginnnBtn; 

    @FXML
    Button signupBtn; 
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchPane(ActionEvent event)   throws IOException{
        if(event.getSource() == adminBtn){
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("AdLogin.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else if (event.getSource() == signupBtn){
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("Signup.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void loginnnBtnHandler(ActionEvent event)  throws IOException{

        String email = emailTxt.getText();
        String passw = passwTxt.getText();

        if(DatabaseHandler.validateLogin(email, passw)){
            System.out.println("Login successful!");

            FXMLLoader loader  = new FXMLLoader(getClass().getResource("Browsing.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Cannot login... Please try again!");
            emailTxt.setText("");
            passwTxt.setText("");
        }
    }



}
