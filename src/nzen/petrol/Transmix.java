/* The project struct of PDistillery
 */

package nzen.petrol;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Nzen
 */
public class Transmix {
    /*
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
    } // IMPROVE nicholas, isn't this supposed to be a forward looking project?
    // ah yes, I'll get on that. I think it was late last time.
    public void sPath( String fPath ) {
        filePath = fPath;
    }

    public LinkedList get( String attr ) {
    return attributes.get( attr );
    }

    public void set( String attr, LinkedList<String> val ) {
        attributes.put( attr, val );
    }

    // sugar, in case I don't feel like it later; ie wrap a single value in a ll
    public void setWrap( String attr, String val ) {
        LinkedList<String> wrap = new LinkedList<>();
        wrap.add( val );
        set( attr, wrap );
    }

}