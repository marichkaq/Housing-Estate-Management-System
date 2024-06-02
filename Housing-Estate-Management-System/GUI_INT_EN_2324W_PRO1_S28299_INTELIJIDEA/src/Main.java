import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    static ArrayList<Apartment> apartments = new ArrayList<>();
    static ArrayList<ParkingPlace> parkingSpaces = new ArrayList<>();
    static ArrayList<Person> tenants = new ArrayList<>();
    static ArrayList<HousingEstate> housingEstates = new ArrayList<>();

    public static void main(String[] args) {

        housingEstates.add(new HousingEstate("DreamTown", "Main", apartments, parkingSpaces, tenants));

        tenants.add(new MainTenant("John", "Smith", 12345, "123 Main Street"));
        tenants.add(new Person("Jane", "Doe", 73666, "456 Elm Avenue"));
        tenants.add(new MainTenant("Michael", "Johnson", 54321, "789 Oak Lane"));
        tenants.add(new MainTenant("Sarah", "Wilson", 98765, "321 Pine Road"));
        tenants.add(new Person("David", "Brown", 24680, "654 Cedar Street"));
        tenants.add(new MainTenant("Emily", "Davis", 13579, "987 Birch Avenue"));


        apartments.add(new Apartment(6, 5, 2.4, housingEstates.get(0)));
        apartments.add(new Apartment(6.6, 6, 2.5, housingEstates.get(0)));
        apartments.add(new Apartment(7, 5.8, 2.8, housingEstates.get(0)));
        apartments.add(new Apartment(6.9, 5.5, 3, housingEstates.get(0)));
        apartments.add(new Apartment(8, 7, 2.5, housingEstates.get(0)));
        apartments.add(new Apartment(6, 5, 2.7, housingEstates.get(0)));
        apartments.add(new Apartment(7.5, 6.5, 2.5, housingEstates.get(0)));
        apartments.add(new Apartment(7.3, 5.6, 2.7, housingEstates.get(0)));
        apartments.add(new Apartment(6.5, 6, 2.7, housingEstates.get(0)));
        apartments.add(new Apartment(8, 5.8, 2.7, housingEstates.get(0)));

        parkingSpaces.add(new ParkingPlace(5.5, 2.4, 2.4, housingEstates.get(0)));
        parkingSpaces.add(new ParkingPlace(5.6, 2.6, 2.6, housingEstates.get(0)));
        parkingSpaces.add(new ParkingPlace(6, 2.8, 2.5, housingEstates.get(0)));
        parkingSpaces.add(new ParkingPlace(6, 3, 3, housingEstates.get(0)));
        parkingSpaces.add(new ParkingPlace(7, 3.5, 3, housingEstates.get(0)));
        parkingSpaces.add(new ParkingPlace(6.5, 3, 2.8, housingEstates.get(0)));
        parkingSpaces.add(new ParkingPlace(6.8, 2.8, 3, housingEstates.get(0)));
        parkingSpaces.add(new ParkingPlace(6.4, 3, 3, housingEstates.get(0)));
        parkingSpaces.add(new ParkingPlace(7, 4, 2.5, housingEstates.get(0)));
        parkingSpaces.add(new ParkingPlace(6.5, 3, 2.8, housingEstates.get(0)));



        for (Person tenant : tenants) {
            tenant.setItemsAndVehicles(new ArrayList<>());
        }

        tenants.get(0).itemsAndVehicles.add(new Item("RecordPlayer", 0.3, 0.2, 0.2));
        tenants.get(1).itemsAndVehicles.add(new Amphibious("AquaBlaster", 3, 2, 2, 50, "Gasoline", 40));
        tenants.get(1).itemsAndVehicles.add(new Item("Skateboard", 0.6, 0.2, 0.2));
        tenants.get(2).itemsAndVehicles.add(new Boat("Wave Rider", 3, 1.5, 0.5, 220, "Outboard", "Fishing Boat"));
        tenants.get(3).itemsAndVehicles.add(new CityCar("CityRacer", 3.5, 1.5, 1.2, 1.5, "Electric", "Toyota"));
        tenants.get(4).itemsAndVehicles.add(new Item("Microscope", 0.2, 0.2, 0.3));
        tenants.get(5).itemsAndVehicles.add(new Motorcycle("Raptor", 1.5, 0.6, 1, 300, "V-Twin", false));

        SimpleDateFormat dateFormat = new SimpleDateFormat(("dd.MM.yyyy"));

        try {
            ((MainTenant) tenants.get(0)).rentApartment(apartments.get(0), dateFormat.parse("12.10.2023"), dateFormat.parse("05.10.2024"));
        } catch (ProblematicTenantException | ParseException e) {
            e.printStackTrace();
        }

        try {
            ((MainTenant) tenants.get(0)).checkInPerson(apartments.get(0), tenants.get(1));
        } catch (NotBelongException e) {
            e.printStackTrace();
        }

        try {
            tenants.get(1).rentParkingPlace(parkingSpaces.get(0), dateFormat.parse("12.11.2023"), dateFormat.parse("10.02.2024"));
        } catch (NotBelongException | ParseException e) {
            e.printStackTrace();
        }

        try {
            tenants.get(1).takeInItem(tenants.get(1).currentParkingPlaces.get(0), tenants.get(1).itemsAndVehicles.get(0));
        } catch (TooManyThingsException | NotBelongException e) {
            e.printStackTrace();
        }

        try {
            ((MainTenant) tenants.get(2)).rentApartment(apartments.get(1), dateFormat.parse("05.06.2023"), dateFormat.parse("12.12.2023"));
        } catch (ProblematicTenantException | ParseException e) {
            e.printStackTrace();
        }

        try {
            tenants.get(2).rentParkingPlace(parkingSpaces.get(1), dateFormat.parse("06.06.2023"), dateFormat.parse("12.12.2023"));
        } catch (NotBelongException | ParseException e) {
            e.printStackTrace();
        }

        try {
            tenants.get(2).takeInItem(tenants.get(2).currentParkingPlaces.get(0), tenants.get(2).itemsAndVehicles.get(0));
        } catch (TooManyThingsException | NotBelongException e) {
            e.printStackTrace();
        }

        try {
            ((MainTenant) tenants.get(3)).rentApartment(apartments.get(2), dateFormat.parse("01.11.2022"), dateFormat.parse("01.11.2023"));
        } catch (ProblematicTenantException | ParseException e) {
            e.printStackTrace();
        }

        try {
            tenants.get(3).rentParkingPlace(parkingSpaces.get(2), dateFormat.parse("10.12.2022"), dateFormat.parse("12.12.2022"));
        } catch (NotBelongException | ParseException e) {
            e.printStackTrace();
        }

        try {
            tenants.get(3).takeInItem(tenants.get(3).currentParkingPlaces.get(0), tenants.get(3).itemsAndVehicles.get(0));
        } catch (TooManyThingsException | NotBelongException e) {
            e.printStackTrace();
        }

        try {
            ((MainTenant) tenants.get(5)).rentApartment(apartments.get(3), dateFormat.parse("10.02.2023"), dateFormat.parse("10.02.2024"));
        } catch (ProblematicTenantException | ParseException e) {
            e.printStackTrace();
        }

        try {
            ((MainTenant) tenants.get(5)).checkInPerson(apartments.get(3), tenants.get(4));
        } catch (NotBelongException e) {
            e.printStackTrace();
        }

        try {
            tenants.get(5).rentParkingPlace(parkingSpaces.get(3), dateFormat.parse("12.02.2023"), dateFormat.parse("10.01.2024"));
        } catch (NotBelongException | ParseException e) {
            e.printStackTrace();
        }

        try {
            tenants.get(5).takeInItem(tenants.get(5).currentParkingPlaces.get(0), tenants.get(5).itemsAndVehicles.get(0));
        } catch (TooManyThingsException | NotBelongException e) {
            e.printStackTrace();
        }

        try {
            ((MainTenant) tenants.get(0)).rentApartment(apartments.get(4), dateFormat.parse("01.12.2023"), dateFormat.parse("01.12.2024"));
        } catch (ProblematicTenantException | ParseException e) {
            e.printStackTrace();
        }

        try {
            ((MainTenant) tenants.get(0)).rentApartment(apartments.get(5), dateFormat.parse("12.12.2023"), dateFormat.parse("05.09.2023"));
        } catch (ProblematicTenantException | ParseException e) {
            e.printStackTrace();
        }

       /* try {
            ((MainTenant) tenants.get(0)).rentApartment(apartments.get(6), dateFormat.parse("12.10.2023"), dateFormat.parse("05.11.2024"));
        } catch (ProblematicTenantException | ParseException e) {
            e.printStackTrace();
        } */

        showMenu();

    }

    public static void showMenu() {
        System.out.println("Would you like to open a menu of actions?\n" + "Please answer Yes/No");
        Scanner scanner = new Scanner(System.in);
        String open = scanner.nextLine();
        boolean exit = false;
        if (open.equals("Yes") || open.equals("yes")) {
            while (!exit) {
                System.out.println("\n" +
                        "∵*.•´¸.•*´✶´♡\n" +
                        "° ☆ °˛*˛☆_Π______*˚☆*\n" +
                        "˚ ˛★˛•˚*/______/ ~⧹。˚˚\n" +
                        "˚ ˛•˛•˚ ｜ 田田 ｜門｜ ˚*\n" +
                        "\uD83C\uDF37╬╬\uD83C\uDF37╬╬\uD83C\uDF37╬╬\uD83C\uDF37╬╬\uD83C\uDF37\n" +
                        "____________MENU OF ACTIONS____________\n" +
                        "1. Choose the housing estate\n" +
                        "2. Create new housing estate \n" +
                        "3. Exit");

                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter the id of housing estate:");
                        for (HousingEstate estate : housingEstates) {
                            System.out.println(estate.name + " id: " + estate.id);
                        }
                        int estId = scanner.nextInt();
                        System.out.println("Choose option:\n" + "1. see information about this housing estate\n" +
                                "2. create new apartment\n" + "3. create new parking place\n" + "4. rent an apartment\n"
                                + "5. rent a parking place\n" + "6. for tenant to check in person into apartment\n" +
                                "7. for tenant to check out person from apartment\n" +
                                "8. for tenant to put item into parking place\n" +
                                "9. for tenant to put item out of the parking place\n" +
                                "10. for tenant to cancel the apartment rent\n" +
                                "11. for tenant to renew the apartment rent\n" +
                                "12. for tenant to cancel the parking place rent\n" +
                                "13. for tenant to renew the parking place rent\n" +
                                "14. save the status of persons residing in the housing estate\n" +
                                "15. back to main menu");
                        int action = scanner.nextInt();
                        switch (action) {
                            case 1:
                                showInfo(estId);
                                break;
                            case 2:
                                createApartment(estId);
                                break;
                            case 3:
                                createParkingPlace(estId);
                                break;
                            case 4:
                                rentApartment(estId);
                                break;
                            case 5:
                                rentParkingPlace(estId);
                                break;
                            case 6:
                                tenantCheckIn(estId);
                                break;
                            case 7:
                                tenantCheckOut(estId);
                                break;
                            case 8:
                                tenantPutIn(estId);
                                break;
                            case 9:
                                tenantTakeOut(estId);
                                break;
                            case 10:
                                cancelApartmentRent(estId);
                                break;
                            case 11:
                                renewApartmentRent(estId);
                                break;
                            case 12:
                                cancelParkingRent(estId);
                                break;
                            case 13:
                                renewParkingRent(estId);
                                break;
                            case 14:
                                saveTenantsInfo(estId);
                                break;
                            case 15:
                                break;
                            default:
                                System.out.println("Invalid option.\n");
                                break;
                        }
                        break;
                    case 2:
                        createHousingEstate();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option.\n");
                        break;
                }
            }

        }
    }

    //}

    public static void showInfo(int estateId) {
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estateId) {
                estateFound = true;
                System.out.println(est.name + "\n" + "id: " + est.id + "\nstreet: " + est.street + "\napartments: ");
                for (Apartment ap : est.apartments) {
                    System.out.print(ap.id + " ");
                }
                System.out.println("\navailable apartments: ");

                for (Apartment ap : est.apartments) {
                    if (ap.mainTenant == null) {
                        System.out.print(ap.id + " ");
                    }
                }

                System.out.println("\nparking places: ");
                for (ParkingPlace pl : est.parkingPlaces) {
                    System.out.print(pl.id + " ");
                }
                System.out.println("\navailable parking places: ");
                for (ParkingPlace pl : est.parkingPlaces) {
                    if (pl.tenant == null) {
                        System.out.print(pl.id + " ");
                    }
                }
                System.out.println("\n");
            }
        }
        if (!estateFound) {
            System.out.println("Invalid estate id");
        }
    }

    public static void createApartment(int estateId) {
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estateId) {
                estateFound = true;

                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter length of the apartment:");
                double length = scanner.nextDouble();

                System.out.println("Enter width of the apartment:");
                double width = scanner.nextDouble();

                System.out.println("Enter height of the apartment:");
                double height = scanner.nextDouble();

                Apartment apartment = new Apartment(length, width, height, est);
                est.apartments.add(apartment);
                System.out.println("Apartment with id " + apartment.id + " was created in housing estate " + est.name);
            }
        }
        if (!estateFound) {
            System.out.println("Invalid estate id");
        }
    }

    public static void createParkingPlace(int estateId) {
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estateId) {
                estateFound = true;

                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter length of the parking place:");
                double length = scanner.nextDouble();

                System.out.println("Enter width of the parking place:");
                double width = scanner.nextDouble();

                System.out.println("Enter height of the parking place:");
                double height = scanner.nextDouble();

                ParkingPlace place = new ParkingPlace(length, width, height, est);
                est.parkingPlaces.add(place);
                System.out.println("Parking place with id " + place.id + " was created in housing estate " + est.name);
            }
        }
        if (!estateFound) {
            System.out.println("Invalid estate id");
        }
    }

    public static void rentApartment(int estateId) {
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estateId) {
                estateFound = true;
                MainTenant person = null;
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter the id of apartment to rent:");
                System.out.println("available apartments: ");
                for (Apartment ap : est.apartments) {
                    if (ap.mainTenant == null) {
                        System.out.print(ap.id + " ");
                    }
                }
                System.out.println("\n");
                int apartmentId = scanner.nextInt();
                scanner.nextLine();
                boolean apartmentFound = false;
                for (Apartment ap : est.apartments) {
                    if (ap.id == apartmentId) {
                        apartmentFound = true;
                        System.out.println("Enter the date of rent start (in form of dd.MM.yyyy): ");
                        String dateS = scanner.nextLine();
                        System.out.println("Enter the date of rent end (in form of dd.MM.yyyy): ");
                        String dateE = scanner.nextLine();


                        System.out.println("1. choose from existing tenants\n" + "2. new tenant");
                        int tenant = scanner.nextInt();
                        scanner.nextLine();
                        switch (tenant) {
                            case 1:
                                System.out.println("Enter an id of person from the list: ");
                                for (Person p : est.tenants) {
                                    if (p.getClass() == MainTenant.class) {
                                        System.out.println(p.name + " " + p.surname + " - id: " + p.id);
                                    }
                                }
                                int tenantId = scanner.nextInt();

                                boolean tenantExists = false;
                                for (Person t : est.tenants) {
                                    if (t.getClass() == MainTenant.class) {
                                        if (t.id == tenantId) {
                                            tenantExists = true;
                                            person = (MainTenant) t;
                                            try {
                                                SimpleDateFormat dateFormat = new SimpleDateFormat(("dd.MM.yyyy"));
                                                Date startDate = dateFormat.parse(dateS);
                                                Date endDate = dateFormat.parse(dateE);
                                                person.rentApartment(ap, startDate, endDate);
                                            } catch (ProblematicTenantException | ParseException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                                if (!tenantExists) {
                                    System.out.println("Invalid tenant id");
                                }

                                break;
                            case 2:
                                System.out.println("Enter the name of main tenant:");
                                String name = scanner.nextLine();
                                System.out.println("Enter the surname of main tenant:");
                                String surname = scanner.nextLine();
                                System.out.println("Enter the id of main tenant:");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter the address of main tenant:");
                                String address = scanner.nextLine().trim();

                                MainTenant t1 = new MainTenant(name, surname, id, address);
                                person = t1;
                                est.tenants.add(person);
                                try {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat(("dd.MM.yyyy"));
                                    Date startDate = dateFormat.parse(dateS);
                                    Date endDate = dateFormat.parse(dateE);
                                    person.rentApartment(ap, startDate, endDate);
                                } catch (ProblematicTenantException | ParseException e) {
                                    e.printStackTrace();
                                }
                                break;

                        }
                    }
                }
                if (!apartmentFound) {
                    System.out.println("Invalid apartment id");
                }
            }
        }
        if (!estateFound) {
            System.out.println("Invalid estate id");
        }
    }

    public static void rentParkingPlace(int estateId) {
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estateId) {
                estateFound = true;
                Person person = null;
                Scanner scanner = new Scanner(System.in);


                System.out.println("Enter the id of parking place to rent:");
                System.out.println("available parking places: ");
                for (ParkingPlace place : est.parkingPlaces) {
                    if (place.tenant == null) {
                        System.out.print(place.id + " ");
                    }
                }
                System.out.println("\n");

                int parkingId = scanner.nextInt();
                scanner.nextLine();
                boolean parkingFound = false;
                for (ParkingPlace place : est.parkingPlaces) {
                    if (place.id == parkingId) {
                        parkingFound = true;
                        System.out.println("Enter the date of rent start (in form of dd.MM.yyyy): ");
                        String dateS = scanner.nextLine();
                        System.out.println("Enter the date of rent end (in form of dd.MM.yyyy): ");
                        String dateE = scanner.nextLine();

                        SimpleDateFormat dateFormat = new SimpleDateFormat(("dd.MM.yyyy"));
                        Date startDate = null;
                        try {
                            startDate = dateFormat.parse(dateS);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date endDate = null;
                        try {
                            endDate = dateFormat.parse(dateE);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        //from existing people in array or new
                        System.out.println("choose from existing tenants");

                        System.out.println("Enter an id of person from the list: ");
                        for (Person p : est.tenants) {
                            System.out.println(p.name + " " + p.surname + " - id: " + p.id);
                        }
                        int tenantId = scanner.nextInt();

                        boolean tenantExists = false;
                        for (Person t : est.tenants) {
                            if (t.id == tenantId) {
                                tenantExists = true;
                                person = t;
                                try {
                                    person.rentParkingPlace(place, startDate, endDate);
                                } catch (NotBelongException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (!tenantExists) {
                            System.out.println("Invalid tenant id");

                        }

                    }
                }
                if (!parkingFound) {
                    System.out.println("Invalid parking space id");
                }
            }
        }
        if (!estateFound) {
            System.out.println("Invalid estate id");
        }
    }


    public static void createHousingEstate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of housing estate:");
        String name = scanner.nextLine();
        System.out.println("Enter the street:");
        String street = scanner.nextLine();
        ArrayList<Apartment> apartments1 = new ArrayList<>();
        ArrayList<ParkingPlace> parkingPlaces = new ArrayList<>();
        ArrayList<Person> tenants1 = new ArrayList<>();
        housingEstates.add(new HousingEstate(name, street, apartments1, parkingPlaces, tenants1));

        System.out.println("Housing estate " + name + " was created");
    }

    public static void tenantCheckIn(int estateId) {
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estateId) {
                estateFound = true;
                Scanner scanner = new Scanner(System.in);
                MainTenant mainTenant;
                Apartment apartment = null;

                System.out.println("Enter an id of the main tenant from the list: ");
                for (Person p : est.tenants) {
                    if (p.getClass() == MainTenant.class) {
                        System.out.println(p.name + " " + p.surname + " - id: " + p.id);
                    }
                }
                int tenantId = scanner.nextInt();


                boolean tenantExists = false;
                for (Person t : est.tenants) {
                    if (t.getClass() == MainTenant.class) {
                        if (t.id == tenantId) {
                            tenantExists = true;
                            mainTenant = (MainTenant) t;

                            System.out.println("Enter the number of apartment from the tenant's: ");
                            for (Apartment ap : mainTenant.currentApartments) {
                                System.out.print(ap.id + " ");
                            }
                            System.out.println("\n");
                            int apartmentId = scanner.nextInt();
                            scanner.nextLine();
                            for (Apartment ap : mainTenant.currentApartments) {
                                if (ap.id == apartmentId) {
                                    apartment = ap;
                                }
                            }

                            System.out.println("Enter the name of a new person: ");
                            String name = scanner.nextLine();
                            System.out.println("Enter the surname of new tenant:");
                            String surname = scanner.nextLine();
                            System.out.println("Enter the id of new tenant:");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter the address of new tenant:");
                            String address = scanner.nextLine().trim();

                            Person person = new Person(name, surname, id, address);

                            try {
                                mainTenant.checkInPerson(apartment, person);
                            } catch (NotBelongException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
                if (!tenantExists) {
                    System.out.println("Invalid tenant id");
                }

            }
        }
        if (!estateFound) {
            System.out.println("Invalid estate id");
        }
    }

    public static void tenantCheckOut(int estateId) {
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estateId) {
                estateFound = true;
                Scanner scanner = new Scanner(System.in);
                MainTenant mainTenant;
                Apartment apartment = null;
                Person person = null;

                System.out.println("Enter an id of the main tenant from the list: ");
                for (Person p : est.tenants) {
                    if (p.getClass() == MainTenant.class) {
                        System.out.println(p.name + " " + p.surname + " - id: " + p.id);
                    }
                }
                int tenantId = scanner.nextInt();


                boolean tenantExists = false;
                for (Person t : est.tenants) {
                    if (t.getClass() == MainTenant.class) {
                        if (t.id == tenantId) {
                            tenantExists = true;
                            mainTenant = (MainTenant) t;

                            System.out.println("Enter the number of apartment from the tenant's: ");
                            for (Apartment ap : mainTenant.currentApartments) {
                                System.out.print(ap.id + " ");
                            }
                            System.out.println("\n");
                            int apartmentId = scanner.nextInt();
                            scanner.nextLine();
                            for (Apartment ap : mainTenant.currentApartments) {
                                if (ap.id == apartmentId) {
                                    apartment = ap;
                                }
                            }

                            System.out.println("Enter the id of person to check out from the list: ");
                            for (Person p : apartment.tenants) {
                                if (p.getClass() != MainTenant.class) {
                                    System.out.println(p.name + " " + p.surname + " - id: " + p.id);
                                }
                            }
                            boolean personExists = false;
                            int personId = scanner.nextInt();

                            for (Person p : apartment.tenants) {
                                if (p.getClass() != MainTenant.class) {
                                    if (p.id == personId) {
                                        personExists = true;
                                        person = p;
                                    }
                                }
                            }
                            if (!personExists) {
                                System.out.println("Invalid person id");
                            }

                            try {
                                mainTenant.checkOutPerson(apartment, person);
                            } catch (NotBelongException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
                if (!tenantExists) {
                    System.out.println("Invalid tenant id");
                }

            }
        }
        if (!estateFound) {
            System.out.println("Invalid estate id");
        }
    }

    public static void tenantPutIn(int estateId) {
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estateId) {
                estateFound = true;
                Scanner scanner = new Scanner(System.in);
                ParkingPlace parkingPlace = null;
                Person person = null;
                Item item = null;

                System.out.println("Enter an id of the tenant from the list: ");
                for (Person p : est.tenants) {
                    if (!(p.currentParkingPlaces.isEmpty())) {
                        System.out.println(p.name + " " + p.surname + " - id: " + p.id);
                    }
                }
                int tenantId = scanner.nextInt();


                boolean tenantExists = false;
                for (Person t : est.tenants) {
                    if (!(t.currentParkingPlaces.isEmpty())) {
                        if (t.id == tenantId) {
                            tenantExists = true;
                            person = t;

                            System.out.println("Enter the number of parking space from the tenant's: ");
                            for (ParkingPlace pl : person.currentParkingPlaces) {
                                System.out.print(pl.id + " ");
                            }
                            System.out.println("\n");
                            int parkingId = scanner.nextInt();
                            scanner.nextLine();
                            for (ParkingPlace pl : person.currentParkingPlaces) {
                                if (pl.id == parkingId) {
                                    parkingPlace = pl;
                                }
                            }

                            System.out.println("1. choose from existing items and vehicles\n" + "2. new item or vehicle");
                            int itemChose = scanner.nextInt();
                            scanner.nextLine();
                            switch (itemChose) {
                                case 1:
                                    System.out.println("Enter an id of item from the list: ");
                                    for (Item i : person.itemsAndVehicles) {
                                        System.out.println(i.name + " - id: " + i.id);
                                    }
                                    int itemId = scanner.nextInt();

                                    boolean itemExists = false;
                                    for (Item i : person.itemsAndVehicles) {
                                        if (i.id == itemId) {
                                            itemExists = true;
                                            item = i;
                                        }
                                    }
                                    //person.itemsAndVehicles.add(item);
                                    try {
                                        assert parkingPlace != null;
                                        person.takeInItem(parkingPlace, item);
                                    } catch (TooManyThingsException | NotBelongException e) {
                                        e.printStackTrace();
                                    }


                                    if (!itemExists) {
                                        System.out.println("Invalid item id");
                                    }

                                    break;
                                case 2:
                                    item = createItem(person);
                                    try {
                                        assert parkingPlace != null;
                                        person.takeInItem(parkingPlace, item);
                                    } catch (TooManyThingsException | NotBelongException e) {
                                        e.printStackTrace();
                                    }
                                    break;

                            }
                        }
                    }
                }
                if (!tenantExists) {
                    System.out.println("Invalid tenant id");
                }
            }
        }
        if (!estateFound) {
            System.out.println("Invalid estate id");
        }
    }

    public static Item createItem(Person person) {
        System.out.println("Enter the type of item from the list: \n" + "1. Item\n" + "2. Amphibious\n" + "3. Boat\n" +
                "4. CityCar\n" + "5. Motorcycle\n" + "6. Off-road car");
        Scanner scanner = new Scanner(System.in);
        Item object = null;
        int num = scanner.nextInt();
        scanner.nextLine();
        switch (num) {
            case 1:
                System.out.println("Enter the name of the item:");
                String name = scanner.nextLine();
                System.out.println("Enter the length of an item:");
                double length = scanner.nextDouble();
                System.out.println("Enter the width of an item:");
                double width = scanner.nextDouble();
                System.out.println("Enter the height of an item:");
                double height = scanner.nextDouble();
                scanner.nextLine();

                Item item = new Item(name, length, width, height);
                person.itemsAndVehicles.add(item);
                object = item;
                break;
            case 2:
                System.out.println("Enter the name of the amphibious:");
                String nameA = scanner.nextLine();
                System.out.println("Enter the length of an amphibious:");
                double lengthA = scanner.nextDouble();
                System.out.println("Enter the width of an amphibious:");
                double widthA = scanner.nextDouble();
                System.out.println("Enter the height of an amphibious:");
                double heightA = scanner.nextDouble();
                System.out.println("Enter the engine capacity of an amphibious:");
                double engineCapacityA = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter the engine type of an amphibious:");
                String engineTypeA = scanner.nextLine();
                System.out.println("Enter the maximum water speed of an amphibious:");
                double maxWaterSpeed = scanner.nextDouble();
                scanner.nextLine();

                Amphibious amphibious = new Amphibious(nameA, lengthA, widthA, heightA, engineCapacityA, engineTypeA, maxWaterSpeed);
                person.itemsAndVehicles.add(amphibious);
                object = amphibious;

                break;
            case 3:
                System.out.println("Enter the name of the boat:");
                String nameB = scanner.nextLine();
                System.out.println("Enter the length of a boat:");
                double lengthB = scanner.nextDouble();
                System.out.println("Enter the width of a boat:");
                double widthB = scanner.nextDouble();
                System.out.println("Enter the height of a boat:");
                double heightB = scanner.nextDouble();
                System.out.println("Enter the engine capacity of a boat:");
                double engineCapacityB = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter the engine type of a boat:");
                String engineTypeB = scanner.nextLine();
                System.out.println("Enter the type of boat:");
                String boatType = scanner.nextLine();

                Boat boat = new Boat(nameB, lengthB, widthB, heightB, engineCapacityB, engineTypeB, boatType);
                person.itemsAndVehicles.add(boat);
                object = boat;

                break;
            case 4:
                System.out.println("Enter the name of the city car:");
                String nameC = scanner.nextLine();
                System.out.println("Enter the length of a city car:");
                double lengthC = scanner.nextDouble();
                System.out.println("Enter the width of a city car:");
                double widthC = scanner.nextDouble();
                System.out.println("Enter the height of a city car:");
                double heightC = scanner.nextDouble();
                System.out.println("Enter the engine capacity of a city car:");
                double engineCapacityC = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter the engine type of a city car:");
                String engineTypeC = scanner.nextLine();
                System.out.println("Enter the brand of a city car:");
                String carBrand = scanner.nextLine();

                CityCar cityCar = new CityCar(nameC, lengthC, widthC, heightC, engineCapacityC, engineTypeC, carBrand);
                person.itemsAndVehicles.add(cityCar);
                object = cityCar;

                break;
            case 5:
                System.out.println("Enter the name of the motorcycle:");
                String nameM = scanner.nextLine();
                System.out.println("Enter the length of a motorcycle:");
                double lengthM = scanner.nextDouble();
                System.out.println("Enter the width of a motorcycle:");
                double widthM = scanner.nextDouble();
                System.out.println("Enter the height of a motorcycle:");
                double heightM = scanner.nextDouble();
                System.out.println("Enter the engine capacity of a motorcycle:");
                double engineCapacityM = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter the engine type of a motorcycle:");
                String engineTypeM = scanner.nextLine();
                System.out.println("Enter if a motorcycle has a trolley(true/false):");
                boolean withTrolley = scanner.hasNext();
                scanner.nextLine();

                Motorcycle motorcycle = new Motorcycle(nameM, lengthM, widthM, heightM, engineCapacityM, engineTypeM, withTrolley);
                person.itemsAndVehicles.add(motorcycle);
                object = motorcycle;

                break;
            case 6:
                System.out.println("Enter the name of the off-road car:");
                String nameO = scanner.nextLine();
                System.out.println("Enter the length of an off-road car:");
                double lengthO = scanner.nextDouble();
                System.out.println("Enter the width of an off-road car:");
                double widthO = scanner.nextDouble();
                System.out.println("Enter the height of an off-road car:");
                double heightO = scanner.nextDouble();
                System.out.println("Enter the engine capacity of an off-road car:");
                double engineCapacityO = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter the engine type of an off-road car:");
                String engineTypeO = scanner.nextLine();
                System.out.println("Enter the maximum water speed of an off-road car:");
                double wheelSize = scanner.nextDouble();

                OffroadCar offroadCar = new OffroadCar(nameO, lengthO, widthO, heightO, engineCapacityO, engineTypeO, wheelSize);
                person.itemsAndVehicles.add(offroadCar);
                object = offroadCar;

                break;

        }
        return object;
    }

    public static void tenantTakeOut(int estateId) {
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estateId) {
                estateFound = true;
                Scanner scanner = new Scanner(System.in);
                ParkingPlace parkingPlace = null;
                Person person = null;
                Item item = null;

                System.out.println("Enter an id of the tenant from the list: ");
                for (Person p : est.tenants) {
                    if (!(p.currentParkingPlaces.isEmpty())) {
                        System.out.println(p.name + " " + p.surname + " - id: " + p.id);
                    }
                }
                int tenantId = scanner.nextInt();


                boolean tenantExists = false;
                for (Person t : est.tenants) {
                    if (!(t.currentParkingPlaces.isEmpty())) {
                        if (t.id == tenantId) {
                            tenantExists = true;
                            person = t;

                            System.out.println("Enter the number of parking space from the tenant's: ");
                            for (ParkingPlace pl : person.currentParkingPlaces) {
                                System.out.print(pl.id + " ");
                            }
                            System.out.println("\n");
                            int parkingId = scanner.nextInt();
                            scanner.nextLine();
                            for (ParkingPlace pl : person.currentParkingPlaces) {
                                if (pl.id == parkingId) {
                                    parkingPlace = pl;
                                }
                            }

                            System.out.println("Enter an id of item from the list: ");
                            for (Item i : parkingPlace.items) {
                                System.out.println(i.name + " - id: " + i.id);
                            }
                            int itemId = scanner.nextInt();

                            boolean itemExists = false;
                            for (Item i : parkingPlace.items) {
                                if (i.id == itemId) {
                                    itemExists = true;
                                    item = i;
                                }
                            }
                            try {
                                assert parkingPlace != null;
                                person.takeOutItem(parkingPlace, item);
                            } catch (NotBelongException e) {
                                e.printStackTrace();
                            }


                            if (!itemExists) {
                                System.out.println("Invalid item id");
                            }
                        }
                    }
                }
                if (!tenantExists) {
                    System.out.println("Invalid tenant id");
                }
            }
        }
        if (!estateFound) {
            System.out.println("Invalid estate id");
        }
    }

    static Comparator<Apartment> apartmentVolumeComparator = new Comparator<Apartment>() {
        @Override
        public int compare(Apartment apartment1, Apartment apartment2) {
            double volume1 = apartment1.volume;
            double volume2 = apartment2.volume;
            return Double.compare(volume1, volume2);
        }
    };

    static Comparator<ParkingPlace> parkingSpaceVolumeComparator = new Comparator<ParkingPlace>() {
        @Override
        public int compare(ParkingPlace place1, ParkingPlace place2) {
            double volume1 = place1.volume;
            double volume2 = place2.volume;
            return Double.compare(volume1, volume2);
        }
    };

    static Comparator<Item> itemComparator = new Comparator<Item>() {
        @Override
        public int compare(Item item1, Item item2) {
            int volumeComparison = Double.compare(item2.volume, item1.volume);
            if (volumeComparison != 0) {
                return volumeComparison;
            } else {
                return item1.name.compareTo(item2.name);
            }
        }
    };


    public static void saveTenantsInfo(int estateId) {
        String fileName = "tenantsInfo.txt";
        String data = "";
        boolean estateFound = false;
        Scanner scanner = new Scanner(System.in);

        for (HousingEstate estate : housingEstates) {
            if (estate.id == estateId) {
                estateFound = true;
                Collections.sort(estate.apartments, apartmentVolumeComparator);
                Collections.sort(estate.parkingPlaces, parkingSpaceVolumeComparator);

                System.out.println("Enter the id of tenant from the menu: ");


                for (Person person : estate.tenants) {
                    System.out.println("Tenants: " + person.name + " " + person.surname + " id: " + person.id);
                }

                int personId = scanner.nextInt();
                boolean idExists = false;

                for (Person person : estate.tenants) {
                    if(person.id == personId){
                        idExists = true;

                    String tenantsData = ("Tenant: " + person.name + " " + person.surname + " id: " + person.id +
                            " address: " + person.address + "\ncurrent apartments: ");
                    data += tenantsData;

                    if(!person.currentApartments.isEmpty()) {
                        for (Apartment ap : person.currentApartments) {
                            String tenantsApartmentsData = (ap.id + " volume:" + ap.volume + " \n");
                            data += tenantsApartmentsData;
                        }
                    }
                    if(!person.currentParkingPlaces.isEmpty()) {
                        String tenantsParking = ("\ncurrent parking spaces: ");
                        data += tenantsParking;
                        for (ParkingPlace pl : person.currentParkingPlaces) {
                            String placeData = (pl.id + " volume:" + pl.volume + "\nitems: ");
                            data += placeData;
                            for (Item item : pl.items) {
                                String itemsData = (item.name + " id: " + item.id + " volume: " + item.volume + " \n");
                                data += itemsData;
                            }
                        }
                    }
                    if(!person.tenantLetters.isEmpty()) {
                        String tenantsLetters = ("tenant letters: ");
                        data += tenantsLetters;
                        for (TenantLetter letter : person.tenantLetters) {

                            if (!(letter.getApartment() == null)) {
                                String messageApData = (" apartments: " + letter.getApartment().id);
                                data += messageApData;
                            }
                            if (!(letter.getApartment() == null)) {
                                String messagePData = (" parking spaces: " + letter.getParkingPlace().id + " \n");
                                data += messagePData;
                            }
                        }
                    }
                        System.out.println("File with data was created");
                }
                }
                if(!idExists){
                    System.out.println("Invalid person id");
                }

            }
        }
        if (!estateFound) {
            System.out.println("Invalid estate id");
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void cancelApartmentRent(int estId){
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estId) {
                estateFound = true;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter an apartment from the list: ");
                for(Apartment apartment : est.apartments){
                        if(apartment.mainTenant != null){
                            System.out.println(apartment.id + " - main tenant " + apartment.mainTenant.name + " "
                                    + apartment.mainTenant.surname);
                        }
                }

                int apartmentId = scanner.nextInt();
                boolean apartmentExists = false;
                for(Apartment apartment : est.apartments){
                    if(apartment.id == apartmentId){
                        apartmentExists = true;
                        MainTenant mainTenant = (MainTenant) apartment.mainTenant;
                        try {
                            mainTenant.cancelApartmentsRent(apartment);
                        } catch (NotBelongException e) {
                            e.printStackTrace();
                        }
                    }
                } if(! apartmentExists){
                    System.out.println("Invalid apartment Id");
                }

            }
        } if(!estateFound){
            System.out.println("Invalid estate id");
        }
    }

    public static void renewApartmentRent(int estId){
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estId) {
                estateFound = true;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter an apartment from the list of apartments which rent has expired: ");
                for(Apartment apartment : est.apartments) {
                    if (apartment.mainTenant != null) {
                        for (TenantLetter letter : apartment.mainTenant.tenantLetters) {
                            if (letter.getApartment() == apartment) {
                                System.out.println(apartment.id + " - main tenant " + apartment.mainTenant.name + " "
                                        + apartment.mainTenant.surname);
                            }
                        }
                    }
                }
                int apartmentId = scanner.nextInt();
                scanner.nextLine();
                boolean apartmentExists = false;
                for(Apartment apartment : est.apartments){
                    if(apartment.id == apartmentId){
                        apartmentExists = true;
                        MainTenant mainTenant = (MainTenant) apartment.mainTenant;
                        System.out.println("Enter the Date (in form of dd.MM.yyyy):");
                        String dateStr = scanner.nextLine();
                        SimpleDateFormat dateFormat = new SimpleDateFormat(("dd.MM.yyyy"));
                        Date date = null;
                        try {
                            date = dateFormat.parse(dateStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        mainTenant.renewApartmentRent(apartment, date);
                    }
                } if(! apartmentExists){
                    System.out.println("Invalid apartment Id");
                }

            }
        } if(!estateFound){
            System.out.println("Invalid estate id");
        }
    }

    public static void cancelParkingRent(int estId){
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estId) {
                estateFound = true;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter a parking place from the list: ");
                for(ParkingPlace parkingPlace : est.parkingPlaces){
                    if(parkingPlace.tenant != null){
                        System.out.println(parkingPlace.id + " - tenant " + parkingPlace.tenant.name + " "
                                + parkingPlace.tenant.surname);
                    }
                }

                int placeId = scanner.nextInt();
                boolean placeExists = false;
                for(ParkingPlace parkingPlace : est.parkingPlaces){
                    if( parkingPlace.id == placeId){
                        placeExists = true;
                        Person tenant = parkingPlace.tenant;
                        try {
                           tenant.cancelParkingRent(parkingPlace);
                        } catch (NotBelongException e) {
                            e.printStackTrace();
                        }
                    }
                } if(! placeExists){
                    System.out.println("Invalid parking place Id");
                }

            }
        } if(!estateFound){
            System.out.println("Invalid estate id");
        }
    }

    public static void renewParkingRent(int estId){
        boolean estateFound = false;
        for (HousingEstate est : housingEstates) {
            if (est.id == estId) {
                estateFound = true;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter a parking place from the list of places which rent has expired: ");
                for(ParkingPlace parkingPlace : est.parkingPlaces) {
                    if (parkingPlace.tenant != null) {
                        for (TenantLetter letter : parkingPlace.tenant.tenantLetters) {
                            if (letter.getParkingPlace() == parkingPlace) {
                                System.out.println(parkingPlace.id + " - tenant " + parkingPlace.tenant.name + " "
                                        + parkingPlace.tenant.surname);
                            }
                        }
                    }
                }
                int parkingPlaceId = scanner.nextInt();
                scanner.nextLine();
                boolean placeExists = false;
                for(ParkingPlace parkingPlace : est.parkingPlaces){
                    if(parkingPlace.id == parkingPlaceId){
                        placeExists = true;
                        Person tenant = parkingPlace.tenant;
                        System.out.println("Enter the Date (in form of dd.MM.yyyy):");
                        String dateStr = scanner.nextLine();
                        SimpleDateFormat dateFormat = new SimpleDateFormat(("dd.MM.yyyy"));
                        Date date = null;
                        try {
                            date = dateFormat.parse(dateStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        tenant.renewParkingRent(parkingPlace, date);
                    }
                } if(! placeExists){
                    System.out.println("Invalid parking place Id");
                }

            }
        } if(!estateFound){
            System.out.println("Invalid estate id");
        }
    }
}