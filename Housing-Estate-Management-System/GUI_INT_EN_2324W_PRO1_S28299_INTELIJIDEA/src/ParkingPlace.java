import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ParkingPlace {
    protected static int counter = 1;
    public final int id;
    protected final double volume;
    protected Person tenant;
    protected ArrayList<Item> items = new ArrayList<>();
    protected double volumeLeft;
    protected Date rentStart;
    protected Date rentEnd;
    protected Date expirationDate;
    protected HousingEstate estate;

    public ParkingPlace(double length, double width, double height, HousingEstate estate) {
        this.id = counter++;
        this.volume = length * width * height;
        this.volumeLeft = volume;
        this.estate = estate;
        setExpirationDate();
    }

    public void setExpirationDate() {
        if(rentEnd != null) {
            Calendar rentEndCalendar = Calendar.getInstance();
            rentEndCalendar.setTime(rentEnd);

            rentEndCalendar.add(Calendar.DAY_OF_MONTH, 30);
            this.expirationDate = rentEndCalendar.getTime();
        }
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    protected double getVolumeLeft(){
        for(Item item : items){
            volumeLeft = volume - item.volume;
        }
        return volumeLeft;
    }
    protected void utilizeItems(){
        items.clear();
        tenant.currentParkingPlaces.remove(this);
        rentStart = null;
        rentEnd = null;
        tenant = null;
        System.out.println("Rent of parking place was canceled and items were utilized");
    }
}
