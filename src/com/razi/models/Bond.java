/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.razi.models;

/**
 *
 * @author Mohamad
 */
public class Bond {

    private Atom start;
    private Atom end;
    private int order = 1;

    /**
     * Construct a bond with certain order
     *
     * @param start
     * @param end
     * @param order
     */
    public Bond(Atom start, Atom end, int order) {
        this.setStartAtom(start);
        this.setEndAtom(end);
        this.order = order;
    }

    /**
     * Construct a bond with order 1
     *
     * @param start
     * @param end
     */
    public Bond(Atom start, Atom end) {
        this.setStartAtom(start);
        this.setEndAtom(end);
    }

    /**
     * Set first atom
     *
     * @param atom
     */
    public final void setStartAtom(Atom atom) {
        this.start = atom;
        atom.addBond(this);
    }

    /**
     * Set end atom
     *
     * @param atom
     */
    public final void setEndAtom(Atom atom) {
        this.end = atom;
        atom.addBond(this);
    }

    /**
     * Set order of atom
     *
     * @param order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Get order of bond
     *
     * @return
     */
    public int getOrder() {
        return this.order;
    }

    public boolean isOrder(int order) {
        return this.getOrder() == order;
    }

    /**
     * Check if this bond contains given atom.
     *
     * @param atom
     * @return
     */
    public boolean hasAtom(Atom atom) {
        return atom == start || atom == end;
    }

    /**
     * Get given atom's partner.
     *
     * @param atom
     * @return
     * @throws Exception
     */
    public Atom getAtomByPartner(Atom atom) throws Exception {
        if (start == atom) {
            return end;
        } else if (end == atom) {
            return start;
        } else {
            throw new Exception("Given atom is not a part of this bond.");
        }
    }

    /**
     * Check if this bond contains an atom with given element.
     *
     * @param element
     * @return
     */
    public boolean hasAtomByElement(Element element) {
        if (start.getElement() == element || end.getElement() == element) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the atom with given element.
     *
     * @param element
     * @return
     * @throws Exception
     */
    public Atom getAtomByElement(Element element) throws Exception {
        if (start.getElement() == element) {
            return start;
        } else if (end.getElement() == element) {
            return end;
        } else {
            throw new Exception("There is no atom with given element");
        }
    }

    /**
     * Returns start atom
     * 
     * @return Atom
     */
    public Atom getStart() {
        return start;
    }

    /**
     * Returns end atom
     * 
     * @return Atom
     */
    public Atom getEnd() {
        return end;
    }
}
