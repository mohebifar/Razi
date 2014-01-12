/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.razi.models;

import java.util.ArrayList;

/**
 *
 * @author Mohamad
 */
public class Molecule extends AtomSet {
    /**
     * Returns list of all bonds which exist in this molecule
     * @return 
     */
    public ArrayList<Bond> getBonds() {
        ArrayList<Bond> bonds = new ArrayList<>();
        for (Atom atom : this) {
            for (Bond bond : atom.getBonds()) {
                if (!bonds.contains(bond)) {
                    bonds.add(bond);
                }
            }
        }

        return bonds;
    }
}
