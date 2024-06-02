public class Boat extends Vehicle{

    protected String boatType;

    public Boat(String name, double length, double width, double height, double engineCapacity, String engineType,
                String boatType) {
        super(name, length, width, height, engineCapacity, engineType);
        this.boatType = boatType;
    }
}
