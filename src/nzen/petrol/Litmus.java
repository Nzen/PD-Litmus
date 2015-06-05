
/** @author Nzen
     * todo:
    concurrentize stain() by colorBlock() multiple ?
 */

package nzen.petrol;

import java.util.TreeMap;
import java.util.LinkedList;

public class Litmus {

    private LangLexer daisyChain;
    private TreeMap< String, String > colorsUsed;
        // unknown number of tags. maybe daisychain can report after lexing?

    public Litmus() {
        colorsUsed = new TreeMap<>();
        daisyChain = null;
    }

    public java.util.LinkedList<Transmix> stain( java.util.LinkedList<Transmix> toCheck ) {
        for ( Transmix wholeFile : toCheck ) {
            String done = "";
            LinkedList<HtmlBlock> allBlocks = splitOutCodeBlocks( wholeFile.gContent() );
            for ( HtmlBlock thing : allBlocks ) {
                if ( thing.isCode ) {
                    done += colorBlock( thing.content, thing.lang );
                } else {
                    done += thing.content;
                }
            }
            // ASK or iterate it here rather than adding above
            wholeFile.sContent( done );
        }
        return toCheck;
    }

    LinkedList<HtmlBlock> splitOutCodeBlocks( String mess ) {
        boolean holdsCode = true;
        String codeStart = "<code lang=";
        int tagLen = codeStart.length();
        String codeEnd = "</code>";
        int[] junctions = new int[ 50 ]; // FIX fragile, etc needs to be dynamic
        LinkedList<HtmlBlock> biggerTerminals = new LinkedList<>();
        int temp_a =0, temp_b =0;
        int messInd = 0;
        String codeLang;
        while ( messInd < mess.length() ) {
            temp_a = mess.indexOf( codeStart, messInd );
            if ( temp_a < 0 ) {
                // no more code blocks: save rest of the page
                biggerTerminals.add(
                    new HtmlBlock( mess.substring( messInd ),
                        null, ! holdsCode) // ASK check for the off by one
                );
                break;
            }
            temp_b = mess.indexOf( ">", temp_a );
            if ( temp_b < 0 )
                break;
            // save the boring part we just skipped
            biggerTerminals.add(
                new HtmlBlock( mess.substring( messInd, temp_b),
                    null, ! holdsCode) // ASK check for the off by one
            );
            // save the code
            codeLang = mess.substring( temp_a + tagLen +1, temp_b -1); // avoiding quotes
            System.out.println( "L.socb() lang is "+ codeLang ); // 4TESTS
            temp_a = mess.indexOf( codeEnd, temp_b +1 );
            biggerTerminals.add(
                new HtmlBlock( mess.substring( temp_b +1, temp_a),
                    codeLang, holdsCode)
            );
            messInd = temp_a;
        }
        return biggerTerminals;
    }

    private String colorBlock( String uncolored, String lang ) {
        ColorSpace cieLab = new ColorSpace( ColorSpace.beginBlack );
        String tempColor;
        String output = "";
        daisyChain = new LangLexer( lang );
        daisyChain.prep( uncolored );
        for ( TermToken symb : daisyChain ) {
            if ( symb.nonTerminal ) {
                if ( not(colorsUsed.containsKey( symb.token )) ) {
                    tempColor = cieLab.next();
                    colorsUsed.put( symb.token, tempColor );
                } else {
                    tempColor = colorsUsed.get( symb.token );
                }
                symb.token = htmlSanitize( symb.token );
                symb.token = coloredSpan( symb.token, tempColor );
            } else
                symb.token = htmlSanitize( symb.token ); // yay, for whitespace
            output += symb.token;
        }
        return output;
    }

    private String htmlSanitize( String someChars ) {
        someChars = someChars.replace( "&", "&amp;" );
        someChars = someChars.replace( "<", "&lt;" );
        someChars = someChars.replace( ">", "&gt;" );
        someChars = someChars.replace( "\t", " &nbsp; " );
        someChars = someChars.replace( "\r", "" );
        someChars = someChars.replace( "\n", "<br>\n" );
        return someChars;
    }

    private String coloredSpan( String word, String color ) {
        return "<span style='color:#"+ color +"'>"+ word +"</span>";
    }

    private boolean not( boolean whatever ) { return ! whatever; }

    private class HtmlBlock {
        String content;
        String lang;
        boolean isCode;
        public HtmlBlock( String interior, String langAttr, boolean care ) {
            content = interior;
            lang = langAttr;
            isCode = care;
    }   }

}
