import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private File file;
    private BufferedReader br;
    private FileReader fr;
    private ArrayList<String> contents;
    private ArrayList<Passenger> Alaska;
    private ArrayList<Passenger> Delta;
    private ArrayList<Passenger> Southwest;
    private int alaskaEndLine;
    private int alaskaStartLine;
    private int deltaEndLine;
    private int deltaStartLine;
    private int southwestEndLine;
    private int southwestStartLine;
    private int AlaskaPassNum;
    private int AlasktaTotal;
    private int DeltaPassNum;
    private int DeltaTotal;
    private int SouthwestPassNum;
    private int SouthwestTotal;

    public FileManager(File file) throws FileNotFoundException {
        Alaska = new ArrayList<>();
        Delta = new ArrayList<>();
        Southwest = new ArrayList<>();
        this.file = file;
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        FileReader frI = null;
        BufferedReader brI = null;
        try {
            frI = new FileReader(file);
            brI = new BufferedReader(frI);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> r = new ArrayList<String>();
        int x = 1;
        while (true) {
            String line = null;
            try {
                line = brI.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            if (x == getAlaskaStartLine() + 1) {
                AlaskaPassNum = Integer.valueOf(line.substring(0, line.indexOf("/")));
                AlasktaTotal = Integer.valueOf(line.substring(line.indexOf("/") + 1));
            }
            if (x >= getAlaskaStartLine() + 3 && !line.equals("") && x <= getAlaskaEndLine() && !line.equals("---------------------ALASKA")) {
                Passenger p = new Passenger(line.substring(0, 1), line.substring(3, line.indexOf(",")), Integer.valueOf(line.substring(line.indexOf(", ") + 2)));
                Alaska.add(p);
            }
            if (x == getDeltaStartLine() + 1) {
                DeltaPassNum = Integer.valueOf(line.substring(0, line.indexOf("/")));
                DeltaTotal = Integer.valueOf(line.substring(line.indexOf("/") + 1));
            }
            if (x >= getDeltaStartLine() + 3 && !line.equals("") && x <= getDeltaEndLine() && !line.equals("---------------------DELTA")) {
                Passenger p = new Passenger(line.substring(0, 1), line.substring(3, line.indexOf(",")), Integer.valueOf(line.substring(line.indexOf(", ") + 2)));
                Delta.add(p);
            }
            if (x == getSouthwestStartLine() + 1) {
                SouthwestPassNum = Integer.valueOf(line.substring(0, line.indexOf("/")));
                SouthwestTotal = Integer.valueOf(line.substring(line.indexOf("/") + 1));
            }
            if (x >= getSouthwestStartLine() + 3 && !line.equals("") && x <= getSouthwestEndLine() && !line.equals("---------------------SOUTHWEST")) {
                Passenger p = new Passenger(line.substring(0, 1), line.substring(3, line.indexOf(",")), Integer.valueOf(line.substring(line.indexOf(", ") + 2)));
                Southwest.add(p);
            }
            r.add(line);
            x++;
        }
        contents = r;
    }

    public ArrayList<Passenger> getAlaska() {
        return Alaska;
    }

    public ArrayList<Passenger> getDelta() {
        return Delta;
    }

    public ArrayList<Passenger> getSouthwest() {
        return Southwest;
    }

    public int getDeltaStartLine() {
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        deltaStartLine = 0;
        while (true) {
            deltaStartLine++;
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            if (line.equals("DELTA")) {
                break;
            }
        }
        /*try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return deltaStartLine;
    }

    public int getDeltaEndLine() {
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        deltaEndLine = 0;
        String last = "";
        while (true) {
            deltaEndLine++;
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            if (last.equals("---------------------DELTA") && line.equals("")) {
                break;
            }
            last = line;
        }
        /*try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return deltaEndLine;
    }

    public int getAlaskaStartLine() {
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        alaskaStartLine = 0;
        while (true) {
            alaskaStartLine++;
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            if (line.equals("ALASKA")) {
                break;
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alaskaStartLine;
    }

    public int getAlaskaEndLine() {
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        alaskaEndLine = 0;
        String last = "";
        while (true) {
            alaskaEndLine++;
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            if (last.equals("---------------------ALASKA") && line.equals("")) {
                break;
            }
            last = line;
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alaskaEndLine;
    }

    public int getSouthwestStartLine() {
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        southwestStartLine = 0;
        while (true) {
            southwestStartLine++;
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            if (line.equals("SOUTHWEST")) {
                break;
            }
        }
        /*try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return southwestStartLine;
    }

    public int getSouthwestEndLine() {
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        southwestEndLine = 0;
        String last = "";
        while (true) {
            southwestEndLine++;
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            if (last.equals("---------------------SOUTHWEST") && line.equals("")) {
                break;
            }
            last = line;
        }
        /*try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return southwestEndLine;
    }

    public void clearFile() {
        FileWriter fwOb = null;
        try {
            fwOb = new FileWriter(file, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        try {
            fwOb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeAlaskaPassenger(Passenger p) {
        Alaska.add(p);
        int place = getAlaskaEndLine() - 1;
        AlaskaPassNum++;
        clearFile();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fos);
        for (int i = 0; i < contents.size(); i++) {
            if (i == getAlaskaStartLine()) {
                pw.println(getAlaskaPassNum() + "/" + AlasktaTotal);
            }
            if (i == place) {
                pw.println(p);
                pw.println("---------------------ALASKA");
                pw.println("");
            } else if (i == getAlaskaStartLine()) {

            } else {
                pw.println(contents.get(i));
            }
        }
        pw.close();
        updateContents();
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void deleteAlaskaPassenger(Passenger p) {
        for (int i = 0; i < Alaska.size(); i++) {
            if (p.equals(Alaska.get(i))) {
                Alaska.remove(i);
            }
        }
        int start = getAlaskaStartLine();
        int place = getAlaskaEndLine() - 1;
        AlaskaPassNum--;
        clearFile();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fos);
        int dontprint = -1;
        for (int i = 0; i < contents.size(); i++) {
            if (i == start) {
                pw.println(getAlaskaPassNum() + "/" + AlasktaTotal);
            } else if (i == dontprint) {

            } else if (!contents.get(i).equals(p.toString())) {
                pw.println(contents.get(i));

            } else if (contents.get(i).equals(p.toString())) {
                dontprint = i + 1;
            }
        }
        pw.close();
        updateContents();
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void writeDeltaPassenger(Passenger p) {
        Delta.add(p);
        int place = getDeltaEndLine() - 1;
        int start = getDeltaStartLine();
        DeltaPassNum++;
        clearFile();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fos);
        for (int i = 0; i < contents.size(); i++) {
            //System.out.println(getDeltaStartLine());
            if (i == start) {
                pw.println(getDeltaPassNum() + "/" + DeltaTotal);
            }
            if (i == place) {
                pw.println(p);
                pw.println("---------------------DELTA");
                pw.println("");
            } else if (i == start) {

            } else {
                pw.println(contents.get(i));
            }
        }
        pw.close();
        updateContents();
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteDeltaPassenger(Passenger p) {
        for (int i = 0; i < Delta.size(); i++) {
            if (p.equals(Delta.get(i))) {
                Delta.remove(i);
            }
        }
        int start = getDeltaStartLine();
        int place = getDeltaEndLine() - 1;
        DeltaPassNum--;
        int end = getAlaskaEndLine();
        clearFile();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fos);
        int dontprint = -1;
        for (int i = 0; i < contents.size(); i++) {
            if (i == start) {
                pw.println(getDeltaPassNum() + "/" + DeltaTotal);
            } else if (i == dontprint) {

            } else if (!contents.get(i).equals(p.toString()) || i < end) {
                pw.println(contents.get(i));

            } else if (contents.get(i).equals(p.toString())) {
                dontprint = i + 1;
            }
        }
        pw.close();
        updateContents();
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void writeSouthwestPassenger(Passenger p) throws FileNotFoundException {
        Southwest.add(p);
        int place = getSouthwestEndLine() - 1;
        int start = getSouthwestStartLine();
        SouthwestPassNum++;
        clearFile();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fos);
        for (int i = 0; i < contents.size(); i++) {
            if (i == start) {
                pw.println(getSouthwestPassNum() + "/" + SouthwestTotal);
            }
            if (i == place) {
                pw.println(p);
                pw.println("---------------------SOUTHWEST");
                pw.println("");
            } else if (i == start) {

            } else {
                pw.println(contents.get(i));
            }
        }
        pw.close();
        updateContents();
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteSouthwestPassenger(Passenger p) {
        for (int i = 0; i < Southwest.size(); i++) {
            if (p.equals(Southwest.get(i))) {
                Southwest.remove(i);
            }
        }
        int start = getSouthwestStartLine();
        int place = getSouthwestEndLine() - 1;
        int end = getDeltaEndLine();
        SouthwestPassNum--;
        clearFile();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fos);
        int dontprint = -1;
        for (int i = 0; i < contents.size(); i++) {
            if (i == start) {
                pw.println(getSouthwestPassNum() + "/" + SouthwestTotal);
            } else if (i == dontprint) {

            } else if (!contents.get(i).equals(p.toString()) || i < end) {
                pw.println(contents.get(i));

            } else if (contents.get(i).equals(p.toString())) {
                dontprint = i + 1;
            }
        }
        pw.close();
        updateContents();
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public int getAlaskaPassNum() {
        return AlaskaPassNum;
    }

    public int getDeltaPassNum() {
        return DeltaPassNum;
    }

    public int getSouthwestPassNum() {
        return SouthwestPassNum;
    }

    public void updateContents() {
        /*try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> raw = new ArrayList<String>();
        int x = 1;
        String line = null;
        while (true) {

            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            if(x == getAlaskaStartLine()+1){
                line = getAlaskaPassNum() + "/" + AlasktaTotal;
            }
            if(x == getDeltaStartLine()+1){
                line = getDeltaPassNum() + "/" + DeltaTotal;
            }
            if(x == getSouthwestStartLine()+1){
                line = getSouthwestPassNum() + "/" + SouthwestTotal;
            }
            raw.add(line);
            x++;
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        contents = raw;*/
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> raw = new ArrayList<String>();
        while (true) {
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            raw.add(line);
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        contents = raw;
    }

}
