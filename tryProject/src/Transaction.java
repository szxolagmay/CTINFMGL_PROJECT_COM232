import javafx.beans.property.SimpleStringProperty;

public class Transaction {
    private final SimpleStringProperty resNum;
    private final SimpleStringProperty hotelCode;
    private final SimpleStringProperty email;
    private final SimpleStringProperty checkIn; 
    private final SimpleStringProperty checkOut;

    public Transaction (String resNum, String hotelCode, String checkIn, String checkOut, String email){
        this.resNum = new SimpleStringProperty(resNum);
        this.hotelCode = new SimpleStringProperty(hotelCode); 
        this.email = new SimpleStringProperty(email); 
        this.checkIn = new SimpleStringProperty(checkIn);
        this.checkOut = new SimpleStringProperty(checkOut);
}
    public String getResNum (){
        return resNum.get();
    }

    public String getHotelCode (){
        return hotelCode.get();
    }

    public String getCheckIn (){
        return checkIn.get(); 
    }

    public String getCheckOut (){
        return checkOut.get();
    }

    public String getEmail (){
        return email.get();
    }
}
