
package nzen.petrol;

import java.util.TreeMap;

/** @author Nzen
 */
public class Litmus {
    /*
     * todo:
    break into non/code blocks
    feed code lang into separate langLexers ; if not dC.amFor( currLang )
    reassemble content
    sanitize html as it comes out of langLexer (so it doesn't kill the spans)
    */
    // not sure if I can just push these around locally, let's see
    // ColorSpace cieLab;
    private LangLexer daisyChain;
    private TreeMap< String, String > colorsUsed;
        // unknown number of tags. maybe daisychain can report after lexing?

    public Litmus() {
        colorsUsed = new TreeMap<>();
        daisyChain = null;
    }

    public java.util.LinkedList<Transmix> stain( java.util.LinkedList<Transmix> toCheck ) {
        int done = 0;
        int eightBit;
        String tempColor;
        String output = "";
        ColorSpace cieLab = new ColorSpace( ColorSpace.beginBlack );
        for ( Transmix wholeFile : toCheck ) {
            // IMPROVE are there multiple blocks in a file?
            daisyChain = new LangLexer( "threeChar" );
            daisyChain.prep( wholeFile.gContent() );
            for ( TermToken symb : daisyChain ) {
                if ( symb.nonTerminal ) {
                    if ( not(colorsUsed.containsKey( symb.token )) ) {
                        tempColor = cieLab.next();
                        colorsUsed.put( symb.token, tempColor );
                    } else {
                        tempColor = colorsUsed.get( symb.token );
                    }
                    // symb.token = htmlSanitize( symb.token )
                    symb.token = coloredSpan( symb.token, tempColor );
                }
                output += symb.token;
                done++;
                eightBit = done & 7; // 4TESTS just breaking up the output
                if ( eightBit == 4 )
                    output += "\n";
                // < 4TESTS
            }
            wholeFile.sContent( output );
        }
        return toCheck;
    }

    private String coloredSpan( String word, String color ) {
        return "<span style=\'color:#"+ color +"\'>"+ word +"</span>";
    }

    private boolean not( boolean whatever ) { return ! whatever; }
}
