import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class User {
    private final SimpleStringProperty userId;
    private final SimpleStringProperty email;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty password;

    public User (String userId, String email, String firstName, String lastName, String phoneNumber, String pword){
        this.userId = new SimpleStringProperty(userId);
        this.email = new SimpleStringProperty(email);
        this.firstName = new SimpleStringProperty(firstName); 
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber); 
        this.password = new SimpleStringProperty(pword);
}
    public String getUserId (){
        return userId.get();
    }

    public String getEmail (){
        return email.get();
    }
   
    public String getFirstName (){
        return firstName.get();
    }

    public String getLastName (){
        return lastName.get();
    }

    public String getPhoneNumber (){
        return phoneNumber.get();
    }

    public String getPassword (){
        return password.get();
    }
}
