public class ProblematicTenantException extends Exception{
    protected Person person;

    public ProblematicTenantException(Person person) {
        super("Person " + person.name + " " + person.surname + " had already renting rooms: " + person.historyApartments
                + " " + person.historyParkingPlaces);
        this.person = person;
    }
}
