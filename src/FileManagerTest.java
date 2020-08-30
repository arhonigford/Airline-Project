import java.io.*;
import java.util.ArrayList;

//was able to add multiple passengers at once before the constructor for FileManager was changed
//and I tried to update the passenger list
//dont know where I went wrong
//something with an open stream somewhere

public class FileManagerTest {
    public static void main(String[] args){
        try {
            FileManager fm = new FileManager(new File("reservations.txt"));
            /*ArrayList<Passenger> alaskaPassengers = fm.getAlaska();
            for(int i = 0; i<alaskaPassengers.size(); i++){
                System.out.println(alaskaPassengers.get(i));
            }
            ArrayList<Passenger> Passengers = fm.getDelta();
            for(int i = 0; i<Passengers.size(); i++){
                System.out.println(Passengers.get(i));
            }
            Passengers = fm.getSouthwest();
            for(int i = 0; i<Passengers.size(); i++){
                System.out.println(Passengers.get(i));
            }*/
            System.out.println(fm.getAlaskaStartLine() + " and " + fm.getAlaskaEndLine());
            System.out.println(fm.getDeltaStartLine() + " and " + fm.getDeltaEndLine());
            System.out.println(fm.getSouthwestStartLine() + " and " + fm.getSouthwestEndLine());
            Passenger p = new Passenger("Frances", "O'Leary", 18);
            Passenger p1 = new Passenger("Anisha", "Sinha", 18);
            Passenger p2 = new Passenger("Alissa", "Honigford", 18);
            fm.writeAlaskaPassenger(p);
            fm.writeDeltaPassenger(p1);
            fm.writeSouthwestPassenger(p2);
            fm.deleteAlaskaPassenger(new Passenger("A", "Narain", 20));
            fm.deleteDeltaPassenger(new Passenger("K", "ABHYANKAR", 19));
            fm.deleteSouthwestPassenger(new Passenger("C", "Westmeyer", 20));
            System.out.println(fm.getAlaskaStartLine() + " and " + fm.getAlaskaEndLine());
            System.out.println(fm.getDeltaStartLine() + " and " + fm.getDeltaEndLine());
            System.out.println(fm.getSouthwestStartLine() + " and " + fm.getSouthwestEndLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
