import java.util.ArrayList;

public class Southwest implements Airline {

    private String name;
    private String info;
    private String offers;
    private ArrayList<Passenger> passengers;
    private String endingMessage;

    public Southwest(ArrayList<Passenger> p) {
        name = "Southwest";
        info = "Southwest Airlines is proud to offer flights to Purdue University.";
        offers = "We are happy to offer free in flight wifi, as well as our amazing snacks" +
                "\nIn addition, we offer flights for much cheaper than other airlines, " +
                "and offer two free checked bags.";
        endingMessage = "We hope you choose Southwest for your next flight.";
        this.passengers = p;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTotal() {
        return 0;
    }

    @Override
    public int getPassNum() {
        return 0;
    }

    @Override
    public void setTotal() {

    }

    @Override
    public void setPassNum() {

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getOffers() {
        return offers;
    }

    @Override
    public void setOffers(String offers) {
        this.offers = offers;
    }

    @Override
    public Passenger getPassenger(int i) {
        return passengers.get(i);
    }

    @Override
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public int getSeatsLeft() {
        return 100 - passengers.size();
    }

    @Override
    public void addPassenger(Passenger p) {
        passengers.add(p);
    }

    @Override
    public boolean equals() {
        return false;
    }

    public String getEndingMessage() {
        return endingMessage;
    }

    @Override
    public String toString() {
        return info + "\n" + offers + "\n";
    }

    @Override
    public String getPassengerString() {
        return "put stuff here tho";
    }
}
