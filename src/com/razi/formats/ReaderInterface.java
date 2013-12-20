/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */

package com.razi.formats;

import com.razi.models.Molecule;
import java.io.File;

/**
 *
 * @author Mohamad
 */
public interface ReaderInterface {
    public void set(String string) throws Exception;
    public void set(File file) throws Exception;
    public void process();
    public Molecule get();
}
