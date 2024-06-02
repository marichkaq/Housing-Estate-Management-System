import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Apartment {
    protected static int counter = 1;
    public final int id;
    protected final double volume;
    protected ArrayList<Person> tenants = new ArrayList<Person>();
    protected Person mainTenant;
    protected Date rentStart;
    protected Date rentEnd;
    protected Date expirationDate;
    protected HousingEstate estate;


    public Apartment(double length, double width, double height, HousingEstate estate) {
        this.id = counter++;
        this.volume = length * width * height;
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

    public void setMainTenant(Person mainTenant) {
        this.mainTenant = mainTenant;
    }

    protected void evictPeople(){
        for(Person tenant : tenants){
            tenant.currentApartments.remove(this);
        }
        rentStart = null;
        rentEnd = null;
        tenants.clear();
        System.out.println("People are evicted from apartment " + id);
    }
}
