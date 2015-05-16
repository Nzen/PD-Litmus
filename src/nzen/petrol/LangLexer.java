
package nzen.petrol;

import java.util.LinkedList;

/**
 * @author Nzen
 */
public class LangLexer implements Iterable< TermToken > {
    /*
    this is a series of regex to determine whether a word is terminal or not
    terminal means string, number, operator, reserved word, comment span, etc
    otherwise, it should be a variable. add environment variables if you don't want to see them

    handle
        if(this.isAwesome(3)){return "I love you";}

    span pairs : "" /_* *_/ // \n
    all white space?
    numbers (but also 0.6d )
    reserved words
    - I could read a json for these
    > fsm states: unsure | span | boring | var

     * todo:
    X? move lexing into the langLexer from the lexanalysis iterator
    use hard regex for java & syntax parsing
    use a config file to select/construct regex
    make a parser strategy for each supported language
    */
    private String code; // FIX to an array or list of termtokens
    private LinkedList<TermToken> tokenStream;
    private final String targetLang;
    private final String[] lexable;

    public LangLexer( String ebnf ) {
        targetLang = ebnf;
        // If I get something I don't understand, just
        lexable = new String[] { "threeChar", "" }; // or use config
    }

    public String amFor() { return targetLang; }

    // to choose the prep strategy
    public boolean checkLang() {
        boolean foundIt = true;
        for ( String lang : lexable ) {
            if (lang.equals( targetLang ))
                return foundIt;
        } // else
        return ! foundIt;
    }

    // this is also how to restart it
    public boolean prep( String codeBlock ) {
        // if ebnf is null return false
        code = codeBlock;

        tokenStream = new LinkedList<>();
        if ( checkLang() )
            return deferTo( codeBlock );
        else
            return noColorAtAll( codeBlock );
    }

    private boolean deferTo( String codeBlock ) { // IMPROVE rename?
        switch( targetLang ) {
        default:
        case "": {
            noColorAtAll( codeBlock );
         } case "threeChar" : {
             everyThreeCharAlternating( codeBlock );
         }
        }
        return true; // UNREADY
    }

    // 4TESTS replace this with the lexing. this just does whatever for mvp
    private void everyThreeCharAlternating( String codeBlock ) {
        for ( int ind = 0; ind < codeBlock.length() -3; ind += 3 ) {
            tokenStream.add(
                new TermToken(
                    codeBlock.substring(ind, ind +3), (ind & 1) >0 // to vary color
            )   );
        }
        // capture final characters
        tokenStream.add(
            new TermToken(
                codeBlock.substring(
                    codeBlock.length() -3, codeBlock.length() -1),
                TermToken.variable
        )   );
    }

    /*doesn't color anything. called for missing / unrecognized languages*/
    public boolean noColorAtAll( String codeBlock ) {
        tokenStream.add(
            new TermToken(
                codeBlock, TermToken.literal
        )   );
        return true; // yeah, worked, whatever
    }

    @Override
    public java.util.Iterator< TermToken > iterator() {
        return tokenStream.iterator();
    }


}
