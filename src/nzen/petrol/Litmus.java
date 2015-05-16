
package nzen.petrol;

import java.util.TreeMap;

/** @author Nzen
 */
public class Litmus {
    // not sure if I can just push these around locally, let's see
    // ColorSpace cieLab;
    private LangLexer daisyChain;
    private TreeMap< String, String > colorsUsed;
        // unknown number of tags. maybe daisychain can report after lexing?

    public Litmus( String codeLang ) {
        colorsUsed = new TreeMap<>();
        daisyChain = new LangLexer( codeLang );
    }

    public java.util.LinkedList<Transmix> stain( java.util.LinkedList<Transmix> toCheck ) {
        int done = 0;
        int eightBit;
        String tempColor;
        String output = "";
        ColorSpace cieLab = new ColorSpace( ColorSpace.beginBlack );
        for ( Transmix wholeFile : toCheck ) {
            daisyChain.prep( wholeFile.gContent() );
            for ( TermToken symb : daisyChain ) {
                if ( symb.isTerminal ) {
                    if ( not(colorsUsed.containsKey( symb.token )) ) {
                        tempColor = cieLab.next();
                        colorsUsed.put( symb.token, tempColor );
                    } else {
                        tempColor = colorsUsed.get( symb.token );
                    }
                    symb.token = coloredSpan( symb.token, tempColor );
                }
                output += symb.token;
                done++;
                eightBit = done & 4; // IMPROVE just breaking up the output some but is clumpy
                if ( eightBit > 0 )
                    output += "\n";
            }
            // IMPROVE are there multiple blocks in a file?
            wholeFile.sContent( output );
        }
        return toCheck;
    }

    private String coloredSpan( String word, String color ) {
        return "<span style=\'color:#"+ color +"\'>"+ word +"</span>";
    }

    private boolean not( boolean whatever ) { return ! whatever; }
}
