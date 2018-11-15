package Model;

import javafx.beans.property.*;

public class Premiumsuite {
    private final StringProperty PremiumsuiteName;
    private final StringProperty PremiumsuiteLocation;
    private final IntegerProperty RoomNumbers;
    private final DoubleProperty Cost;
    private final StringProperty Status;
    private final StringProperty Maintainance;
    public Premiumsuite(String Name,String Location,int RoomNumbers,double Price,String status,String maintain)
    {
        this.PremiumsuiteName=new SimpleStringProperty(Name);
        this.PremiumsuiteLocation=new SimpleStringProperty(Location);
        this.RoomNumbers=new SimpleIntegerProperty(RoomNumbers);
        this.Cost=new SimpleDoubleProperty(Price);
        this.Status=new SimpleStringProperty(status);
        this.Maintainance=new SimpleStringProperty(maintain);
    }

    public String getStatus() {
        return Status.get();
    }

    public StringProperty statusProperty() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status.set(status);
    }

    public String getMaintainance() {
        return Maintainance.get();
    }

    public StringProperty maintainanceProperty() {
        return Maintainance;
    }

    public void setMaintainance(String maintainance) {
        this.Maintainance.set(maintainance);
    }

    public String getPremiumsuiteName() {
        return PremiumsuiteName.get();
    }

    public StringProperty premiumsuiteNameProperty() {
        return PremiumsuiteName;
    }

    public void setPremiumsuiteName(String premiumsuiteName) {
        this.PremiumsuiteName.set(premiumsuiteName);
    }

    public String getPremiumsuiteLocation() {
        return PremiumsuiteLocation.get();
    }

    public StringProperty premiumsuiteLocationProperty() {
        return PremiumsuiteLocation;
    }

    public void setPremiumsuiteLocation(String premiumsuiteLocation) {
        this.PremiumsuiteLocation.set(premiumsuiteLocation);
    }

    public int getRoomNumbers() {
        return RoomNumbers.get();
    }

    public IntegerProperty roomNumbersProperty() {
        return RoomNumbers;
    }

    public void setRoomNumbers(int roomNumbers) {
        this.RoomNumbers.set(roomNumbers);
    }

    public double getCost() {
        return Cost.get();
    }

    public DoubleProperty costProperty() {
        return Cost;
    }

    public void setCost(double cost) {
        this.Cost.set(cost);
    }
}
