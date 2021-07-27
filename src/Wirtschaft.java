import java.util.Scanner;

public class Wirtschaft {

    private Settings S;
    private PlayerManager PM;
    private HotelManager HM;
    private Spielfeld F;
    private Player P;
    private Vars V;
    private Bank B;

    public Wirtschaft(Settings setS, PlayerManager setPM, HotelManager setHM, Spielfeld setF) {

        this.S = setS;
        this.PM = setPM;
        this.HM = setHM;
        this.F = setF;
        this.V = new Vars();
        this.B = new Bank(S, PM, V);

    }

    public void actions(int i) {

        P = PM.getPlayers(i);

        if (F.getFArt(P.getPosition()) == null) {   // Kaufen

            if (P.getKontostand() >= S.getHotelKaufKosten()) {

                if (isYes("Möchtest Du das Feld für " + S.getHotelKaufKosten() + " kaufen?")) {

                    kauf();

                }

            } else if (P.getHypothekAnzahl() < S.getMaxHypothekAnzahl() && (P.getKontostand()+S.getHypothekHoehe()) >= S.getHotelKaufKosten()) {

                if (isYes("Das Feld kostet " + S.getHotelKaufKosten() + ". Möchtest Du es Dir mit einem Kredit von " + S.getHypothekHoehe() + " finanzieren?")) {

                    B.newSchulden(P);
                    kauf();

                }

            } else {

                System.out.println("Du kannst Dir das Feld nicht leisten! ");

            }

        } else if (HM.getHotels(P.getPosition()).getOwner() != P) {    // Miete zahlen

            int hotelMapIndex = P.getPosition();

            // Mietempfänger ermitteln
            Player PZ = HM.getHotels(hotelMapIndex).getOwner();

            // Hotel ermitteln
            int HO = P.getPosition();

            if (P != PZ) {

                    if (P.getKontostand() >= HM.getHotels(HO).getMietpreis()) {

                        miete(PZ, HO);
                        System.out.println("Das Feld gehört " + PZ.getName() + ". Die Miete kostet " + HM.getHotels(HO).getMietpreis());


                    } else if (P.getHypothekAnzahl() < S.getMaxHypothekAnzahl() && (P.getKontostand() + S.getHypothekHoehe()) >= HM.getHotels(HO).getMietpreis()) {

                        System.out.println("Das Feld gehört " + PZ.getName() + ". Die Miete kostet " + HM.getHotels(HO).getMietpreis() + ". Du musstest eine Hypothek von " + S.getHypothekHoehe() + " aufnehmen!");
                        B.newSchulden(P);

                    } else {

                        // Game Over
                        System.out.println("Du kannst Dir die Miete nicht mehr leisten. Game Over! ");
                        for (int g = 0; g < S.getSpielFeldLaenge(); g++) {

                            if (HM.getHotels(g) != null) {

                                Hotel TDL = HM.getHotels(g);

                                if (TDL.getOwner() == P) {

                                    F.resetFArt(g);
                                    HM.removeHotel(g);

                                }

                            }

                        }
                        P.setingameStatus(false);

                    }

            }

        } else {    // Ausbau des Hotels

            if (HM.getHotels(P.getPosition()).istNichtVollstaendigAusgebaut()) {

                if (P.getKontostand() >= S.getHotelKaufKosten()) {

                    if (isYes("Möchtest Du das Hotel für " + S.getHotelKaufKosten() + " ausbauen?")) {

                        upgrade();

                    }

                } else if (P.getHypothekAnzahl() < S.getMaxHypothekAnzahl() && (P.getKontostand() + S.getHypothekHoehe() >= S.getHotelKaufKosten())) {

                    if (isYes("Du kannst eine Hypothek von " + S.getHypothekHoehe() + "aufnehmen, um das Hotel auszubauen.")) {

                        B.newSchulden(P);
                        upgrade();

                    }

                } else {

                    System.out.println("Du kannst dir keinen Ausbau leisten. ");

                }

            }

        }

    }

    private void kauf() {

        P.setKontostand(P.getKontostand()-S.getHotelKaufKosten());
        Hotel H = new Hotel(P);
        HM.addHotel(P.getPosition(), H);
        F.setFArt(P.getPosition(), H);

    }

    private void miete(Player PZ, int HO) {

        P.setKontostand(P.getKontostand()-HM.getHotels(HO).getMietpreis());
        PZ.setKontostand(PZ.getKontostand()+HM.getHotels(HO).getMietpreis());

    }

    public boolean isYes(String msg) {

        Scanner sc = new Scanner(System.in);

        char choice = 'c';
        while (Character.toLowerCase(choice) != 'y' && Character.toLowerCase(choice) != 'n') {

            System.out.print(msg + " (y/n): ");
            choice = sc.next().charAt(0);

        }

        if (Character.toLowerCase(choice) == 'y') {

            return true;

        }

        return false;

    }

    private void upgrade() {

        Hotel HO = HM.getHotels(P.getPosition());
        HO.ausbauen();
        P.setKontostand(P.getKontostand()-S.getHotelKaufKosten());

    }

}
