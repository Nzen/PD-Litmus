
/**
 * @author Nzen
 * A simple struct to say whether this token needs color or not
 */

package nzen.petrol;

public class TermToken {
    String token;
    boolean nonTerminal;
    public static final boolean variable = true;
    public static final boolean literal = false;

    public TermToken( String chit, boolean termitude ) {
        token = chit;
        nonTerminal = termitude;
    }
}
