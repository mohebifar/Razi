package com.razi.formats.mol;

import com.razi.formats.ReaderInterface;
import com.razi.models.Molecule;
import java.io.File;

/**
 * MolFile Reader
 * 
 * @author Mohamad Mohebifar <mohebifar.ir>
 */
public class Reader implements ReaderInterface {
    private String table;
    private Molecule molecule = new Molecule();

    @Override
    public void set(String string) throws Exception {
        table = string;
    }

    @Override
    public void set(File file) throws Exception {
        table = "something";
    }

    @Override
    public Molecule get() {
        return this.molecule;
    }

    @Override
    public void process() throws Exception {

    }
}
