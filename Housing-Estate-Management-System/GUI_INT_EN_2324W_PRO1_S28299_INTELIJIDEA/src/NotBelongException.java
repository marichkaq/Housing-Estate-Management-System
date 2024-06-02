public class NotBelongException extends Exception{
    Person person;

    public NotBelongException(Person person) {
        super("The request can not be completed, " + person.name + " " + person.surname + " does not rent this object.");
        this.person = person;
    }
}
