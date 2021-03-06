/**
 * @author Nzen
  The project struct of PDistillery

    pd will give a ll of Transmix structs to the plugin. order is irrelevant.
    insertion and removal are important.
    searching can be accommodated with smaller groups (ex for templates-posts)

    should I use their maps (hash/tree) or make/copy a trie ?

    path / filepath
    map of ll< str > attributes (ex tags)
    content

     * todo:
    switch to nio.Path
    (profile the memory use on all this)
  */

package nzen.petrol;

import java.util.HashMap;
import java.util.LinkedList;

public class Transmix {

    public String filePath; // IMPROVE use Path instead
    public HashMap<String,LinkedList<String>> attributes;
    private String content;

    public Transmix() {
        this( "","" );
    }

    public Transmix( String fPath, String stuff ) {
        filePath = fPath;
        content = stuff;
        attributes = new HashMap<>();
    }

    public String gContent() {
        return content;
    }
    public void sContent( String newText ) {
        if ( newText == null )
            newText = "";
        content = newText;
    }

    public String gPath() {
        return filePath;
    } // IMPROVE to java.nio.file.path
    public void sPath( String fPath ) {
        filePath = fPath;
    }

    public LinkedList get( String attr ) {
    return attributes.get( attr );
    }

    public void set( String attr, LinkedList<String> val ) {
        attributes.put( attr, val );
    }

    // sugar, in case I don't feel like it later: wrap a single value in a LL
    public void setWrap( String attr, String val ) {
        LinkedList<String> wrap = new LinkedList<>();
        wrap.add( val );
        set( attr, wrap );
    }

}