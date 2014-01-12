/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.razi.models;

import com.vividsolutions.jts.geom.Coordinate;
import java.util.ArrayList;

/**
 * Atom Model
 *
 * @author Mohamad Mohebifar <mohebifar.ir>
 * @version 0.1
 */
public class Atom {

    private ArrayList<Bond> bonds = new ArrayList<Bond>();
    private Coordinate position = new Coordinate(0, 0, 0);
    private Element element;

    public Atom(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return this.element;
    }

    public ArrayList<Bond> getBonds() {
        return bonds;
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
     * Checking connectivity of this atom to another atom by given distance. For
     * example Hydrogens in H-H are connected to each other by distance 1 or
     * H-C-H are connected to each other by distance 2. In other words Hydrogens
     * in H-C-H are in one three situation. (3-1 = 2)
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

    /**
     * Assign a bond to this atom
     *
     * @param bond
     */
    public void addBond(Bond bond) {
        this.bonds.add(bond);
    }
    
    /**
     * Position of this atom
     * 
     * @return Coordinate
     */
    public Coordinate getPosition() {
        return position;
    }
}
