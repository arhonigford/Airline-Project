import java.io.IOException;

public final class ResponseListener {
    /**
     * Creates a {@code CountdownServer} instance, then begins to serve clients.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReservationServer server;

        try {
            server = new ReservationServer();
        } catch (IOException e) {
            e.printStackTrace();

            return;
        } //end try catch

        server.serveClients();
    } //main


}
