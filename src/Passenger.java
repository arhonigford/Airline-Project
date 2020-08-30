import java.io.Serializable;

public class Passenger implements Serializable {

    private String firstName;
    private String lastName;
    private int age;

    public Passenger(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return Character.toUpperCase(firstName.charAt(0)) + ". " + lastName.toUpperCase() + ", " + age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Passenger) {
            Passenger p = (Passenger) obj;
            return this.firstName.toUpperCase().equals(p.getFirstName().toUpperCase()) && this.lastName.toUpperCase().equals(p.getLastName().toUpperCase()) && this.age == p.getAge();
        } else {
            return false;
        }
    }
}
