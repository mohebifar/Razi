
import com.razi.data.elements.ElementsLoader;
import com.razi.formats.smiles.Reader;
import com.razi.models.Element;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;

/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
/**
 *
 * @author Mohamad
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Reader a = new Reader();
            a.set("As1ArCCC1");
            a.process();
            
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

}
