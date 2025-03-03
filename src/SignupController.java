import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class SignupController implements Initializable {

    @FXML
    private TextField fNameTxt;

    @FXML
    private TextField lNameTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField passwTxt;

    @FXML
    private Button aSignupBtn; 

    @FXML
    private Button aLoginBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize (URL url, ResourceBundle rb){

    }

    @FXML
    private boolean createUser(ActionEvent event) {

        String email = emailTxt.getText();
        String firstName = fNameTxt.getText();
        String lastName = lNameTxt.getText();
        String phoneNumber = phoneTxt.getText();
        String password = passwTxt.getText();

        email = email.trim();
        firstName = firstName.trim();
        lastName = lastName.trim();
        phoneNumber = phoneNumber.trim();
        password = password.trim();

        if(email.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no username provided");
            return false;
        }
        if(firstName.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no first name provided");
            return false;
        }
        if(lastName.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no lastname provided");
            return false;
        }
        if(phoneNumber.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no phone number provided");
            return false;
        }
        if(password.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no password provided");
            return false;
        }

        User user = new User("", email, firstName, lastName, phoneNumber, password);

        if(DatabaseHandler.addUser(user))
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no successful");
            emailTxt.setText("");
            passwTxt.setText("");
            fNameTxt.setText("");
            lNameTxt.setText("");
            phoneTxt.setText("");
            return true;
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("unsuccessful");
            emailTxt.setText("");
            passwTxt.setText("");
            fNameTxt.setText("");
            lNameTxt.setText("");
            phoneTxt.setText("");
            return false;
        }
    }

    public void switchPane(ActionEvent event)   throws IOException{
        if (event.getSource() == aLoginBtn){
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("Loginnn.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }



}