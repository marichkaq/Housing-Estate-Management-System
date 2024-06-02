import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Person {
   protected String name;
   protected String surname;
   protected int id;
   protected String address;
   protected ArrayList<Apartment> currentApartments = new ArrayList<Apartment>();
    protected ArrayList<Apartment> historyApartments = new ArrayList<Apartment>();
   protected ArrayList<ParkingPlace> currentParkingPlaces = new ArrayList<ParkingPlace>();
    protected ArrayList<ParkingPlace> historyParkingPlaces = new ArrayList<ParkingPlace>();
   protected ArrayList<TenantLetter> tenantLetters = new ArrayList<TenantLetter>();
   protected ArrayList<Item> itemsAndVehicles = new ArrayList<>();



    public Person(String name, String surname, int id, String address){
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.address = address;

    }


    public void setItemsAndVehicles(ArrayList<Item> itemsAndVehicles) {
        this.itemsAndVehicles = itemsAndVehicles;
    }

    protected void rentParkingPlace(ParkingPlace place, Date startDate, Date endDate) throws NotBelongException {
        if(place.tenant == null) {
            if (currentParkingPlaces.size() < 5) {
                currentParkingPlaces.add(place);
                historyParkingPlaces.add(place);
                place.rentStart = startDate;
                place.rentEnd = endDate;
                place.tenant = this;
                System.out.println(this.name + " " + this.surname + " rented parking place " + place.id);
                Thread timePassingThread = new TimePassingThread(startDate);
                timePassingThread.start();
                Thread checkRentalIssuesThread = new CheckRentalIssuesThread(place.estate, startDate);
                checkRentalIssuesThread.start();
            } else{
                System.out.println(name + " " + surname + " can not rent an parking place, he/she already rents 5.");
            }
        }else{
            throw new NotBelongException(this);
        }
    }


    protected void cancelParkingRent(ParkingPlace place) throws NotBelongException {
        if(this.currentParkingPlaces.contains(place)) {
            place.utilizeItems();
            Iterator<TenantLetter> iterator = tenantLetters.iterator();
            while(iterator.hasNext()) {
                TenantLetter item = iterator.next();
                if((item.getParkingPlace() == place && item.getApartment() == null)) {
                    iterator.remove();
                    System.out.println("Tenant letter for " + this.name + " " + this.surname + " for parking space " + place.id + " was removed");
                }
            }
        } else{
            throw new NotBelongException(this);
        }
    }

    protected void takeInItem(ParkingPlace parkingPlace, Item item) throws TooManyThingsException, NotBelongException {
        if(parkingPlace.tenant == this ){
            if(parkingPlace.getVolumeLeft() >= item.volume) {
                parkingPlace.items.add(item);
                System.out.println("Item " + item.name + " was placed into parking place " + parkingPlace.id);
            } else throw new TooManyThingsException();
        } else throw new NotBelongException(this);
    }

    protected void takeOutItem(ParkingPlace parkingPlace, Item item) throws NotBelongException {
        if(parkingPlace.tenant == this){
            if(!parkingPlace.items.isEmpty()){
                parkingPlace.items.remove(item);
                System.out.println("Item " + item.name + " was taken out of parking place " + parkingPlace.id);
            } else System.out.println("Parking space is already empty.");
        } else throw new NotBelongException(this);
    }

    protected void createAndSaveLetter(Apartment apartment, ParkingPlace place){
        TenantLetter letter = new TenantLetter(this, apartment, place);
        tenantLetters.add(letter);
        System.out.println("Tenant letter for " + this.name + " " + this.surname + " was created");
    }


    protected void renewParkingRent(ParkingPlace place, Date date){
        place.rentEnd = date;
        System.out.println(name + " " + surname + " has renewed the rent of parking place " + place.id);
        removeParkingLetter(place);
    }

    protected void removeParkingLetter(ParkingPlace place){
        tenantLetters.removeIf(letter -> {
            ParkingPlace letterParking = letter.getParkingPlace();
            if (letterParking != null && letterParking.equals(place)) {
                System.out.println("Tenant letter for " + this.name + " " + this.surname + " for parking space " + place.id + " was removed");
                return true;
            }
            return false;
        });
    }

    protected void ifNotParkingPlaceRenewed(ParkingPlace place){
        System.out.println("30 days have passed after expiring of rent.");
        place.utilizeItems();
    }
}
