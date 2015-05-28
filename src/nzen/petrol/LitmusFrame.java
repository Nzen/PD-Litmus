
package nzen.petrol;

/** @author Nzen
 * A standalone harness to run Litmus by bundling 'a' file into pd.Transmix
 */
public class LitmusFrame {

    public static void main( String[] args ) {
        /*
        convert files to transmix
        init litmus with lang config
        hand transmix to litmus
        write transmix to files

        run all the internal tests from here. use junit or something like an adult

         * todo:
        send html content with <code>
        read a file into transmix
        receive test results
        print output from transmix
        */
        LitmusFrame representsPd = new LitmusFrame();
    }

    public LitmusFrame() {
        String content =
            "Class is dead, says Foucault; however, according to Reicher,\n"
            +" it is not so much class that is dead, but rather the absurdity,\n"
            +" and some would say the paradigm, of class.  However, the subject\n"
            +" is interpolated into a postpatriarchialist dialectic theory that\n"
            +" includes consciousness as a totality.";
        String allcaps = "QWERTYUIOPASDFGHJKLZXCVBMN?ZAQWSXCDERFVBGTYHN*M,KIUJ\n"
                + "QWERTYUIOPASDFGHJKLZXCVBMN?ZAQWSXCDERFVBGTYHN*M,KIUJ\n"
                + "QWERTYUIOPASDFGHJKLZXCVBMN?ZAQWSXCDERFVBGTYHN*M,KIUJ\n"
                + "QWERTYUIOPASDFGHJKLZXCVBMN?ZAQWSXCDERFVBGTYHN*M,KIUJ\n";
                // for threeChar
        java.util.LinkedList<Transmix> hierarchy = new java.util.LinkedList<>();
        hierarchy.add(
                new Transmix( "C:\\users\\Nzen\\Downloads",
                readSpecificFile( "hasCode.html" )
        )   );
        // System.out.println( "LF() file is\n"+ hierarchy.getFirst().gContent() ); // 4TESTS

        Litmus semanticColor = new Litmus();
        hierarchy = semanticColor.stain( hierarchy );

        // visually inspect
        System.out.println( "Lit became \n"+ hierarchy.getFirst().gContent() );
    }

    final public String readSpecificFile( String fileName ) {
        String got = "";
        try {
            java.nio.file.Path relPath = java.nio.file.Paths.get(fileName);
            // System.out.println( "LF.rsf() path is "+ relPath.toString() ); // 4TESTS
            byte[] encoded = java.nio.file.Files.readAllBytes(
                    relPath);
            got = new String(encoded, java.nio.charset.StandardCharsets.UTF_8);
        } catch ( java.io.IOException ioe ) {
            System.err.println( "LF.rsf() didn't get a real file" );
        }
        return got; // UNREADY
    }

}
