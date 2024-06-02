import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HousingEstate {
    protected int id;
    protected static int counter = 1;
    protected String name;
    String street;
    protected ArrayList<Apartment> apartments;
    protected ArrayList<ParkingPlace> parkingPlaces;
    protected ArrayList<Person> tenants;

    public HousingEstate(String name, String street,  ArrayList<Apartment> apartments,
                         ArrayList<ParkingPlace> parkingPlaces, ArrayList<Person> tenants) {
        this.id = counter++;
        this.name = name;
        this.street = street;
        this.apartments = apartments;
        this.parkingPlaces = parkingPlaces;
        this.tenants = tenants;
    }

    protected void checkRentalIssues(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        for (Apartment apartment : apartments) {
            if (apartment.rentEnd != null && apartment.rentEnd.compareTo(currentDate) == 0) {
                System.out.println("Rent of apartment " + apartment.id + " has expired.");
                apartment.mainTenant.createAndSaveLetter(apartment, null);
                apartment.rentEnd = null;
            }
                if (apartment.getExpirationDate() != null && apartment.getExpirationDate().compareTo(currentDate) == 0) {
                    if (apartment.mainTenant != null) {
                        MainTenant tenant = (MainTenant) apartment.mainTenant;
                        tenant.ifNotApartmentRenewed(apartment);
                    }
            }
        }
        for (ParkingPlace place : parkingPlaces) {
            if (place.rentEnd != null && place.rentEnd.compareTo(currentDate) == 0) {
                System.out.println("Rent of parking place " + place.id + " has expired.");
                place.tenant.createAndSaveLetter(null, place);
                place.rentEnd = null;
            }

                if (place.getExpirationDate() != null && place.getExpirationDate().compareTo(currentDate) == 0) {
                    if (place.tenant != null) {
                        place.tenant.ifNotParkingPlaceRenewed(place);
                    }
                }

        }
    }
}
