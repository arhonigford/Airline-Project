import java.util.ArrayList;

public class Alaska implements Airline {
    private ArrayList<Passenger> passengers;
    private  String name;
    private String info;
    private String offers;
    private String endingMessage;


    public Alaska (ArrayList<Passenger> p) {
        info = "Alaska Airlines is proud to serve the strong and knowledgeable Boilermakers at Purdue University";
        offers = "We primarily fly westward, and often have stops in Alaska and California. \n" +
                "We provide fun snacks, such as pretzels and goldfish. \n " +
                "We also have comfortable seats, and free WIFI.";
        endingMessage = "We hope you choose Alaska Airlines for your next Itinerary!";
        this.passengers = p;

    }
    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public String getName() {
        return "Alaska";
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

    public String getEndingMessage() {
        return endingMessage;
    }

    @Override
    public Passenger getPassenger(int i) {
        return null;
    }

    @Override
    public ArrayList<Passenger> getPassengers() {
        return null;
    }

    @Override
    public int getSeatsLeft() {
        return 150 - passengers.size();

    }


    @Override
    public void addPassenger(Passenger p) {

    }

    @Override
    public boolean equals() {
        return false;
    }

    @Override
    public String getPassengerString() {
        return "put stuff here tho";
    }


}
