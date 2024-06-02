public class TenantLetter {
    Person person;
    Apartment apartment;
    ParkingPlace parkingPlace;
    String message;

    public TenantLetter(Person person, Apartment apartment, ParkingPlace place) {
        this.person = person;
        this.apartment = apartment;
        this.parkingPlace = place;
        this.message = "Your rent has expired, renew it within 30 days or cancel.";
    }

    public Apartment getApartment() {
        return apartment;
    }

    public ParkingPlace getParkingPlace() {
        return parkingPlace;
    }
}
