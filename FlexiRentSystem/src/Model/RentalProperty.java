package Model;

import javafx.beans.property.*;

public class RentalProperty
{
    private final StringProperty RentalName;
    private  final StringProperty RentalLocation;
    private final IntegerProperty RoomNumbers;
    private final DoubleProperty Cost;
    public RentalProperty(String Name,String Location,int RoomNumber,Double Price)
    {
        this.RentalName=new SimpleStringProperty(Name);
        this.RentalLocation=new SimpleStringProperty(Location);
        this.RoomNumbers=new SimpleIntegerProperty(RoomNumber);
        this.Cost=new SimpleDoubleProperty(Price);
    }

    public String getRentalName() {
        return RentalName.get();
    }

    public StringProperty rentalNameProperty() {
        return RentalName;
    }

    public void setRentalName(String rentalName) {
        this.RentalName.set(rentalName);
    }

    public String getRentalLocation() {
        return RentalLocation.get();
    }

    public StringProperty rentalLocationProperty() {
        return RentalLocation;
    }

    public void setRentalLocation(String rentalLocation) {
        this.RentalLocation.set(rentalLocation);
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
