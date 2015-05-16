
package nzen.petrol;

/**
 * @author Nzen
 */
public class LangLexer implements Iterable< TermToken > {
    /*
    this is a series of regex to determine whether a word is terminal or not
    terminal means string, number, operator, reserved word, comment span, etc
    otherwise, it should be a variable. add environment variables if you don't want to see them

    it doesn't really matter what type of terminal it is.
    this is a distinct object because there are many languages: java jscript, c++

    whoops, lang needs to lex, not just regex to handle
        if(this.isAwesome(3)){return "I love you";}
    hence litmus can't split words anymore

	load this with the whole code block
	then the client can iterate through the tokens
	output str_bool of {token ; isTerminal}
	minVP: spaces are terminal, everything else is non

    */
    private String code; // FIX to an array or list of termtokens

    public LangLexer( String codeBlock, String ebnf ) {
        /*
        build a lexer thing from the ebnf
        apply to code block
        supply to lexanalysis
        let them extract what they need
        */
        code = codeBlock;
    }

    public LangLexer( String ebnf ) {
        
    }

    public boolean prep( String codeBlock ) {
        // if ebnf is null return false
        code = codeBlock;
        return true; // UNREADY
    }

    @Override
    public java.util.Iterator< TermToken > iterator() {
        return new LexAnalysis( code );
    }

    private class LexAnalysis implements java.util.Iterator< TermToken > {
        String aSyTree; // data type will change, this is just to cut the red highlight
        int pointingAt;
        public LexAnalysis( String ast ) {
            aSyTree = ast;
            pointingAt = 0;
        }
	
        @Override
        public boolean hasNext() {
                return pointingAt < aSyTree.length();
        }

        @Override
        // FIX this needs to iterate through some array or list of term tokens, not do this mvp
        public TermToken next() {
            int end;
            int colorBit = pointingAt & 1; // even?
            pointingAt += 3;
            if ( pointingAt < aSyTree.length() )
                end = pointingAt;
            else
                end = aSyTree.length() -1; // NOTE likely overlap: this is a hack until real iteration
            return new TermToken(
                    aSyTree.substring( end -2, end +1 ),
                    true);//colorBit > 0);
        }

        @Override
        public void remove() {
                throw new UnsupportedOperationException();
        }
    }
}
