package com.razi.formats;

import com.razi.models.Molecule;
import java.io.File;

/**
 *
 * @author Mohamad Mohebifar <mohebifar.ir>
 */
public interface ReaderInterface {
    
    /**
     * Set a string as input smiles
     * 
     * @param string
     * @throws Exception 
     */
    public void set(String string) throws Exception;
    
    /**
     * Set a smiles file (.smi) as input
     * 
     * @param file
     * @throws Exception 
     */
    public void set(File file) throws Exception;
    
    /**
     * Process the parsing
     * 
     * @throws Exception 
     */
    public void process() throws Exception;
    
    /**
     * Return the Molecule instance
     * 
     * @return Molecule
     */
    public Molecule get();
}
