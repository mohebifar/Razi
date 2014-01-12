
import com.razi.formats.mol.Writer;
import com.razi.formats.smiles.Reader;
import com.razi.models.Molecule;
import forcefield.mmff.Generate3D;

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
            sr.set("C(H)(H)(H)H");
            sr.process();

            Molecule mol = sr.get();

//            CountDescriptor cd = new CountDescriptor(mol);
            Generate3D gen = new Generate3D(mol);
            gen.process();

            Writer mw = new Writer();
            mw.set(mol);
            mw.process();
            System.out.println(mw.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
