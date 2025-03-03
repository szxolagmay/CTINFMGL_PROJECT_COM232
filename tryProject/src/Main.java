import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Make this your main page
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        // Sets title of main page
        primaryStage.setTitle("Welcome to Agoda!");

        // Sets window size
        primaryStage.setScene(new Scene(root));

        // Displays main page
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}