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

    /* UNREADY the meaty algorithm */
    protected bislice( LinkedList< int[] > readiedVals ) {
        /*
        # calculates one at a time ; prepend returnVal as export to avoid un/boxing
        def mandolin( qu ) :
            # I'll assume client checks qu.containsAnElement() :
            #qu.pr() # 4TESTS
            intermed = qu.gFirst()
            if intermed.start >= intermed.fin :
                return intermed.fin, qu
            sta = intermed.start
            end = intermed.fin
            dist = end - sta
            if dist is 1 :
                qu.sLast( qN(sta, sta) )
                return end, qu
            if dist is 2 :
                qu.sLast( qN(sta, sta) )
                qu.sLast( qN(end, end) )
                return sta +1, qu # mid
            else :
                mid = dist / 2 + sta
                qu.sLast( qN(mid +1, end) )
                qu.sLast( qN(sta, mid -1) )
                return mid, qu
        */
    }

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
}
