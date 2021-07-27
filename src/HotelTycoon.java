import java.util.*;

public class HotelTycoon {

    static String[] options = new String[] {
            "(1) Spiel Starten",
            "(2) Spieler hinzufügen",
            "(3) Spieler entfernen",
            "(4) Einstellungen",
            "(5) Beenden" };
    static PlayerManager PM = new PlayerManager();
    static Settings S = new Settings();
    static Vars V = new Vars();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean run = true;

        while (run) {

            if (PM.getSize() < 2) {

                action(2);

            } else {

                S.listPlayers(PM);

                for (int i = 0; i < options.length; i++) {

                    System.out.println(options[i]);

                }
                System.out.println();

                // Aktion
                int choice = 0;
                while (choice < 1 || choice > 5) {

                    choice = S.letItBeInteger("Option");

                }

                if (choice == 5) {

                    // Spiel vorbei
                    System.out.print("Spiel beendet");
                    run = false;

                } else {

                    action(choice);

                }

            }

        }

    }

    public static void action(int c) {

        Scanner sc = new Scanner(System.in);
        Player p;

        switch (c) {

            case 1: // Spielstart

                if (PM.getSize() >= 2) {

                    Game G = new Game(S, PM, V);
                    G.start();

                }

                break;

            case 2: // Spieler hinzufügen

                // Konsole leeren
                V.getEscape();

                System.out.print(V.getTitle() + "\nSpielernamen eingeben: ");
                p = new Player(sc.nextLine(), S);
                PM.addPlayer(p);

                break;

            case 3: // Spieler löschen

                S.listPlayers(PM);

                // Dann geh raus ins Menü
                if (PM.getSize() == 0) {

                    // Künstliche Pause
                    S.sleep(5);

                    break;

                }

                int deletedPlayer = 0;
                while (deletedPlayer < 1 || deletedPlayer > PM.getSize()) {

                    deletedPlayer = S.letItBeInteger("Spieler-ID eingeben");

                }

                PM.removePlayer(deletedPlayer);

                break;

            case 4: // Einstellungen

                S.main();
                break;

            case 5:
            default:

                break;

        }

    }

}
