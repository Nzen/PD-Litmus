
package nzen.petrol;

import java.util.LinkedList;

/** @author Nzen
 */

/*
 todo
break by whitespace & pda
test or print, so I know it works
take in operator list, apply to pda
same for comment pairs
use keywords too

should this run through the whole thing or work char by char?
just run through it, no sense in having control straddle two classes

*/
public class SyntaxPDautoma {
    // private String[] operators;
    private LinkedList<TermToken> charStream;
    private int lastEnded;
    private int curr;

    public enum Token {
        unsure, literal, variable; //, span; um will I ever use \" outside of span?
        public static boolean toTT( Token became ) {
            if (became == variable)
                return TermToken.variable;
            else
                return TermToken.literal;
        }
    }
    int testToTT() {
        int failed = 0;
        boolean resultType = Token.toTT(Token.variable);
        failed += (resultType) ? 0 : 1;
        return failed;
    }

    // for whitespace only
    public SyntaxPDautoma() {
        charStream = new LinkedList<>();
    }

    /*classifies tokens from codeBlob, char wise*/
    public LinkedList<TermToken> lex( String codeBlob ) {
        Token currState = Token.unsure;
        for ( int ind = 0; ind < codeBlob.length(); ind++ ) {
            Token movesTo = transTo( codeBlob.charAt(ind), currState );
            if ( movesTo != currState ) {
                charStream.add( new TermToken(
                        codeBlob.substring(lastEnded, ind),
                        Token.toTT(currState)
                )   );
                /* System.out.println( "spda.trans from "+ Integer.toString(lastEnded)
                    +" to "+ Integer.toString(ind)
                    +" is "+ currState +" wbe "+ movesTo ); // 4TESTS */
                lastEnded = ind; // off by 1?
                currState = movesTo; // halting run? FIX
            }
            // keep transitioning, as I won't split until oh
        }
        return charStream; // UNREADY
    }

    /* initial transition, not useful for three states yet */
    Token transTo( char lett, Token currState ) {
        if ( whiteSpace( lett ) ) {
            currState = whiteTrans( currState );
        } else {
            currState = nonWhiteTrans( currState );
        }
        return currState;
    }

    boolean whiteSpace( char lett ) {
        return lett == ' ' || lett == '\t' || lett == '\n' || lett == '\r';
    }
    Token whiteTrans( Token curr ) {
        switch( curr ) {
         default:
         case literal:
         case variable:
         case unsure:
            return Token.literal;
    }   }
    /*ie next char is not whitespace; more meaningful later when real ambiguity*/
    Token nonWhiteTrans( Token curr ) {
        switch( curr ) {
         default:
         case literal:
         case variable:
         case unsure:
            return Token.variable;
    }   }

}
