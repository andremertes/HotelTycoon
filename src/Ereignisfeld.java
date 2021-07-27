public class Ereignisfeld {

    private int ereignis;
    private Player P;
    private PlayerManager PM;
    private HotelManager HM;
    private Settings S;
    private Spielfeld SP;

    public Ereignisfeld(Settings setS) {

        setEreignis();
        this.S = setS;

    }

    private void setEreignis() {

        this.ereignis = (int) (5*Math.random());

    }

    public void getEreignis(int n, PlayerManager setP, int p, HotelManager setHM, Spielfeld setSP) {

        PM = setP;
        P = PM.getPlayers(p);
        HM = setHM;
        SP = setSP;

        // n = 3; // 2 -> Hotel geschenkt // 3 -> Hotel verloren

        switch (n){

            case 0: // Kontostand +500

                mehrGeldEins();
                break;

            case 1: // Kontostand der anderen +100

                mehrGeldZwei();
                break;

            case 2: // zufaelliges Hotel geschenkt bekommen

                getRandomHotel();
                break;

            case 3: // zufaelliges Hotel verlieren

                looseRandomHotel();
                break;

            case 4: // Keine Schulden mehr. YAY

                thankYouInvestor();
                break;

            case 5:
            default: // -100 Kontostand

                looseMoney();
                break;

        }

    }

    private void mehrGeldEins() {

        P.setKontostand(P.getKontostand()+500);

        System.out.println("Du bekommst 500 geschenkt! ");

    }

    private void mehrGeldZwei() {

        for (int i = 0; i < PM.getSize(); i++) {

            PM.getPlayers(i).setKontostand(PM.getPlayers(i).getKontostand()+100);

        }

        P.setKontostand(P.getKontostand()-100);

        System.out.println("Alle anderen bekommen 100 geschenkt! ");

    }

    private void getRandomHotel() {

        int freiFeldCount = 0;

        for (int i = 0; i < S.getSpielFeldLaenge(); i++) {

            if (SP.getFArt(i) == null) {

                freiFeldCount++;

            }

        }

        if (freiFeldCount != 0) {

            int rnd = (int)(Math.random()*freiFeldCount);

            for (int i = 0; i < S.getSpielFeldLaenge(); i++) {

                if (SP.getFArt(i) == null) {

                    rnd--;

                }

                if (SP.getFArt(i) == null && rnd == 0) {

                    Hotel H = new Hotel(P);
                    HM.addHotel(i, H);
                    SP.setFArt(i, H);

                    System.out.println("Du hast ein Hotel geschenkt bekommen. Bis zum nächsten Zug ist es gebaut! ");

                }

            }

        } else {

            System.out.println("Pech gehabt! ");

        }

    }

    private void looseRandomHotel() {

        int hotAnzahl = 0;

        for (int i = 0; i < S.getSpielFeldLaenge(); i++) {

            if (HM.getHotels(i) != null) {

                if (HM.getHotels(i).getOwner().getName() == P.getName()) {

                    hotAnzahl++;

                }

            }

        }

        if (hotAnzahl > 0) {

            int rnd = (int)(Math.random()*hotAnzahl)+1;

            for (int i = 0; i < S.getSpielFeldLaenge(); i++) {

                if (HM.getHotels(i) != null) {

                    if (HM.getHotels(i).getOwner().getName() == P.getName()) {

                        rnd--;

                    }

                }

                if (rnd == 0) {

                    SP.resetFArt(i);
                    HM.removeHotel(i);

                    break;

                }

            }

            System.out.println("Eins Deiner Hotels ist insolvent! Die Mitarbeiter haben es bis zum nächsten Zug geräumt... ");

        } else {

            System.out.println("Pech gehabt! ");

        }

    }

    private void thankYouInvestor() {

        if (P.getSchulden() > 0) {

            P.setSchulden(0);
            System.out.println("Ein reicher Investor hat Deine Schulden getilgt. YAY! ");

        } else {

            System.out.println("Pech gehabt! ");

        }

    }

    private void looseMoney() {

        if (P.getKontostand() >= 100) {

            P.setKontostand(P.getKontostand()-100);
            System.out.println("Du verlierst 100! ");

        } else {

            System.out.println("Pech gehabt! ");

        }

    }

}
