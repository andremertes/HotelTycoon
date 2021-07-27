import java.nio.charset.Charset;
import java.util.Random;

public class Hotel {

    private Player owner;
    private int ausbaustufe;
    private int[] mietpreis = new int[]{250, 500, 750, 1000};
    private String name;

    // Konstruktor
    public Hotel(Player kaeufer) {

        this.owner = kaeufer;
        this.ausbaustufe = 0;

        // Random Name
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        this.name = generatedString;

    }

    public void ausbauen() {

        ausbaustufe++;

    }

    public boolean istNichtVollstaendigAusgebaut() {

        if (this.ausbaustufe < mietpreis.length-1) {

            return true;

        }

        return false;

    }

    public float getMietpreis() {

        return mietpreis[this.ausbaustufe];

    }

    public int getAusbaustufe() {

        return ausbaustufe;

    }

    public Player getOwner() {

        return owner;

    }

    public String getName() {

        return name;

    }

}
