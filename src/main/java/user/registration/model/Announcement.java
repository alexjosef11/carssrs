package user.registration.model;

import java.io.File;
import java.util.List;

public class Announcement {

    private String make;
    private String model;
    private String price;
    private String year;
    private String kilometers;
    private String power;
    private String VehicleType;
    private String FuelType;
    private boolean SellBox;
    private boolean RentBox;
    private boolean SwapBox;
    private String path;

    public Announcement(String make, String model, String price, String year, String kilometers,
                        String power, String VehicleType,String FuelType, boolean SellBox, boolean RentBox, boolean SwapBox, String path) {
        this.make = make;
        this.model = model;
        this.price = price;
        this.year = year;
        this.kilometers = kilometers;
        this.power = power;
        this.VehicleType = VehicleType;
        this.FuelType = FuelType;
        this.SellBox = SellBox;
        this.RentBox = RentBox;
        this.SwapBox = SwapBox;
        this.path = path;
    }
    public Announcement(){}
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice(){ return price; }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getKilometers() {
        return kilometers;
    }
    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public String getPower() {
        return power;
    }
    public void setPower(String power) { this.power = power; }

    public String getVehicleType() {return VehicleType;}
    public void setVehicleType(String VehicleType) { this.VehicleType = VehicleType; }

    public String getFuelType() {return FuelType;}
    public void setFuelType(String FuelType) { this.FuelType = FuelType; }

    public boolean getSellBox(){return SellBox;}
    public void setSellBox(boolean SellBox){this.SellBox=SwapBox;}

    public boolean getRentBox(){return RentBox;}
    public void setRentBox(boolean RentBox){this.RentBox=RentBox;}

    public boolean getSwapBox(){return SwapBox;}
    public void setSwapBox(boolean SwapBox){this.SwapBox=SwapBox;}

    public String getFile(){return path;}
    public void setFile( String path){this.path=path;}




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Announcement Announcement = (Announcement) o;

        if (!make.equals(Announcement.make)) return false;
        if (!model.equals(Announcement.model)) return false;
        return year.equals(Announcement.year);
    }

    @Override
    public int hashCode() {
        int result = make.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + year.hashCode();
        return result;
    }
    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
