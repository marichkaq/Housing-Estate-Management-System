public class Item {
    protected String name;
    protected double volume;
    protected int id;
    protected static int counter = 1;

    public Item(String name, double length, double width, double height){
        this.name = name;
        this.volume = length * width * height;
        this.id = counter++;
    }
}
