/* A strategy to choose colors for html
 */

package nzen.petrol;
/* is this only for html or java too?
    start with a verboten color instead?
    starting with minVP

    yeah, give it a background color, eventually 7_7
    test this internally.

 * todo:
finish download/code/bislice.py to walk the rgb
find matrix transform btwx ciexyz/lab & rgb
*/

/**
 * @author Nzen
 */
public class ColorSpace {
    private boolean startedDark;
    private int done;
    private int[] lastColor;
    public static final boolean beginBlack = true;
    final private int rr = 0, gg = 1, bb = 2;

    public ColorSpace() {
        this( ColorSpace.beginBlack );
    }

    public ColorSpace( boolean startDark ) {
        startedDark = startDark;
        if ( startDark ) {
            lastColor = new int[] { 1,1,1 };
        } else {
            lastColor = new int[] { 252,252,252 };
        }
    }

    /* IMPROVE later change this to some real color walk */
    public String next() {
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
