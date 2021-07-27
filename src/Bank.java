public class Bank {

    private PlayerManager PM;
    private Vars V;
    private Settings S;
    private Player P;

    public Bank(Settings setS, PlayerManager setPM, Vars setV) {

        this.S = setS;
        this.PM = setPM;
        this.V = setV;

    }

    // Hypothek aufnehmen
    public void newSchulden(Player PL) {

        P = PL;

        P.setKontostand(P.getKontostand()+S.getHypothekHoehe());
        P.setSchulden(P.getSchulden()+S.getHypothekHoehe());
        P.setHypothekanzahl(P.getHypothekAnzahl()+1);

    }

    // Hypothek tilgen
    public void tilgen(int n, float s) {

        P = PM.getPlayers(n);

        P.setSchulden(P.getSchulden()-s);
        P.setKontostand(P.getKontostand()-s);

    }

    // Zinsen bezahlen
    public void zinsZahlung(int n) {

        P = PM.getPlayers(n);
        float zinsen = Math.round(((P.getSchulden()/(100/S.getHypothekZinsen())) * Math.pow(10,2))/Math.pow(10,2))+1;

        if (P.getKontostand() >= zinsen) {

            P.setKontostand(P.getKontostand()-zinsen);

        } else {

            if (checkKreditWürdigkeit(P)) {

                newSchulden(P);
                P.setKontostand(P.getKontostand()-zinsen);

            } else {

                P.setingameStatus(false);
                System.out.println("Du bist insolvent. Game Over! ");

            }

        }

    }

    // Hat der Spieler eine laufende Hypothek?
    public boolean hasHypothek(int n) {

        Player P = PM.getPlayers(n);

        if (P.getSchulden() > 0) {

            return true;

        }

        return false;

    }

    // Darf der Spieler noch eine Hypothek aufnehmen?
    public boolean checkKreditWürdigkeit(Player PL) {

        P = PL;

        if (P.getHypothekAnzahl() < S.getMaxHypothekAnzahl()) {

            return true;

        }

        return false;

    }

    public boolean canAffordHypothek(int n) {

        P = PM.getPlayers(n);

        if (P.getKontostand()-(P.getSchulden()/((int)(100/S.getHypothekZinsen()))) < 0) {

            if (P.getHypothekAnzahl() < S.getMaxHypothekAnzahl()) {

                newSchulden(P);
                return true;

            }

            return false;

        }

        return true;

    }

}
