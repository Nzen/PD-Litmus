
package nzen.petrol;

/**
 * @author Nzen
 * A simple struct to say whether this token needs color or not
 */
public class TermToken {
    String token;
    boolean isTerminal;

    public TermToken( String chit, boolean termitude ) {
        token = chit;
        isTerminal = termitude;
    }
}
