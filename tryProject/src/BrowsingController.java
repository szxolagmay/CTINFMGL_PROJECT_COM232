import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BrowsingController {
    ObservableList<Transaction> transList = FXCollections.observableArrayList();

    ObservableList<Property> hotelsList = FXCollections.observableArrayList(); 

    @FXML
    private Button bookBelmontBtn;

    @FXML
    private Button bookHeritageBtn;

    @FXML
    private Button bookMarcoPoloBtn;

    @FXML
    private Button bookOkadaBtn;

    @FXML
    private Button bookPicoDLBtn;

    @FXML
    private Button bookSheratonBtn;

    @FXML
    private Button userLogoutBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void logoutUser(ActionEvent event)    throws IOException{
        if(event.getSource() == userLogoutBtn){
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("Login.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void bookBelmont(ActionEvent event)    throws IOException{
        if(event.getSource() == bookBelmontBtn){
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("FormBelmont.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void bookMarcoPolo(ActionEvent event)    throws IOException{
        if(event.getSource() == bookMarcoPoloBtn){
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("FormMarcoPolo.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void bookHeritage(ActionEvent event)    throws IOException{
        if(event.getSource() == bookHeritageBtn){
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("FormHeritage.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void booPicoDL(ActionEvent event)    throws IOException{
        if(event.getSource() == bookPicoDLBtn){
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("FormPico.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void bookSheraton(ActionEvent event)    throws IOException{
        if(event.getSource() == bookSheratonBtn){
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("FormSheraton.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void bookOkada(ActionEvent event)    throws IOException{
        if(event.getSource() == bookOkadaBtn){
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("FormOkada.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
