import java.util.Scanner;

public class Game {

    private Settings S;
    private PlayerManager PM;
    private Vars V;
    private Bank B;
    private Player P;
    private Dice D;
    private HotelManager HM;
    private Hotel H;
    Scanner sc;

    private int round;

    public Game(Settings setS, PlayerManager setPM, Vars setV) {

        this.S = setS;
        this.PM = setPM;
        this.V = setV;
        this.B = new Bank(S, PM, V);
        this.round = 1;

        S.setPlayer(PM, V);
        D = new Dice(S, PM);
        HM = new HotelManager();

    }

    public void start() {

        // Initialisieren
        Spielfeld feld = new Spielfeld(S.getSpielFeldLaenge(), S, PM, HM);
        Wirtschaft W = new Wirtschaft(S, PM, HM, feld);

        // Ziehen der Runden und Züge
        while (isInGame()) {

            for (int i = 0; i < PM.getSize(); i++) {

                P = PM.getPlayers(i);

                if (P.getIngameStatus()) {

                    V.getEscape();
                    V.getTitle();
                    System.out.println();

                    // Spielfeld zeichnen
                    feld.paintFeld(PM.getPlayers(i));

                    // Zinsen einer Hypothek zu zahlen?
                    if (P.hasActiveHypothek()) {

                        // Geht der Spieler beim Zahlen der Zinsen GameOver?
                        if (B.canAffordHypothek(i)) {

                            B.zinsZahlung(i);

                            V.getEscape();
                            V.getTitle();
                            System.out.println();

                            // Spielfeld zeichnen
                            feld.paintFeld(PM.getPlayers(i));

                            System.out.println("Du hast " + S.getHypothekZinsen() + "% Zinsen für Deine Hypothek bezahlt!");

                        } else { // Nein, dann bezahlen und weiter

                            P.setingameStatus(false);

                            // Hotels verlieren
                            for (int g = 0; g < S.getSpielFeldLaenge(); g++) {

                                if (HM.getHotels(g) != null) {

                                    Hotel TDL = HM.getHotels(g);

                                    if (TDL.getOwner() == P) {

                                        feld.resetFArt(g);
                                        HM.removeHotel(g);

                                    }

                                }

                            }

                            System.out.println("Du kannst Die die Zinsen für Deine Hypothek nicht mehr leisten. Game Over!");
                            break;

                        }

                    }

                    // Würfeln
                    int wurf = D.wurf();
                    //if (D.getNumberOfFaces() == 6) {

                    //    coolerWuerfel(wurf);


                     //} else {

                        System.out.print("Du hast eine " + P.getColor() + wurf + V.getANSI_RESET() + " gewürfelt. ");

                     //}

                    int n = S.letItBeInteger("Wie viele Felder möchtest Du ziehen?");

                    // Wie viele Felder ziehen? (Würfelklasse) -> Ziehen der Felder
                    D.zug(i, n, wurf);

                    V.getEscape();
                    V.getTitle();
                    System.out.println();

                    // Spielfeld zeichnen
                    feld.paintFeld(PM.getPlayers(i));

                    // Feldaktion ausführen -> Ereignisfeld oder Hotel?
                    if (feld.getFArt(P.getPosition()) != "Ereignisfeld") {

                        // Normales Feld
                        W.actions(i);

                        V.getEscape();
                        V.getTitle();
                        System.out.println();

                        // Spielfeld zeichnen
                        feld.paintFeld(PM.getPlayers(i));

                    } else {

                        // Ereignisfeld
                        Ereignisfeld E = new Ereignisfeld(S);
                        E.getEreignis(feld.getEreignis(), PM, i, HM, feld);

                    }

                    // Kredit vorhanden? Wenn ja... tilgen?
                    if (B.hasHypothek(i)) {

                        if (W.isYes("Du hältst eine Hypothek. Möchtest Du sie tilgen?")) {

                            float rate = -1;

                            while (!(rate > 0 && rate <= P.getKontostand())) {

                                rate = S.letItBeInteger("Wie viel möchtest Du abbezahlen?");

                            }

                            B.tilgen(i,rate);

                        }

                    }

                }

                // Künstliche Pause am Ende jedes Zuges
                S.sleep(3);

            }

            round++;

        }

        end();

    }

    private void coolerWuerfel(int wurf) {

        String[] wuerfel = new String[] {"\u2680", "\u2681", "\u2682", "\u2683", "\u2684", "\u2685"};

        System.out.print("Du hast gewürfelt: " + wuerfel[wurf-1] + " ");

    }

    private boolean isInGame() {

        if (S.getGamemode() == 1) {

            // Last Man Standing

            int playerCounter = 0;

            for (int i = 0; i < PM.getSize(); i++) {

                if (PM.getPlayers(i).getIngameStatus()) {

                    playerCounter++;

                }

            }

            if (playerCounter >= 2) {

                return true;

            }

        } else {

            // Rundenbasiert

            if (this.round <= S.getRounds()) {

                return true;

            }

        }

        return false;

    }

    // Gewinner küren
    private void end() {

        int gewinnerID = 0;

        for (int i = 1; i < PM.getSize(); i++) {

            if ((PM.getPlayers(gewinnerID).getKontostand() - PM.getPlayers(gewinnerID).getSchulden()) < (PM.getPlayers(i).getKontostand()-PM.getPlayers(i).getSchulden())) {

                gewinnerID = i;

            } else { }
        }

        System.out.println("Gewinner: " + PM.getPlayers(gewinnerID).getName());

        // Künstliche Pause
        S.sleep(10);

    }

}
