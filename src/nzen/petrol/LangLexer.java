
/**
 * @author Nzen
    this is a series of regex to determine whether a word is terminal or not
    terminal means string, number, operator, reserved word, comment span, etc
    otherwise, it should be a variable. add environment variables if you don't want to see them
        if(this.isAwesome(3)){return "I love you";}
        __(____.isAwesome(_)){______ "__________";} < punctuation left for visual

    span pairs : "" /_* *_/ // \n
    all white space?
    numbers (but also 0.6d )
    reserved words
    - I could read a json for these
    > fsm states: unsure | span | boring | var

     * todo:
    use hard regex for numbers, spans
    use a config file to select/construct regex
    handle escaped span chars
    make a parser strategy for each supported language
	(import the pdautoma class) ? split benefit: separates config from work
*/

package nzen.petrol;

import java.util.LinkedList;

public class LangLexer implements Iterable< TermToken > {
    private LinkedList<TermToken> tokenStream;
    private final String targetLang;
    private final String[] lexable; // languages

    public LangLexer( String ebnf ) {
        targetLang = ebnf;
        // If I get something I don't understand, just
        lexable = new String[] { "words", "threeChar", "" }; // or use config
    }

    public String amFor() { return targetLang; }

    // this is also how to restart it
    public void prep( String codeBlock ) {

        tokenStream = new LinkedList<>();
        applyLang( codeBlock );
    }

    private void applyLang( String codeBlock ) { // IMPROVE rename
        switch( targetLang ) {
        default:
        case "": {
            noColorAtAll( codeBlock );
            return;
         } case "threeChar" : {
             everyThreeCharAlternating( codeBlock );
             return;
         } case "words": {
             replicatesStrTokenizer( codeBlock );
             return;
         } case "math": {
             whiteAndOperators( codeBlock );
             return;
         }
        }
    }

    // for math
    private void whiteAndOperators( String codeBlock ) {
        String[] calculatorSymbols = new String[] {
            "=", "+", "-", "/", "*", "^", "(", ")"
        };
        SyntaxPDautoma wao = new SyntaxPDautoma( calculatorSymbols );
        tokenStream = wao.lex(codeBlock);
    }

    /* just splits along spaces */
    private void replicatesStrTokenizer( String codeBlock ) {
        SyntaxPDautoma rstok = new SyntaxPDautoma();
        tokenStream = rstok.lex(codeBlock);
    }

    // 4TESTS each 3 chars is alternately a nonterminal
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
    public void noColorAtAll( String codeBlock ) {
        tokenStream.add(
            new TermToken(
                codeBlock, TermToken.variable
        )   );
    }

    @Override
    public java.util.Iterator< TermToken > iterator() {
        return tokenStream.iterator();
    }


}
