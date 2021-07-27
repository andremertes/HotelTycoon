import java.nio.charset.Charset;
import java.util.Random;

public class Player {

    private Settings S;

    private String name;
    private float kontostand = 2000;
    private float schulden = 0;
    private int hypothekanzahl = 0;
    private int position = 0;
    private boolean isInGame;
    private String color;

    // Konstruktor
    public Player(String spielerNamen, Settings setS) {

        this.S = setS;
        setName(spielerNamen);
        setKontostand(S.getStartKontostand());
        setingameStatus(true);

    }

    // Individuelle Einstellungen setzen
    public String getName() {

        return name;

    }

    public void setKontostand(float neuerKontostand) {

        this.kontostand = neuerKontostand;

    }

    public void setSchulden(float neueSchulden) {

        this.schulden = neueSchulden;

    }

    public void setName(String spielerName) {

        this.name = spielerName;

    }

    public void setHypothekanzahl(int n) {

        this.hypothekanzahl = n;

    }

    public void setPosition(int n) {

        this.position = n;

    }

    public void setingameStatus(boolean status) {

        this.isInGame = status;

    }

    public void setColor(String c) {

        this.color = c;

    }

    public float getKontostand() {

        return kontostand;

    }

    public float getSchulden() {

        return schulden;

    }

    public int getHypothekAnzahl() {

        return hypothekanzahl;

    }

    public int getPosition() {

        return position;

    }

    public boolean getIngameStatus() {

        return isInGame;

    }

    public String getColor() {

        return this.color;

    }

    public boolean hasActiveHypothek() {

        if (this.schulden != 0) {

            return true;

        }

        return false;

    }

}
