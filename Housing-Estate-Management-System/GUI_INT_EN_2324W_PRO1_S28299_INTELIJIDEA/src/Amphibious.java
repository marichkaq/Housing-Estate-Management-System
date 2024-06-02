public class Amphibious extends Vehicle{

    protected double maxWaterSpeed;

    public Amphibious(String name, double length, double width, double height, double engineCapacity, String engineType,
                       double maxWaterSpeed) {
        super(name, length, width, height, engineCapacity, engineType);
        this.maxWaterSpeed = maxWaterSpeed;
    }
}
