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
public class Molecule {

    private ArrayList<Atom> atoms = new ArrayList<Atom>();

    /**
     * Adds an atom to the molecule
     * 
     * @param atom 
     */
    public void addAtom(Atom atom) {
        atoms.add(atom);
    }

    /**
     * Returns list of all atoms which exist in this molecule
     * 
     * @return 
     */
    public ArrayList<Atom> getAtoms() {
        return atoms;
    }

    /**
     * Returns list of all bonds which exist in this molecule
     * @return 
     */
    public ArrayList<Bond> getBonds() {
        ArrayList<Bond> bonds = new ArrayList<>();
        for (Atom atom : this.getAtoms()) {
            for (Bond bond : atom.getBonds()) {
                if (!bonds.contains(bond)) {
                    bonds.add(bond);
                }
            }
        }

        return bonds;
    }
}
