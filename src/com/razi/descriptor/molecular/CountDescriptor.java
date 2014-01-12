/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.razi.descriptor.molecular;

import com.razi.models.Atom;
import com.razi.models.Bond;
import com.razi.models.Element;
import com.razi.models.Molecule;
import java.util.ArrayList;

/**
 *
 * @author Mohamad
 */
public class CountDescriptor {

    private Molecule molecule;

    public CountDescriptor(Molecule molecule) {
        this.molecule = molecule;
    }

    public int countAtoms() {
        return molecule.size();
    }

    public int countAtomsByElement(Element element) {
        int count = 0;
        for (Atom atom : molecule) {
            if (atom.getElement() == element) {
                count++;
            }
        }

        return count;
    }

    public int countBonds() {
        return molecule.getBonds().size();
    }

    public int countBondsByOrder(int order) {
        int count = 0;
        
        ArrayList<Bond> bonds = molecule.getBonds();
        
        for (Bond bond : bonds) {
            if (bond.isOrder(order)) {
                count++;
            }
        }

        return count;
    }
}
