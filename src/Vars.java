import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Vars {

    // private String escape = "\033[H\033[2J";
    private String title = "******* Hotel Tycoon *******";
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_BLUE = "\u001B[34m";
    private final String ANSI_PURPLE = "\u001B[35m";
    private final String ANSI_CYAN = "\u001B[36m";
    private final String ANSI_WHITE = "\u001B[37m";
    private final String format = "%-4s";
    private final String formatTwo = "%-8s%-16s%-8s";
    //private final String SpielfeldFeld = "\u25A2";
    private String formatString = null;
    private final String SpielfeldFeld = "\u2616"; // "\u2302";
    private final String SpielfeldFeldSymbol = "\uD83C\uDFDA";
    private final String PlayerSymbol = "\u25C9";
    private final String EreignisFeldSymbol = "\u2B12"; // "\u25EE";
    private final String hutSymbol = "\uD83C\uDFA9";
    private final String ActivePlayerPointer = "\uFF1C"; // "\u25C4\u25AC"; // "\u25C0\u25AC";
    // private final String SpielfeldHotel = "\u2302";
    private final String[] SpielfeldHotel = new String[]{"\uD83C\uDFE0", "\uD83C\uDFE1", "\uD83C\uDFE8", "\uD83C\uDFE9"};

    public String[] array = new String[]{ANSI_RED, ANSI_BLUE, ANSI_YELLOW, ANSI_GREEN, ANSI_PURPLE, ANSI_CYAN};

    public Vars() {
    }

    public String setFormat(Settings S) {

        for (int i = 1; i < S.getSpielFeldLaenge(); i++) {

            formatString = formatString + "%-8s";

        }

        return formatString;

    }

    public String getFormat() {

        return format;

    }

    public String getFormatTwo() {

        return formatTwo;

    }

    public String getSpielfeldFeld() {

        // return (" " + convertFromUtf8ToIso(SpielfeldFeld) + " ");
        return (" " + SpielfeldFeld + " ");

    }

    public String getSpielfeldFeldSymbol() {

        return SpielfeldFeldSymbol;

    }

    public String getPlayerSymbol() {

        // return (" " + convertFromUtf8ToIso(PlayerSymbol) + " ");
        return (" " + PlayerSymbol + " ");

    }

    public String getEreignisFeldSymbol() {

        // return (" " + convertFromUtf8ToIso(EreignisFeldSymbol) + " ");
        return (" " + EreignisFeldSymbol + " ");

    }

    public String getHut() {

        return hutSymbol;

    }

    public String getActivePlayerPointer() {

        return (" " + ActivePlayerPointer);

    }

    /* private static String convertFromUtf8ToIso(String s1) {

        if(s1 == null) {

            return null;

        }

        String s = new String(s1.getBytes(StandardCharsets.UTF_8));
        byte[] b = s.getBytes(StandardCharsets.ISO_8859_1);

        return new String(b, StandardCharsets.ISO_8859_1);

    } */

    public void getEscape() {

        try
        {
            final String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win"))
            {
                //System.out.println("win");
                Runtime.getRuntime().exec("cls");

                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            }
            else
            {
                //System.out.println("linux/max");
                Runtime.getRuntime().exec("clear");
                //return this.escape;

                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }

    }

    public String getTitle() {

        return this.title;

    }

    public String getAllColors(int n) {

        return array[n];

    }

    public String getANSI_RESET() {

        return ANSI_RESET;

    }

    public String getANSI_RED() {

        return ANSI_RED;

    }

    public String getANSI_BLUE() {

        return ANSI_BLUE;

    }

    public String getANSI_CYAN() {

        return ANSI_CYAN;

    }

    public String getANSI_GREEN() {

        return ANSI_GREEN;

    }

    public String getANSI_PURPLE() {

        return ANSI_PURPLE;

    }

    public String getANSI_WHITE() {

        return ANSI_WHITE;

    }

    public String getANSI_YELLOW() {

        return ANSI_YELLOW;

    }

}
