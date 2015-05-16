
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
            +" and some would say the paradigm, of class. However, the subject\n"
            +" is interpolated into a postpatriarchialist dialectic theory that\n"
            +" includes consciousness as a totality.";
        String allcaps = "QWERTYUIOPASDFGHJKLZXCVBMN?ZAQWSXCDERFVBGTYHN*M,KIUJ\n"
                + "QWERTYUIOPASDFGHJKLZXCVBMN?ZAQWSXCDERFVBGTYHN*M,KIUJ\n"
                + "QWERTYUIOPASDFGHJKLZXCVBMN?ZAQWSXCDERFVBGTYHN*M,KIUJ\n"
                + "QWERTYUIOPASDFGHJKLZXCVBMN?ZAQWSXCDERFVBGTYHN*M,KIUJ\n";
        java.util.LinkedList<Transmix> hierarchy = new java.util.LinkedList<>();
        hierarchy.add(new Transmix( "C:\\users\\Nzen\\Downloads", allcaps ));

        Litmus semanticColor = new Litmus();
        hierarchy = semanticColor.stain( hierarchy );

        // visually inspect
        System.out.println( "Lit became \n"+ hierarchy.getFirst().gContent() );
    }

}
