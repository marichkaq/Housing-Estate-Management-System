public class OffroadCar extends Vehicle{

    protected double wheelSize;

    public OffroadCar(String name, double length, double width, double height, double engineCapacity, String engineType,
                      double wheelSize) {
        super(name, length, width, height, engineCapacity, engineType);
        this.wheelSize = wheelSize;
    }
}
