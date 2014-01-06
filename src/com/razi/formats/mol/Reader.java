/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.razi.formats.mol;

import com.razi.formats.ReaderInterface;
import com.razi.models.Molecule;
import java.io.File;

/**
 *
 * @author Mohamad Mohebifar
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
