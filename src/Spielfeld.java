public class Spielfeld {

    private Settings S;
    private PlayerManager PM;
    private Vars V = new Vars();
    private String[] feldInfo; // Speichert Besitz/Typ
    private String[][] brett;
    private Hotel H;
    private Ereignisfeld E;
    private HotelManager HM;
    private Player P;
    private String lol = null;

    public Spielfeld(int n, Settings setS, PlayerManager setPM, HotelManager setHM) {

        this.S = setS;
        this.PM = setPM;
        this.HM = setHM;

        this.feldInfo = new String[n];
        this.brett = new String[n][PM.getSize()];

        for (int i = 0; i < PM.getSize(); i++) {
            for (int j = 0; j < feldInfo.length; j++) {
                brett[j][i] = V.getSpielfeldFeld();
            }
        }

        setEreignisfelder();

        lol = V.setFormat(S);

    }

    public void paintFeld(Player PL) {

        P = PL;

        // Header-Zeile
        header();

        // Feld an sich
        brett();

    }

    private void header() {

        System.out.print("Besitz: ");

        for (int i = 0; i < feldInfo.length; i++) {

            if (feldInfo[i] != "Ereignisfeld") {

                if (HM.getSize() > 0) {

                    // SpielerIndex ermitteln
                    if (HM.getHotels(i) != null) {

                        int j = 1;
                        for ( ; j-1 < PM.getSize(); j++) {

                            if (HM.getHotels(i).getOwner() == PM.getPlayers(j-1)) {

                                System.out.print(" " + j + " ");
                                //System.out.printf(lol,  " " + V.getSpielfeldFeldSymbol());

                            } else { }

                        }

                    } else {

                        System.out.print(" " + "S" + " ");
                        //System.out.printf(V.getFormat(), V.getSpielfeldFeldSymbol());

                    }

                } else {

                    System.out.print(" " + "S" + " ");
                    //System.out.printf(V.getFormat(), V.getSpielfeldFeldSymbol());

                }

            } else {

                //System.out.printf(V.getFormat(), V.getSpielfeldFeldSymbol());
                System.out.print(" E ");

            }

        }

        System.out.print("\nAusbau: ");

        for (int i = 0; i < feldInfo.length; i++) {

            if (feldInfo[i] != "Ereignisfeld") {

                if (HM.getSize() > 0) {

                    if (HM.getHotels(i) != null) {

                        if (HM.getHotels(i).getName() == feldInfo[i]) {

                            System.out.print(" " + HM.getHotels(i).getAusbaustufe() + " ");
                        }

                    } else {

                        System.out.print("   ");
                        //System.out.print(" " + V.getSpielfeldFeldSymbol() + " ");
                        //System.out.printf(V.getFormat(), V.getSpielfeldFeldSymbol());

                    }

                } else {

                    System.out.print("   ");
                    //System.out.print(" " + V.getSpielfeldFeldSymbol() + " ");
                    //System.out.printf(V.getFormat(), V.getSpielfeldFeldSymbol());

                }

            } else {

                System.out.print("   ");
                //System.out.print(" " + V.getHut() + " ");
                //System.out.printf(V.getFormat(), V.getHut());

            }

        }

    }

    private void brett() {

        for (int i = 0; i < PM.getSize(); i++) {

            System.out.print("\n        " + V.getANSI_RESET());

            for (int j = 0; j < feldInfo.length; j++) {

                if (j == PM.getPlayers(i).getPosition()) {

                    System.out.print(PM.getPlayers(i).getColor() + V.getPlayerSymbol() + V.getANSI_RESET());

                } else {

                    if (feldInfo[j] != null && feldInfo[j] != "Ereignisfeld") {

                        System.out.print(HM.getHotels(j).getOwner().getColor());

                    }

                    //System.out.printf(lol, brett[j][i]);
                    System.out.print(brett[j][i]);

                    System.out.print(V.getANSI_RESET());

                }

            }

            System.out.printf(V.getFormatTwo(), PM.getPlayers(i).getColor() + "(" + (i+1) + ") ", PM.getPlayers(i).getName(), " Kontostand: " + PM.getPlayers(i).getKontostand());
            // System.out.print(PM.getPlayers(i).getColor() + " (" + (i+1) + ") " + PM.getPlayers(i).getName() + " Kontostand: " + PM.getPlayers(i).getKontostand());

            if (PM.getPlayers(i).getSchulden() != 0) {

                System.out.print(" Hypothek: " + PM.getPlayers(i).getSchulden());

            }

            if (PM.getPlayers(i) == P) {

                System.out.print(V.getActivePlayerPointer());

            }

        }

        System.out.println(V.getANSI_RESET());

    }

    public String getFArt(int i) {

        return this.feldInfo[i];

    }

    public void setFArt(int i, Hotel H) {

        this.feldInfo[i] = H.getName();

    }

    public void resetFArt(int i) {

        this.feldInfo[i] = null;

    }

    private void setEreignisfelder() {

        int rnd;

        for (int i = 0; i < S.getAnzahlEreignisfelder(); ) {

            rnd = (int)(Math.random()*S.getSpielFeldLaenge());

            if (feldInfo[rnd] != "Ereignisfeld") {

                feldInfo[rnd] = "Ereignisfeld";
                for (int e = 0; e < PM.getSize(); e++) {
                    brett[rnd][e] = V.getEreignisFeldSymbol();
                }
                E = new Ereignisfeld(S);
                i++;

            }

        }

    }

    public int getEreignis() {

        return (int)(Math.random()*5)+1;

    }

}
