/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.razi.formats.mol;

import com.razi.descriptor.molecular.CountDescriptor;
import com.razi.formats.WriterInterface;
import com.razi.models.Atom;
import com.razi.models.Bond;
import com.razi.models.Molecule;

/**
 *
 * @author Mohamad
 */
public class Writer implements WriterInterface {

    private Molecule molecule;
    private StringBuilder result = new StringBuilder();

    public void set(Molecule molecule) {
        this.molecule = molecule;
    }

    public void process() throws Exception {
        result.append("Hello");
        breakLine();
        result.append("Mohamad/Razi");
        breakLine();
        makeCountLine();
        breakLine();
        makeAtomLines();
        makeBondLines();
    }

    public String get() {
        return result.toString();
    }

    private void breakLine() {
        result.append("\n");
    }

    private String fixCharacters(String string, int count) {
        count -= string.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= count; i++) {
            builder.append(" ");
        }

        builder.append(string);
        return builder.toString();
    }

    private String fixCharacters(int number, int count) {
        return fixCharacters(String.valueOf(number), count);
    }

    private String fixCharacters(double number, int count) {
        return fixCharacters(String.valueOf(number), count);
    }

    private void makeCountLine() {
        CountDescriptor cd = new CountDescriptor(molecule);
        String obselete = "   ";

        String atomCount = fixCharacters(cd.countAtoms(), 3);
        String bondCount = fixCharacters(cd.countBonds(), 3);
        String atomListCount = fixCharacters(1, 3);
        String zero = fixCharacters(0, 3);
        String isChiral = fixCharacters(0, 3);
        String stextCounts = fixCharacters(3, 3);
        String propertyCounts = fixCharacters(1, 3);

        result.append(atomCount);
        result.append(bondCount);

        result.append(atomListCount);
        result.append(zero);
        result.append(isChiral);
        result.append(stextCounts);
        result.append(obselete);
        result.append(obselete);
        result.append(obselete);
        result.append(obselete);
        result.append(propertyCounts);
        result.append(" V2000");

    }

    private void makeAtomLine(Atom atom) {
        result.append(fixCharacters(0.0, 10));
        result.append(fixCharacters(0.0, 10));
        result.append(fixCharacters(0.0, 10));
        result.append(" ");
        result.append(fixCharacters(atom.getElement().getSymbol(), 3));
    }

    private void makeAtomLines() {
        for (Atom atom : molecule.getAtoms()) {
            makeAtomLine(atom);
            breakLine();
        }
    }
    
    

    private void makeBondLine(Bond bond) {
        int start = 1 + molecule.getAtoms().indexOf(bond.getStart());
        int end = 1 + molecule.getAtoms().indexOf(bond.getEnd());
        
        result.append(fixCharacters(start, 3));
        result.append(fixCharacters(end, 3));
        result.append(fixCharacters(bond.getOrder(), 3));
    }

    private void makeBondLines() {
        for (Bond bond : molecule.getBonds()) {
            makeBondLine(bond);
            breakLine();
        }
    }
    
    
}
