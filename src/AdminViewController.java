import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminViewController implements Initializable {

        ObservableList<User> usersList = FXCollections.observableArrayList(); 
    
        ObservableList<Transaction> transList = FXCollections.observableArrayList(); 
    
        ObservableList<Property> proList = FXCollections.observableArrayList(); 
    
        @FXML
        private AnchorPane usersPane;
    
        @FXML
        private AnchorPane transPane;
    
        @FXML
        private AnchorPane hotelsPane;

        @FXML
        private AnchorPane proFormsPane;
        
        // USER TABLE
        @FXML
        private TableView<User> usersTable; 
            
        @FXML
        private TableColumn<User, String> uIdCol;
            
        @FXML
        private TableColumn<User, String> emailCol;
            
        @FXML
        private TableColumn<User, String> fNameCol;
            
        @FXML
        private TableColumn<User, String> lNameCol;
            
        @FXML
        private TableColumn<User, String> noCol;
            
        @FXML
        private TableColumn<User, String> pwCol;
        
        // TRANSACTION TABLE 
        @FXML
        private TableView<Transaction> transTable; 
            
        @FXML
        private TableColumn<Transaction, String> resNumCol;
            
        @FXML
        private TableColumn<Transaction, String> emailtransCol;
            
        @FXML
        private TableColumn<Transaction, String> hotelCol;
            
        @FXML
        private TableColumn<Transaction, String> inDateCol;
            
        @FXML
        private TableColumn<Transaction, String> outDateCol;
        
        // PROPERTIES TABLE
        @FXML
        private TableView<Property> hotelsTable; 
            
        @FXML
        private TableColumn<Property, String> codeCol;
            
        @FXML
        private TableColumn<Property, String> hnameCol;
            
        @FXML
        private TableColumn<Property, String> ratingCol;
            
        @FXML
        private TableColumn<Property, String> addressCol;
            
        @FXML
        private TableColumn<Property, String> typeCol;
        
        // BUTTONS 
        @FXML
        private Button usersBtn; 
            
        @FXML
        private Button transBtn;
            
        @FXML
        private Button hotelsBtn;
            
        @FXML
        private Button addProBtn;
            
        @FXML
        private Button delProBtn;
            
        @FXML
        private Button delUserBtn;
            
        @FXML
        private Button udUserBtn;

        @FXML
        private Button updateProBtn;

        @FXML
        private Button logoutBtn;
            
        // Update User
        @FXML
        private TextField emailTxt;

        @FXML
        private TextField firstnameTxt;

        @FXML
        private TextField lastnameTxt;

        @FXML
        private TextField passTxt; 

        @FXML
        private TextField phoneTxt;

        @FXML
        private TextArea userIdTxt;

        // ADD PROPERTY 
        @FXML
        private TextField protypeAddTxt;

        @FXML
        private TextField protypeCodeTxt;

        @FXML
        private ComboBox protypeComb;

        @FXML
        private TextField protypeNameTxt;

        @FXML
        private TextField protypeRatingTxt;
            
        private Stage stage;
        private Scene scene;
        private Parent root;
        Integer index;
            
        @Override
        public void initialize (URL url, ResourceBundle rb){
            initializeCol();
            loadUsers();
            loadTrans();
            loadHotels();initializeCol();
            loadUsers();
            loadTrans();
            loadHotels();

            // Update Property
            ObservableList<String> proType = FXCollections.observableArrayList("Hotel", "Guest House", "Resort", "Hostel", "Serviced Apartment");
            protypeComb.setItems(proType);
        }
            
        private void initializeCol(){
            //users table 
            uIdCol.setCellValueFactory(new PropertyValueFactory<>("UserId"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
            fNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            lNameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
            noCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
            pwCol.setCellValueFactory(new PropertyValueFactory<>("Password"));
        
            //trans table 
            resNumCol.setCellValueFactory(new PropertyValueFactory<>("ResNum"));
            emailtransCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
            hotelCol.setCellValueFactory(new PropertyValueFactory<>("HotelCode"));
            inDateCol.setCellValueFactory(new PropertyValueFactory<>("CheckIn"));
            outDateCol.setCellValueFactory(new PropertyValueFactory<>("CheckOut"));
            
            //properties table 
            codeCol.setCellValueFactory(new PropertyValueFactory<>("protypeCode"));
            hnameCol.setCellValueFactory(new PropertyValueFactory<>("protypeName"));
            ratingCol.setCellValueFactory(new PropertyValueFactory<>("protypeRating"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("protypeAdd"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("proType"));
        }
            
        public void logout(ActionEvent event)    throws IOException{
            if(event.getSource() == logoutBtn){
                FXMLLoader loader  = new FXMLLoader(getClass().getResource("Login.fxml"));
                root = loader.load();
    
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }

    @FXML
    public void loadUsers(){
                    
    usersList.clear();
    ResultSet result = DatabaseHandler.getUsers();
        
    try {
        while (result.next()) {
            String userId = result.getString("custid");
            String email = result.getString("email");
            String firstname = result.getString("firstName");
            String lastname = result.getString("lastName");
            String phonenumber = result.getString("phoneNumber");
            String password = result.getString("password");
            usersList.add(new User(userId, email, firstname, lastname, phonenumber, password));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    usersTable.setItems(usersList);
    }

    @FXML
    private void loadTrans(){
        
        transList.clear();
        ResultSet result = DatabaseHandler.getTrans();

        try {
            while (result.next()) {
                String resNum = result.getString("resNum");
                String proCode = result.getString("procode");
                String checkIn = result.getString("checkin");
                String checkOut = result.getString("checkout");
                String email = result.getString("email");
                transList.add(new Transaction(resNum, proCode, checkIn, checkOut, email)); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        transTable.setItems(transList);
    }

    @FXML
    private void loadHotels(){
        
        proList.clear();
        ResultSet result = DatabaseHandler.getHotels();

        try {
            while (result.next()) {
                String proCode = result.getString("procode");
                String proName = result.getString("proname");
                String rating = result.getString("rating");
                String address = result.getString("address");
                String type = result.getString("protype");
                proList.add(new Property(proCode, proName, rating, address, type)); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        hotelsTable.setItems(proList);
    }

    public void switchForm(ActionEvent event){
        if(event.getSource() == usersBtn){
            usersPane.setVisible(true);
            transPane.setVisible(false);
            hotelsPane.setVisible(false);

        }else if (event.getSource() == transBtn){
            usersPane.setVisible(false);
            transPane.setVisible(true);
            hotelsPane.setVisible(false);

        }else if (event.getSource() == hotelsBtn){
            usersPane.setVisible(false);
            transPane.setVisible(false);
            hotelsPane.setVisible(true);

        }else if (event.getSource() == updateProBtn){
            usersPane.setVisible(false);
            transPane.setVisible(false);
            hotelsPane.setVisible(true);
    }
}


    // PROPERTY 
    public void getProperty(MouseEvent event) {
        index = hotelsTable.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        protypeCodeTxt.setText(codeCol.getCellData(index).toString());
        protypeNameTxt.setText(hnameCol.getCellData(index).toString());
        protypeRatingTxt.setText(ratingCol.getCellData(index).toString());
        protypeAddTxt.setText(addressCol.getCellData(index).toString());
    }

    public boolean addNewPro(ActionEvent event){
        String protypeCode = protypeCodeTxt.getText();
        String protypeName = protypeNameTxt.getText();
        String protypeRating = protypeRatingTxt.getText();
        String protypeAdd = protypeAddTxt.getText();
        String proType = protypeComb.getSelectionModel().getSelectedItem().toString();

        protypeCode = protypeCode.trim();
        protypeName = protypeName.trim();
        protypeRating = protypeRating.trim();
        protypeAdd = protypeAdd.trim();
        proType = proType.trim();

        if(protypeCode.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no property code provided");
            return false;
        }
        if(protypeName.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no property name provided");
            return false;
        }
        if(protypeRating.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no rating provided");
            return false;
        }
        if(protypeAdd.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no address provided");
            return false;
        }

        Property property = new Property(protypeCode, protypeName, protypeRating, protypeAdd, proType);

        if(DatabaseHandler.addProperty(property))
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no successful");
            loadHotels();
            protypeNameTxt.setText("");
            protypeCodeTxt.setText("");
            protypeAddTxt.setText("");
            protypeRatingTxt.setText("");
            return true;
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("unsuccessful");
            protypeNameTxt.setText("");
            protypeCodeTxt.setText("");
            protypeAddTxt.setText("");
            protypeRatingTxt.setText("");
            return false;
        }
    }

    @FXML
    public boolean deletePro(ActionEvent event) {


        Property property = hotelsTable.getSelectionModel().getSelectedItem();
        System.out.println(property);


        if(DatabaseHandler.deletePro(property))
        {
            System.out.println("Property has been deleted!");
            loadHotels();
            return true;
        }
        else
        {
            System.out.println("Property has not been deleted!");
            return false;
        }
    }

    @FXML
    public boolean updatePro(ActionEvent event) {

        String procode = protypeCodeTxt.getText();
        String proname = protypeNameTxt.getText();
        String rating = protypeRatingTxt.getText();
        String address = protypeAddTxt.getText();
        String protype = protypeComb.getSelectionModel().getSelectedItem().toString();

        procode = procode.trim();
        proname = proname.trim();
        rating = rating.trim();
        address = address.trim();
        protype = protype.trim();

        if(procode.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no property code provided");
            return false;
        }
        if(proname.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no property name provided");
            return false;
        }
        if(rating.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no rating provided");
            return false;
        }
        if(address.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no address provided");
            return false;
        }

        Property property = new Property(procode, proname, rating, address, protype);

        if(DatabaseHandler.updatePro(property))
        {
            System.out.println("Successful");
            loadHotels();
            protypeCodeTxt.setText("");
            protypeNameTxt.setText("");
            protypeRatingTxt.setText("");
            protypeAddTxt.setText("");
            return true;
        }
        else
        {
            System.out.println("unsuccessful");
            return false;
        }
    }


    // USERS
    @FXML
    public void getUser(MouseEvent event) {
        index = usersTable.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        userIdTxt.setText(uIdCol.getCellData(index).toString());
        emailTxt.setText(emailCol.getCellData(index).toString());
        firstnameTxt.setText(fNameCol.getCellData(index).toString());
        lastnameTxt.setText(lNameCol.getCellData(index).toString());
        phoneTxt.setText(noCol.getCellData(index).toString());
        passTxt.setText(pwCol.getCellData(index).toString());
    }

    @FXML
    public boolean deleteUser(ActionEvent event) {

        User user = usersTable.getSelectionModel().getSelectedItem();
        System.out.println(user);

        if(DatabaseHandler.deleteUser(user))
        {
            System.out.println("User has been deleted!");
            loadUsers();
            return true;
        }
        else
        {
            System.out.println("User has not been deleted!");
            return false;
        }
    }

    @FXML
    public boolean updateUser(ActionEvent event) {

        String userid = userIdTxt.getText();
        String email = emailTxt.getText();
        String firstName = firstnameTxt.getText();
        String lastName = lastnameTxt.getText();
        String phoneNum = phoneTxt.getText();
        String password = passTxt.getText();

        userid = userid.trim();
        email = email.trim();
        firstName = firstName.trim();
        lastName = lastName.trim();
        phoneNum = phoneNum.trim();
        password = password.trim();

        if(email.length() == 0)
        {
            System.out.println("No username!");
            return false;
        }
        if(firstName.length() == 0)
        {
            System.out.println("No username!");
            return false;
        }
        if(lastName.length() == 0)
        {
            System.out.println("No username!");
            return false;
        }
        if(phoneNum.length() == 0)
        {
            System.out.println("No username!");
            return false;
        }
        if(password.length() == 0)
        {
            System.out.println("No password!");
            return false;
        }

        User user = new User(userid, email, firstName, lastName, phoneNum, password);

        if(DatabaseHandler.udUser(user))
        {
            System.out.println("Successful");
            loadUsers();
            userIdTxt.setText("");
            emailTxt.setText("");
            firstnameTxt.setText("");
            lastnameTxt.setText("");
            phoneTxt.setText("");
            passTxt.setText("");
            return true;
        }
        else
        {
            System.out.println("unsuccessful");
            userIdTxt.setText("");
            emailTxt.setText("");
            firstnameTxt.setText("");
            lastnameTxt.setText("");
            phoneTxt.setText("");
            passTxt.setText("");
            return false;
        }
    }

        
    // TRANSACTIONS
    @FXML
    public boolean deleteTrans(ActionEvent event) {

        Transaction transaction = transTable.getSelectionModel().getSelectedItem();
        System.out.println(transaction);

        if(DatabaseHandler.deleteTrans(transaction))
        {
            System.out.println("Property has been deleted!");
            loadTrans();
            return true;
        }
        else
        {
            System.out.println("Property has not been deleted!");
            return false;
        }
    }
    

    
    
}    

        




 