import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Settings {

    private String[] optionen;

    private int spielFeldLaenge = 25;   // 2
    private int anzahlEreignisfelder = 5;   // 3
    private float startKontostand = 2000.F; //4
    private int hypothekZinsen = 10;    // 5
    private float hypothekHoehe = 2000.F;   // 6
    private int maxHypothekAnzahl = 3;  // 7
    private float hotelKaufKosten = 1000.F; // 8
    private int gamemode = 1;   // 1, 1 = Last Man Standing, 2 = Rundenbasiert
    private int rounds = 15;    // 1
    private int diceFaces = 6;  // 9
    private Vars V = new Vars();
    PlayerManager PM;

    public Settings() {

        this.optionen = new String[] {
                "Spielmodus",
                "Spielfeldlänge",
                "Anzahl an Ereignisfeldern",
                "Startkontostand",
                "Hypothekszinsen",
                "Höhe einer Hypothek",
                "Maximale Anzahl an Hypotheken pro Spieler",
                "Hotelkaufkosten",
                "Anzahl der Augen beim Würfel",
                "Zurück zum Menü"
        };

    }

    public void main() {

        boolean isInSettings = true;

        while (isInSettings) {

            int n = 0;

            System.out.println("******* Einstellungen *******\n");

            for (int i = 0; i < this.optionen.length; i++) {

                System.out.println("(" + (i+1) + ") " + optionen[i]);

            }
            System.out.println();

            while (n < 1 || n > optionen.length) {

                n = letItBeInteger("Option");

            }

            action(n);

            if (n == optionen.length) {
                isInSettings = false;
            }

        }

    }

    public void listPlayers(PlayerManager setPM) {

        PM = setPM;

        // Konsole leeren
        V.getEscape();
        System.out.println(V.getTitle() + "\n\n============================");

        if (PM.getSize() != 0) {

            // Ausgabe aller Spieler
            for (int i = 0; i < PM.getSize(); i++) {

                System.out.println("(" + (i+1) + ") " + PM.getPlayers(i).getName());

            }

        } else {

            System.out.println("Noch keine Spieler!");

        }

        System.out.print("============================\nGamemode: ");
        System.out.print(printGamemode());
        if (getGamemode() == 2) {

            System.out.print(" -> " + getRounds());

        }
        System.out.println("\n");

    }

    private void action(int c) {

        switch (c) {

            case 1: // Spielmodus ändern

                setGamemode();
                break;

            case 2: // Spielfeldlänge

                setSpielFeldLaenge();
                break;

            case 3: // Anzah Ereignisfelder

                setAnzahlEreignisfelder();
                break;

            case 4: // Startkontostand

                setStartKontostand();
                break;

            case 5: // Hypothekszinsen

                setHypothekZinsen();
                break;

            case 6: // Hypothek Höhe

                setHypothekHoehe();
                break;

            case 7: // Maximale Anzahl an Hypotheken

                setMaxHypothekAnzahl();
                break;

            case 8: // Hotelkaufkosten

                setHotelKaufKosten();
                break;

            case 9: // Würfel Augenzahl

                setDiceFaces();
                break;

            default:
                break;

        }

    }

    public int letItBeInteger(String message) {

        int returns = 0;

        String uChoice = null;
        boolean isStupidUser = true;

        while (isStupidUser) {

            try {

                System.out.print(message + ": ");
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                uChoice = input.readLine();
                Integer.parseInt(uChoice);
                isStupidUser = false;

            } catch (Exception e) { }

        }

        returns = Integer.parseInt(uChoice);

        return returns;

    }

    public void sleep(int time) {

        for (int r = time; r > 0; r--) {

            System.out.println("Weiter in " + r + "s... ");

            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {}

        }

    }

    private void setSpielFeldLaenge() {

        System.out.println("Aktuelle Spielfeldlänge: " + getSpielFeldLaenge());

        int n = letItBeInteger("Neue Spielfeldlänge eingeben");
        spielFeldLaenge = n;

    }

    private void setAnzahlEreignisfelder() {

        System.out.println("Aktuelle Anzahl an Ereignisfeldern: " + getAnzahlEreignisfelder());

        int n = letItBeInteger("Neue Anzahl an Ereignisfeldern eingeben");
        anzahlEreignisfelder = n;

    }

    private void setStartKontostand() {

        System.out.println("Aktuelles Startkapital: " + getStartKontostand());

        int n = letItBeInteger("Neues Startkapital eingeben");
        startKontostand = n;

    }

    private void setHypothekZinsen() {

        System.out.println("Aktueller Hypothekszinssatz (in %): " + getHypothekZinsen());

        int n = letItBeInteger("Neuen Hypothekszinssatz eingeben (in %)");
        hypothekZinsen = n;

    }

    private void setHypothekHoehe() {

        System.out.println("Aktuelle Höhe einer Hypothek: " + getHypothekHoehe());

        int n = letItBeInteger("Neue Höhe einer Hypothek eingeben");
        this.hypothekHoehe = n;

    }

    private void setMaxHypothekAnzahl() {

        System.out.println("Maximale Hypotheken pro Spieler: " + getMaxHypothekAnzahl());

        int n = letItBeInteger("Neue Anzahl an Hypotheken pro Spieler eingeben");
        maxHypothekAnzahl = n;

    }

    private void setHotelKaufKosten() {

        System.out.println("Aktueller Preis für ein Hotel: " + getHotelKaufKosten());

        int n = letItBeInteger("Neuen Preis für ein Hotel eingeben");
        hotelKaufKosten = n;

    }

    private void setGamemode() {

        int gmc = 0;

        while (gmc < 1 || gmc > 2) {

            gmc = letItBeInteger("(1) Last Man Standing, (2) Rundenbasiert\nSpielmodus wählen");

        }

        if (gmc == 1 || gmc == 2) {

            this.gamemode = gmc;

        } else {

            System.out.println("(1) Last Man Standing, (2) Rundenbasiert");

        }

        if (this.gamemode == 2) {

            setRounds(letItBeInteger("Runden wählen"));

        }

    }

    private void setDiceFaces() {

        System.out.println("Aktuelle Augenzahl des Würfels: " + getDiceFaces());

        int n = letItBeInteger("Neue Augenzahl des Würfels eingeben");
        diceFaces = n;

    }

    private void setRounds(int n) {

        if (n > 0) {

            this.rounds = n;

        }

    }

    public void setPlayer(PlayerManager PM, Vars V) {

        int[] colour = new int[V.array.length];   // Count

        // Iterieren aller Spieler
        for (int i = 0; i < PM.getSize(); i++) {

            int minA = 0;
            int minIndex = 0;

            for (int j = 0; j < (V.array.length-1); j++) {

                minIndex = Math.min(colour[minA],colour[j+1]);
                minA = minIndex;

            }

            int k = 0;
            for (; k < (V.array.length-1); ) {

                if (colour[k] != minIndex) {

                    k++;

                } else {

                    break;

                }

            }

            colour[k]++;

            PM.getPlayers(i).setColor(V.getAllColors(k));
            PM.getPlayers(i).setKontostand(this.getStartKontostand());
            PM.getPlayers(i).setingameStatus(true);

        }

    }

    public int getSpielFeldLaenge() {

        return this.spielFeldLaenge;

    }

    public int getAnzahlEreignisfelder() {

        return anzahlEreignisfelder;

    }

    public float getStartKontostand() {

        return startKontostand;

    }

    public int getHypothekZinsen() {

        return hypothekZinsen;

    }

    public float getHypothekHoehe() {

        return hypothekHoehe;

    }

    public int getMaxHypothekAnzahl() {

        return maxHypothekAnzahl;

    }

    public float getHotelKaufKosten() {

        return hotelKaufKosten;

    }

    public String printGamemode() {

        if (this.gamemode == 1) {

            return "Last Man Standing";

        } else {

            return "Runden";

        }

    }

    public int getGamemode() {

        return gamemode;

    }

    public int getRounds() {

        return this.rounds;

    }

    public int getDiceFaces() {

        return diceFaces;

    }

}
