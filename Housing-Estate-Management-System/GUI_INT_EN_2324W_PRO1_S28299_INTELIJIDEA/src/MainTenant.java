import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class MainTenant extends Person{
    public MainTenant(String name, String surname, int id, String address) {
        super(name, surname, id, address);

    }

    protected void rentApartment(Apartment apartment, Date startDate, Date endDate) throws ProblematicTenantException {
        if(apartment.mainTenant == null){
        if(currentApartments.size() < 5) {
            if(!(tenantLetters.size() >= 3)) {
                currentApartments.add(apartment);
                historyApartments.add(apartment);
                apartment.rentStart = startDate;
                apartment.rentEnd = endDate;
                apartment.tenants.add(this);
                apartment.setMainTenant(this);
                System.out.println(this.name + " " + this.surname + " rented apartment " + apartment.id);
                Thread timePassingThread = new TimePassingThread(startDate);
                timePassingThread.start();
                Thread checkRentalIssuesThread = new CheckRentalIssuesThread(apartment.estate, startDate);
                checkRentalIssuesThread.start();
            } else{
                throw new ProblematicTenantException(this);
            }
        }  else{
            System.out.println(name + " " + surname + " can not rent an apartment, he/she already rents 5.");
        }
        }else{
            System.out.println("This apartment is already taken.");
        }
    }

    protected void cancelApartmentsRent(Apartment apartment) throws NotBelongException {
        if(apartment.mainTenant == this){
            apartment.evictPeople();
            System.out.println(this.name + " " + this.surname + " has cancelled the rent of apartment " + apartment.id);
            Iterator<TenantLetter> iterator = tenantLetters.iterator();
            while(iterator.hasNext()) {
                TenantLetter item = iterator.next();
                if((item.getApartment() == apartment && item.getParkingPlace() == null)) {
                    //removeApartmentLetter(apartment);
                    iterator.remove();
                    System.out.println("Tenant letter for " + this.name + " " + this.surname + " for apartment " + apartment.id + " was removed");
                }
            }
        } else{
            throw new NotBelongException(this);
        }
    }

      protected void checkInPerson(Apartment apartment, Person person) throws NotBelongException {
        if(apartment.mainTenant == this){
            person.currentApartments.add(apartment);
            person.historyApartments.add(apartment);
            apartment.tenants.add(person);
            System.out.println(person.name + " " + person.surname + " was checked in to apartment " + apartment.id);
        } else{
            throw new NotBelongException(this);
        }
    }

    protected void checkOutPerson(Apartment apartment, Person person) throws NotBelongException {
        if(apartment.mainTenant == this){
            if(apartment.tenants.contains(person)) {
                person.currentApartments.remove(apartment);
                apartment.tenants.remove(person);
                System.out.println(person.name + " " + person.surname + " was checked out from apartment " + apartment.id);
            } else throw new NotBelongException(person);
        } else throw new NotBelongException(this);
    }

    protected void renewApartmentRent(Apartment apartment, Date date){
        apartment.rentEnd = date;
        System.out.println(name + " " + surname + " has renewed the rent of apartment " + apartment.id);
        removeApartmentLetter(apartment);
    }

    protected void removeApartmentLetter(Apartment apartment){
        tenantLetters.removeIf(letter -> {
            Apartment letterApartment = letter.getApartment();
            if (letterApartment != null && letterApartment.equals(apartment)) {
                System.out.println("Tenant letter for " + this.name + " " + this.surname + " for apartment " + apartment.id + " was removed");
                return true;
            }
            return false;
        });
    }

    protected void ifNotApartmentRenewed(Apartment apartment){
        System.out.println("30 days have passed after expiring of rent.");
        apartment.evictPeople();
    }
}
