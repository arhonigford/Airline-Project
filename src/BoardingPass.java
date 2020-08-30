import java.io.Serializable;

public class BoardingPass implements Serializable {
    private Passenger passenger;
    private Airline airline;
    private Gate gate;

    public BoardingPass(Passenger passenger, Airline airline, Gate gate) {
        this.passenger = passenger;
        this.gate = gate;
        this.airline = airline;
    }

    public String getPassenger() {
        return passenger.toString();
    }

    public Airline getAirline() {
        return airline;
    }

    public Gate getGate() {
        return gate;
    }

    @Override
    public String toString() {
        String s = "Boarding Pass[ " + airline.getName() + ", " + passenger.getFirstName()
                + ", " + passenger.getFirstName() + ", " + passenger.getAge() + ", " + gate + "]";
        return s;
    }
}
