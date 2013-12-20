/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.razi.models;

import java.util.ArrayList;

/**
 * Atom Model
 *
 * @author Mohamad Mohebifar
 * @version 0.1
 */
public class Atom {

    private ArrayList<Bond> bonds = new ArrayList<Bond>();
    private Element element;

    public void Atom(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return this.element;
    }

    /**
     * Checking bond of this atom to another atom
     *
     * @param atom
     * @return boolean
     */
    public boolean isBonded(Atom atom) {
        for (Bond bond : bonds) {
            if (bond.hasAtom(atom)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checking bond of this atom to another atom by given distance.
     *
     * @param distance
     * @param atom
     * @return boolean
     * @throws Exception
     */
    public boolean isBondedByDistance(int distance, Atom atom) throws Exception {
        distance = Math.abs(distance);

        if (distance < 1) {
            throw new Exception("There is a paradox. Checking an atom is another atom ?");
        }

        if (distance == 1) {
            return this.isBonded(atom);
        } else {
            distance--;

            for (Bond bond : bonds) {
                if (bond.getAtomByPartner(this).isBondedByDistance(distance, atom)) {
                    return true;
                }
            }
        }

        return false;
    }
}
