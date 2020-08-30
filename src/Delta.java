import java.util.ArrayList;

public class Delta implements Airline {

    private String name;
    private String info;
    private String offers;
    private ArrayList<Passenger> passengers;
    private String endingMessage;

    public Delta(ArrayList<Passenger> p) {
        name = "Delta";
        info = "Delta Airlines is proud to be one of the five premier Airlines at Purdue University.";
        offers = "We  offer exceptional services, with free limited WiFi for all customers. \n" +
                "Passengers who use T-Mobile as a cell phone carrier get additional benefits.  \n" +
                "We are also happy to offer power outlets in each seat for passenger use.";
        endingMessage = "We hope you choose to fly Delta as your next Airline.";
        this.passengers = p;
    }
    @Override
    public String getInfo() {
        return info;
    }

    public String getEndingMessage(){
        return endingMessage;
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
        return 200 - passengers.size();

    }

    @Override
    public void addPassenger(Passenger p) {
        passengers.add(p);
    }

    @Override
    public boolean equals() {
        return false;
    }

    @Override
    public String getPassengerString() {
        return "put stuff here tho";
    }


    //@Override
    //public String toString() {
        //return info + "\n" + offers + "\n" + endingMessage;
    //}
}
