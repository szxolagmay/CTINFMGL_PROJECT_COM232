import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookingController implements Initializable {

    ObservableList<Transaction> transList = FXCollections.observableArrayList();
    ObservableList<Transaction> myBookingList = FXCollections.observableArrayList();

    @FXML
    private Button backBtn; 
    
    @FXML
    private Button confirmBtn; 

    @FXML
    TextField emailTxt; 

    @FXML
    TextField custidsampleTxt;

    @FXML
    private Label procodesampleTxt;

    @FXML
    private ComboBox roomComb;

    @FXML
    private DatePicker inDate;

    @FXML
    private DatePicker outDate;

    @FXML
    TextArea totalArea;
    
    // TRANSACTION TABLE 
    @FXML
    private TableView<Transaction> mytransTable; 
        
    @FXML
    private TableColumn<Transaction, String> resNumCol;
        
    @FXML
    private TableColumn<Transaction, String> hotelCol;
        
    @FXML
    private TableColumn<Transaction, String> inDateCol;
        
    @FXML
    private TableColumn<Transaction, String> outDateCol;

    @FXML
    private TableColumn<Transaction, String> emailCol;

    private Stage stage;
    private Scene scene;
    private Parent root;
    Integer index;
    

    @Override
    public void initialize (URL url, ResourceBundle rb){
        ObservableList<String> room = FXCollections.observableArrayList("Single Room", "Twin Beds", "Junior Suite", "Executive", "Presidential");
        roomComb.setItems(room);

        initializeCol();
        loadMyTrans();
    }


    private void initializeCol(){

        //trans table 
        resNumCol.setCellValueFactory(new PropertyValueFactory<>("ResNum"));
        hotelCol.setCellValueFactory(new PropertyValueFactory<>("HotelCode"));
        inDateCol.setCellValueFactory(new PropertyValueFactory<>("CheckIn"));
        outDateCol.setCellValueFactory(new PropertyValueFactory<>("CheckOut"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));

    }


    public void back(ActionEvent event)    throws IOException{
        if(event.getSource() == backBtn){
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("Browsing.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    
    @FXML
    private void loadMyTrans(){
        
        myBookingList.clear();
        ResultSet result = DatabaseHandler.getTrans();

        try {
            while (result.next()) {
                String resNum = result.getString("resNum");
                String proCode = result.getString("procode");
                String checkIn = result.getString("checkin");
                String checkOut = result.getString("checkout");
                String email = result.getString("email");
                myBookingList.add(new Transaction(resNum, proCode, checkIn, checkOut, email)); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mytransTable.setItems(myBookingList);
    }


    // Create transaction
    @FXML
    private boolean addTransaction(ActionEvent event) {
        
        String email = emailTxt.getText();
        String procode = procodesampleTxt.getText();
        String checkin = inDate.getEditor().getText();
        String checkout = outDate.getEditor().getText();
        //String room = roomComb.getSelectionModel().getSelectedItem().toString();
        inDate =  new DatePicker();
        inDate.setPromptText("Check In Date");
        outDate = new DatePicker();
        outDate.setPromptText("Check Out Date"); 

        email = email.trim();
        procode = procode.trim();
        //room = room.trim();

        if(email.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR); 
            alert.setContentText("no property code provided");
            return false;
        }

        Transaction transaction = new Transaction("", procode, checkin, checkout, email);

        if (DatabaseHandler.addTransaction(transaction)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("successful");
            loadMyTrans();
            emailTxt.setText("");
            inDate.getEditor().setText("");
            outDate.getEditor().setText("");
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("unsuccessful");
            emailTxt.setText("");
            inDate.getEditor().setText("");
            outDate.getEditor().setText("");
            return false;
        }

    }

    public void addValidTrans(ActionEvent event)  throws IOException{

        String email = emailTxt.getText();

        if(DatabaseHandler.validEmail(email)){
            addTransaction(event);
            
        } else {
            System.out.println("Please create an account first before booking!");
            emailTxt.setText("");
        }
    }


    // Cancel/Delete Transaction
    @FXML
    public boolean deleteTrans(ActionEvent event) {


        Transaction transaction = mytransTable.getSelectionModel().getSelectedItem();
        System.out.println(transaction);


        if(DatabaseHandler.deleteTrans(transaction))
        {
            System.out.println("Transaction has been deleted!");
            loadMyTrans();
            emailTxt.setText("");
            inDate.getEditor().setText("");
            outDate.getEditor().setText("");
            return true;
        }
        else
        {
            System.out.println("Transaction has not been deleted!");
            emailTxt.setText("");
            inDate.getEditor().setText("");
            outDate.getEditor().setText("");
            return false;
        }
    }


    // Update Transaction
    @FXML
    public boolean updateTrans(ActionEvent event) {
        String email = emailTxt.getText();
        String procode = procodesampleTxt.getText();
        String checkin = inDate.getEditor().getText();
        String checkout = outDate.getEditor().getText();
        //String room = roomComb.getSelectionModel().getSelectedItem().toString();
        inDate =  new DatePicker();
        inDate.setPromptText("Check In Date");
        outDate = new DatePicker();
        outDate.setPromptText("Check Out Date"); 

        email = email.trim();
        procode = procode.trim();
        //room = room.trim();

        if(email.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR); 
            alert.setContentText("no email provided");
            return false;
        }

        Transaction transaction = new Transaction("", procode, checkin, checkout, email);

        if(DatabaseHandler.updateTrans(transaction))
        {
            System.out.println("Successful");
            loadMyTrans();
            emailTxt.setText("");
            inDate.getEditor().setText("");
            outDate.getEditor().setText("");
            return true;
        }
        else
        {
            System.out.println("unsuccessful");
            emailTxt.setText("");
            inDate.getEditor().setText("");
            outDate.getEditor().setText("");
            return false;
        }
    }


    // Select Transaction from table
    @FXML
    public void selectTrans(MouseEvent event) {
        index = mytransTable.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        emailTxt.setText(emailCol.getCellData(index).toString());
        procodesampleTxt.setText(hotelCol.getCellData(index).toString());
        inDate.getEditor().setText(inDateCol.getCellData(index).toString());
        outDate.getEditor().setText(outDateCol.getCellData(index).toString());
    }

}