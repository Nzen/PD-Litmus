
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
        for ( Transmix wholeFile : toCheck ) {
            String done = "";
            HtmlBlock[] allBlocks = splitOutCodeBlocks( wholeFile.gContent() );
            for ( HtmlBlock thing : allBlocks ) {
                if ( thing.isCode ) {
                    done += colorBlock( thing.content, "words" );
                } else {
                    done += thing.content;
                }
            }
            // ASK or iterate it here rather than adding above
            wholeFile.sContent( done );
        }
        return toCheck;
    }

    HtmlBlock[] splitOutCodeBlocks( String mess ) {
        int[] junctions = new int[ 12 ]; // FIX fragile, etc needs to be dynamic
        int junctInd = 0;
        int temp_a, temp_b;
        int messInd = 0;
        String codeTag;
        for ( int ind = 0; ind < mess.length(); ind++ ) {
            temp_a = mess.indexOf( "<code lang=", messInd );
            if ( temp_a < 0 )
                break;
            temp_b = mess.indexOf( ">", temp_a );
            if ( temp_b < 0 )
                break;
            codeTag = mess.substring( temp_a, temp_b);
            // codeTag = "<code lang='banana'>"
            /* it's late late, FIX assume the rest of is broken hacks. you're welcome
            mainly, I'm not going to split this correctly
            */
            junctions[ junctInd ] = temp_b;
            junctInd++;
            temp_a = mess.indexOf( "</code>", temp_b );
            if ( temp_a < 0 )
                break;
            junctions[ junctInd ] = temp_a;
            junctInd++;
            messInd = temp_a;
        }
        boolean codeB = true; // REPLACE
        HtmlBlock[] sorted = new HtmlBlock[ junctInd ];
        temp_a = 0;
        for ( int ind = 0; ind < junctInd -1; ind++ ) {
            sorted[ ind ] = new HtmlBlock(
                    mess.substring( temp_a, junctions[ ind ]),
                    "", not( codeB )
            );
            temp_a = junctions[ ind ];
            ind++;
            sorted[ ind ] = new HtmlBlock(
                    mess.substring( temp_a, junctions[ ind ]),
                    "words", codeB
            );
        }
        return sorted; // UNREADY
    }

    private String colorBlock( String uncolored, String lang ) {
        //int done = 0;
        int eightBit;
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
            /*
            done++;eightBit = done & 7; // 4TESTS just breaking up the output
            if ( eightBit == 4 )
                output += "\n";
            // < 4TESTS */
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
