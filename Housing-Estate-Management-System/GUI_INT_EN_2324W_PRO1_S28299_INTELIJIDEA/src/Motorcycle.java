public class Motorcycle extends Vehicle{

    protected boolean withTrolley;

    public Motorcycle(String name, double length, double width, double height, double engineCapacity, String engineType,
                      boolean withTrolley) {
        super(name, length, width, height, engineCapacity, engineType);
        this.withTrolley = withTrolley;
    }
}
