package utils;

/**
 * A class that implements methods for easy Python-like data output into the console.
 * <p>Also, the {@link Decorations} subclass provides methods for console text decoration.
 * <p>Text decorating doesn't work in Windows CMD, so <b>enableFormatting</b> flag must be set to false when building
 * command-line apps.
 *
 * @author Dmytro Popov
 */
@SuppressWarnings("unused")
public final class Console {
    /**
     * A flag for disabling text formatting. May be needed if the application is run from the CMD,
     * that doesn't support text formatting.
     */
    public static boolean enableFormatting = true;

    public static abstract class Decorations {
        /**
         * Returns `text` with the `code` string placed before the text and ANSI_RESET symbol placed at the end of the
         * text.
         *
         * @param text a string to be encased with decoration code
         * @param code a decoration code
         * @return encased string
         */
        private static String encase(String text, String code) {
            return (enableFormatting) ? (code + text + Decorations.ANSI_RESET) : text;
        }

        /* Decoration functions */

        public static String red(String text) {
            return encase(text, ANSI_RED);
        }

        public static String green(String text) {
            return encase(text, ANSI_GREEN);
        }

        public static String yellow(String text) {
            return encase(text, ANSI_YELLOW);
        }

        public static String blue(String text) {
            return encase(text, ANSI_BLUE);
        }

        public static String magenta(String text) {
            return encase(text, ANSI_MAGENTA);
        }

        public static String cyan(String text) {
            return encase(text, ANSI_CYAN);
        }

        public static String white(String text) {
            return encase(text, ANSI_WHITE);
        }

        public static String bold(String text) {
            return encase(text, ANSI_BOLD);
        }

        public static String underline(String text) {
            return encase(text, ANSI_UNDERLINE);
        }

        public static String inverted(String text) {
            return encase(text, ANSI_INVERTED);
        }

        /* Decoration codes */
        private static final String ESC = "\u001b[";

        private static String esc(int decorationCode) {
            return ESC + decorationCode + "m";
        }

        public static final String
                ANSI_RED = esc(31), ANSI_RED_BG = esc(41),
                ANSI_GREEN = esc(32), ANSI_GREEN_BG = esc(42),
                ANSI_YELLOW = esc(33), ANSI_YELLOW_BG = esc(43),
                ANSI_BLUE = esc(34), ANSI_BLUE_BG = esc(44),
                ANSI_MAGENTA = esc(35), ANSI_MAGENTA_BG = esc(45),
                ANSI_CYAN = esc(36), ANSI_CYAN_BG = esc(46),
                ANSI_WHITE = esc(37), ANSI_WHITE_BG = esc(47),
                ANSI_BOLD = esc(1),
                ANSI_UNDERLINE = esc(4),
                ANSI_INVERTED = esc(7),
                ANSI_RESET = esc(0);
    }

    /**
     * A shortcut for System.out.println()
     *
     * @param text {@link String}
     */
    public static void println(String text) {
        System.out.println(text);
    }

    /**
     * Prints all the given objects into the console using {@link StringBuilder}
     *
     * @param arr an array of objects
     */
    public static void println(Object... arr) {
        StringBuilder sb = new StringBuilder();

        for (Object text : arr) {
            sb.append(text.toString()).append(' ');
        }

        System.out.println(sb);
    }

    public static void println(boolean bool) {
        println(String.valueOf(bool));
    }

    /**
     * Prints a two-dimensional array arr in the form of a matrix.
     *
     * @param <T> a type of the array
     * @param arr a two-dimensional array
     */
    public static <T> void printTwoDimArray(T[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (T[] row : arr) {
            sb.append("[");
            for (T elem : row) {
                sb.append(" ").append(elem);
            }
            sb.append(" ]\n");
        }
        println(sb.toString());
    }

    /**
     * Prints the contents of the array with their indexes in the format of
     * <p>
     * <code>{<br>
     * &nbsp;"index : String representation of the object"<br>
     * &nbsp;...<br>
     * }</code>
     *
     * @param arr an array is to be printed
     */
    public static <T> void printEnumeratedArray(T[] arr) {
        printEnumeratedArray("", arr);
    }

    /**
     * Prints the contents of the array with their indexes with the label in the format of
     * <p>
     * LABEL<br>
     * <code>{<br>
     * &nbsp;"index : String representation of the object"<br>
     * &nbsp;...<br>
     * }</code>
     *
     * @param arr an array is to be printed
     */
    public static <T> void printEnumeratedArray(String label, T[] arr) {
        if (label.length() > 0) println(label);
        println("{");
        for (int i = 0; i < arr.length; i++)
            println("", String.valueOf(i), ":", ((arr[i] != null) ? arr[i].toString() : "null"));
        println("}");
    }

    /**
     * Decorates the String text with color codes colorCodes.
     * Adds ANSI_RESET at the end of the String.
     * <br>This will return the original String if the <b>enableFormatting</b> flag is set to false.
     * <br><br><i>Some of the decoration codes can be found at {@link Decorations} subclass.</i>
     *
     * @param text       a String is to be decorated
     * @param colorCodes a decoration code/codes
     * @return String
     */
    public static String formatText(String text, String... colorCodes) {
        if (!enableFormatting) return text;

        StringBuilder sb = new StringBuilder();
        for (String code : colorCodes) sb.append(code);
        sb.append(text).append(Decorations.ANSI_RESET);
        return sb.toString();
    }
}