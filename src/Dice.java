import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Dice {

    private int seiten;
    private char[] wuerfelSeiten;
    private Settings S;
    private Scanner sc;
    private PlayerManager PM;
    private Player P;
    private Vars V = new Vars();

    // Konstruktor
    public Dice(Settings setS, PlayerManager setPM) {

        this.S = setS;
        this.PM = setPM;
        this.seiten = S.getDiceFaces();
        wuerfelSeiten = new char[seiten];

        for (int i = 1; i-1 < seiten; i++) {

            this.wuerfelSeiten[i-1] = (char)i;

        }

    }

    public int wurf() {

        return (int) (Math.random() * seiten) + 1;

    }

    public void zug(int i, int n, int wurf) {

        P = PM.getPlayers(i);

        while (n > wurf || n < 1) {

            System.out.print("Wie viele Felder möchtest Du ziehen? ");

            String ueEingabe = null;
            boolean userIsEvenMoreStupid = true;
            while (userIsEvenMoreStupid) {

                try {

                    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                    ueEingabe = input.readLine();
                    Integer.parseInt(ueEingabe);
                    userIsEvenMoreStupid = false;

                } catch (Exception e) {

                    System.out.print("Wie viele Felder möchtest Du ziehen? ");

                }

            }

            n = Integer.parseInt(ueEingabe);

        }

        int position = P.getPosition();

        if ((position + n) >= S.getSpielFeldLaenge()) {

            P.setPosition(position + n - S.getSpielFeldLaenge());
            P.setKontostand(P.getKontostand()+S.getStartKontostand());

        } else {

            P.setPosition(position + n);

        }

    }

    /* public boolean isWuerfelSeite(int t) {

        for (int i = 1; i-1 < wuerfelSeiten.length; i++) {

            if (t == wuerfelSeiten[i-1]) {

                return true;

            }

        }

        return false;

    } */

    public int getNumberOfFaces() {

        return seiten;

    }

    public void setNumberOfFaces(int faces) {

        seiten = faces;

    }

}
