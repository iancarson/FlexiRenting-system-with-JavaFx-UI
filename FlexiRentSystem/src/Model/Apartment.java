package Model;

import javafx.beans.property.*;

public class Apartment {
    private final StringProperty ApartmentName;
    private final StringProperty ApartmentLocation;
    private final IntegerProperty RoomNumbers;
    private final DoubleProperty Cost;
    private final StringProperty Status;
    private final StringProperty Maintainance;
    public Apartment(String Name,String Location,int RoomNumbers,Double Price,String status,String Maintain)
    {
        this.ApartmentName=new SimpleStringProperty(Name);
        this.ApartmentLocation=new SimpleStringProperty(Location);
        this.RoomNumbers=new SimpleIntegerProperty(RoomNumbers);
        this.Cost=new SimpleDoubleProperty(Price);
        this.Status=new SimpleStringProperty(status);
        this.Maintainance=new SimpleStringProperty(Maintain);
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

    public String getApartmentName() {
        return ApartmentName.get();
    }

    public StringProperty apartmentNameProperty() {
        return ApartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.ApartmentName.set(apartmentName);
    }

    public String getApartmentLocation() {
        return ApartmentLocation.get();
    }

    public StringProperty apartmentLocationProperty() {
        return ApartmentLocation;
    }

    public void setApartmentLocation(String apartmentLocation) {
        this.ApartmentLocation.set(apartmentLocation);
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
