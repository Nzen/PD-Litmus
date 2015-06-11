/**
 * @author Nzen
 A strategy to choose colors for html

 is this only for html or java too?
    yeah, give it a background color, eventually 7_7
    test this internally.

 * todo:
finish download/code/bislice.py to walk the rgb
find matrix transform btwx ciexyz/lab & rgb
 */

package nzen.petrol;

import java.util.LinkedList; // it's a queue & deque too

public class ColorSpace {
    private LinkedList< int[] > unprocessedSpace; // ASK compiler will likely cry on this
    private int[] verbotenColor;
    private int verbotenBuffer;
    private boolean startedDark;
    private int done;
    private int[] lastColor;
    public static final boolean beginBlack = true;
    final private int rr = 0, gg = 1, bb = 2;

    // avoid white
    public ColorSpace() {
        this( ColorSpace.beginBlack );
    }

    // avoid white or black
    public ColorSpace( boolean startDark ) {
        startedDark = startDark;
        if ( startDark ) {
            lastColor = new int[] { 1,1,1 };
        } else {
            lastColor = new int[] { 252,252,252 };
        }
    }

    /* UNREADY also, I'll make/ignore stuff if callee doesn't put three ints here
    // also, I'll assume this will be the main interface
    public ColorSpace( int[] avoid ) {
        startedDark = startDark;
        if ( startDark ) {
            lastColor = new int[] { 1,1,1 };
        } else {
            lastColor = new int[] { 252,252,252 };
        }
    }*/

    /* UNREADY color avoidance functions*/
    public String prepSelf() {
        /*
        # specify here, as an inner class or object fields
        def gap( val ) :
            if val <= 7 or val >= 12 :
                return val
            else :
                return -1

        # this is how I'll avoid the background color
        def chinked_blade() :
            starter = 1
            ending = 15
            print "begin s1,e%d" % (ending)
            # slice(starter, ending)
            val = -1
            ku = queue( qN(starter, ending) )
            while ku.containsAnElement() :
                val,ku = mandolin(ku)
                if gap( val ) < 0 :
                    continue
                raw_input( val )
        */
        return "UNREADY";
    }

    /* UNREADY */
    public String next() {
        /*
        lastColor[ rr ]
        lastColor[ gg ]
        lastColor[ bb ]

        low = 0
        hi = 255
        red = queue( qN(low, hi) )
        lue = queue( qN(low, hi) )
        een = queue( qN(low, hi) )
        rr = low
        bb = low
        gr = low
        # prime the pump to stagger them
        bb,lue = mandolin(lue)
        gr,een = mandolin(een)
        gr,een = mandolin(een)
        # now run for real
        while red.containsAnElement() :
            rr,red = mandolin(red)
            bb,lue = mandolin(lue)
            gr,een = mandolin(een)
            raw_input( str(rr) +" "+ str(bb) +" "+ str(gr) )
        */
        return strHexOfColor(); // UNREADY
    }

    /* UNREADY the meaty algorithm, one at a time; deque to get two vals out */
    protected LinkedList bislice( LinkedList< int[] > readiedVals ) {
        int[] span = readiedVals.poll();
        if (span[ Ind.low ] >= span[ Ind.high ]) {
            readiedVals.push( new int[]{span[ Ind.low ]} ); // le sigh, a deque for ease
            return readiedVals;
        } // else
        int low = span[ Ind.low ];
        int high = span[ Ind.high ];
        int dist = high - low;
        if ( dist == 1 ) {
            readiedVals.add( new int[]{low, low} );
            readiedVals.push( new int[]{high} );
        } else if ( dist == 2 ) {
            readiedVals.add( new int[]{low, low} );
            readiedVals.add( new int[]{high, high} );
            readiedVals.push( new int[]{low +1} ); // mid, also avoids division
        } else {
            int mid = (Math.round(dist / 2) + low);
            readiedVals.add( new int[]{mid +1, high} );
            readiedVals.add( new int[]{low, mid -1} );
            readiedVals.push( new int[]{mid} );
        }
        return readiedVals;
    }

    public void eyeTestBislice() {
        LinkedList< int[] > dque = new LinkedList<>();
        int[] shortRun = new int[]{1, 15};
        dque.add( shortRun );
        while ( dque.size() > 0 ) {
            dque = bislice( dque );
            shortRun = dque.pop(); // yay two return vals
            System.out.println( "cs.etb() - "+ shortRun[Ind.low] );
            /*for ( int[] beep : dque ) {
                System.out.println( " "+ beep[Ind.low] +","+ beep[Ind.high] );
            }*/
    }   }

    /* IMPROVE later change this to some real color walk; nope just all out replace */
    public String nezt() {
        done++;
        int indToChange = done % 3;
        switch( indToChange ) {
        default:
        case rr: {
            lastColor[ rr ] = (int)(Math.sin( done ) *125.0) + 125;
            break;
         }
        case gg: {
            lastColor[ gg ] = (int)(Math.sin( done +1.57 ) *125.0) + 125;
            break;
         }
        case bb: {
            lastColor[ bb ] = (int)(Math.sin( done +.785 ) *125.0) + 125;
            break;
         }
        }

        /*
        int indToChange = done % 3;
        if ( indToChange < 1 ) {
            lastColor[ indToChange ] =
                    (int)(Math.sin( done )*125.0) + 125;
            lastColor[ indToChange +1 ] =
                    (int)(Math.cos( done )*125.0) + 125;
        } else {
            lastColor[ indToChange ] =
                    (int)(Math.cos( done )*125.0) + 125;
            lastColor[ indToChange -1 ] =
                    (int)(Math.sin( done )*125.0) + 125;
        }
        */
        // System.out.println( "CS to "+ strHexOfColor() ); // 4TESTS
        return strHexOfColor(); // UNREADY
    }

    private String strHexOfColor() {
        String[] hex = new String[ 3 ];
        hex[0] = Integer.toHexString( lastColor[rr] );
        hex[1] = Integer.toHexString( lastColor[gg] );
        hex[2] = Integer.toHexString( lastColor[bb] );
        for ( int ind = 0; ind < hex.length; ind++ ) {
            if ( hex[ ind ].length() < 2 )
                hex[ ind ] = "0"+ hex[ ind ];
        }
        return hex[0] + hex[1] + hex[2];
    }

    public void restart() {
        if ( startedDark ) {
            lastColor = new int[] { 1,1,1 };
        } else {
            lastColor = new int[] { 252,252,252 };
        }
        done = 0;
    }

    private class Ind {
        final static int low = 0;
        final static int high = low +1;
    }
}
