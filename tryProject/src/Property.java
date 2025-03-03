import javafx.beans.property.SimpleStringProperty;

public class Property {
    private final SimpleStringProperty protypeName;
    private final SimpleStringProperty protypeCode;
    private final SimpleStringProperty protypeAdd;
    private final SimpleStringProperty protypeRating;
    private final SimpleStringProperty proType;

    public Property (String protypeCode, String protypeName, String protypeRating, String protypeAdd, String proType){
        this.protypeName = new SimpleStringProperty(protypeName);
        this.protypeCode = new SimpleStringProperty(protypeCode);
        this.protypeAdd = new SimpleStringProperty(protypeAdd); 
        this.protypeRating = new SimpleStringProperty(protypeRating);
        this.proType = new SimpleStringProperty(proType); 
}

    public String getProtypeName (){
        return protypeName.get();
    }

    public String getProtypeCode (){
        return protypeCode.get();
    }
   
    public String getProtypeAdd (){
        return protypeAdd.get();
    }

    public String getProtypeRating (){
        return protypeRating.get();
    }

    public String getProType (){
        return proType.get();
    }
}
