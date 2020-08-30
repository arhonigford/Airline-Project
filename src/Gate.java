import java.io.Serializable;
import java.util.Random;

public class Gate implements Serializable {

    private char gateLetter;
    private int gateNum;

    public Gate() {
        Random r = new Random();
        gateNum = r.nextInt(19);
        int n = r.nextInt(3);
        switch (n) {
            case 0:
                gateLetter = 'A';
                break;
            case 1:
                gateLetter = 'B';
                break;
            case 2:
                gateLetter = 'C';
                break;
        }
    }

    public Gate(int gateNum, char gateLetter) {
        this.gateLetter = gateLetter;
        this.gateNum = gateNum;
    }

    public char getGateLetter() {
        return gateLetter;
    }

    public int getGateNum() {
        return gateNum;
    }

    public void setGateLetter(char gateLetter) {
        this.gateLetter = gateLetter;
    }

    public void setGateNum(int gateNum) {
        this.gateNum = gateNum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Gate) {
            Gate g = (Gate) obj;
            return this.gateNum == g.getGateNum() && this.gateLetter == g.getGateLetter();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "" + gateLetter + "" + gateNum;
    }
}
