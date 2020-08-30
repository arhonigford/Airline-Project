import java.io.Serializable;
import java.util.ArrayList;

public interface Airline extends Serializable {

    String getInfo();

    //not so sure about this
    abstract String getName();

    abstract int getTotal();

    abstract int getPassNum();

    abstract void setTotal();

    abstract void setPassNum();

    abstract void setName(String name);

    abstract String getOffers();

    abstract void setOffers(String offers);


    abstract Passenger getPassenger(int i);

    abstract ArrayList<Passenger> getPassengers();

    abstract int getSeatsLeft();

    abstract void addPassenger(Passenger p);

    abstract String toString();

    abstract boolean equals();

    abstract String getPassengerString();

}
