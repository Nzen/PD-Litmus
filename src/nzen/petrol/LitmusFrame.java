
package nzen.petrol;

/** @author Nzen
 * A standalone harness to run Litmus by bundling 'a' file into pd.Transmix
 */
public class LitmusFrame {

    public static void main( String[] args ) {
        /*
        use junit or something, like an adult
         * todo:
        read a file into _valid_ transmix
        receive test results
        print new file using the altered transmix path
        */
        LitmusFrame representsPd = new LitmusFrame();
    }

    public LitmusFrame() {
        java.util.LinkedList<Transmix> hierarchy = new java.util.LinkedList<>();
        hierarchy.add(
                new Transmix( "C:\\users\\Nzen\\Downloads",
                readSpecificFile( "hasCode.html" )
        )   );
        // System.out.println( "LF() file is\n"+ hierarchy.getFirst().gContent() ); // 4TESTS

        Litmus semanticColor = new Litmus();
        hierarchy = semanticColor.stain( hierarchy );

        // visually inspect
        // System.out.println( "Lit became \n"+ hierarchy.getFirst().gContent() ); // 4TESTS
        printSpecificFile( hierarchy );
        System.out.println( "LF() file pooped, go check it" ); // 4TESTS
    }

    // this is okay, but PD won't work like this
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
        return got;
    }

    final public void printSpecificFile( java.util.LinkedList<Transmix> postPlugin ) {
        String outFile = "hasCodeResult.html";
        java.nio.file.Path relPath = java.nio.file.Paths.get(outFile);
        try {
            java.io.BufferedWriter paper
                = java.nio.file.Files.newBufferedWriter(
                        relPath,
                        java.nio.charset.StandardCharsets.UTF_8,
                        java.nio.file.StandardOpenOption.TRUNCATE_EXISTING );
            paper.append( postPlugin.getFirst().gContent() );
            paper.close();
        } catch ( java.io.IOException ioe ) {
            System.err.println( "LF.rsf() had some I/O problem. there's like five options" );
        }
    }

}
