public abstract class Vehicle extends Item{
    protected double engineCapacity;
    //protected Vehicle vehicleType;
    protected String engineType;


    public Vehicle(String name, double length, double width, double height, double engineCapacity,
                   String engineType) {
        super(name, length, width, height);
        this.engineCapacity = engineCapacity;
        this.engineType = engineType;
    }
}
