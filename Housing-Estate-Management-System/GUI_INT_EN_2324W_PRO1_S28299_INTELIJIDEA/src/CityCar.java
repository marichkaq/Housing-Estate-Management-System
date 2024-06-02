public class CityCar extends Vehicle{

    protected String carBrand;

    public CityCar(String name, double length, double width, double height, double engineCapacity, String engineType,
                   String carBrand) {
        super(name, length, width, height, engineCapacity, engineType);
        this.carBrand = carBrand;
    }
}
