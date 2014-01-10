
import Jama.LUDecomposition;
import Jama.Matrix;
import com.razi.formats.mol.Writer;
import com.razi.formats.smiles.Reader;
import com.razi.models.Molecule;
import com.vividsolutions.jts.geom.Coordinate;

/**
 *
 * @author Mohamad Mohebifar <mohebifar.ir>
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Reader sr = new Reader();
            sr.set("C(=O)O");
            sr.process();

            Molecule mol = sr.get();
            Writer mw = new Writer();
//            mw.set(mol);
//            mw.process();
//            System.out.println(mw.get());

//            CountDescriptor cd = new CountDescriptor(mol);
            
            Coordinate a = new Coordinate();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

}
