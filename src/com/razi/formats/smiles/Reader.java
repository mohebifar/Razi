package com.razi.formats.smiles;

import com.razi.data.elements.ElementsLoader;
import com.razi.formats.ReaderInterface;
import com.razi.models.Atom;
import com.razi.models.Bond;
import com.razi.models.Element;
import com.razi.models.Molecule;
import java.io.File;
import java.util.ArrayList;

/**
 * SMILES Parser
 * 
 * @author Mohamad Mohebifar <mohebifar.ir>
 */
public class Reader implements ReaderInterface {

    private String smiles;
    private Molecule molecule = new Molecule();
    private ArrayList<Atom> atoms = new ArrayList<Atom>();
    private ArrayList<Atom> atomReferences = new ArrayList<Atom>();

    private int bondOrder = 1;

    protected static enum Types {

        ELEMENT, BOND, REFERENCE, STARTLAYER, ENDLAYER
    };

    private class Meaning {

        protected Types type;
        protected String stringValue;
        protected int intValue;
        protected int characterShift = 1;
    }

    @Override
    public void set(String string) throws Exception {
        smiles = string;
    }

    @Override
    public void set(File file) throws Exception {
        smiles = "something";
    }

    @Override
    public Molecule get() {
        return this.molecule;
    }

    @Override
    public void process() throws Exception {
        Atom lastAtom;

        int index, layer;
        index = 0;
        layer = 0;

        while (index < smiles.length()) {
            Meaning meaning = getMeaning(index);

            switch (meaning.type) {
                case ELEMENT:
                    lastAtom = getLastAtom(layer);
                    Element element = ElementsLoader.getAtomBySymbol(meaning.stringValue);
                    Atom atom = new Atom(element);

                    molecule.addAtom(atom);

                    if (lastAtom == null && layer > 0) {
                        lastAtom = getLastAtom(layer - 1);
                    }

                    if (lastAtom != null) {
                        Bond bond = new Bond(lastAtom, atom, getBondOrder());
                    }

                    setLastAtom(atom, layer);
                    break;
                case BOND:
                    bondOrder = meaning.intValue;
                    break;
                case STARTLAYER:
                    layer++;
                    break;
                case ENDLAYER:
                    clearLastAtom(layer--);
                    break;
                case REFERENCE:
                    Atom reference = getAtomReference(meaning.intValue);
                    if (reference == null) {
                        setAtomReference(getLastAtom(layer), meaning.intValue);
                    } else {
                        Bond bond = new Bond(reference, getLastAtom(layer), getBondOrder());
                    }
                    break;
            }

            index += meaning.characterShift;
        }
    }

    private Meaning getMeaning(int index) {
        Meaning meaning = new Meaning();

        char currentChar = smiles.charAt(index);
        char nextChar = index < smiles.length() - 1 ? smiles.charAt(index + 1) : ' ';

        String stick = new StringBuilder().append(currentChar).append(nextChar).toString();

        if (Element.isValidSymbol(stick)) {
            meaning.characterShift = 2;
            meaning.type = Types.ELEMENT;
            meaning.stringValue = stick;
        } else if (currentChar == '-') {
            meaning.type = Types.BOND;
            meaning.intValue = 1;
        } else if (currentChar == '=') {
            meaning.type = Types.BOND;
            meaning.intValue = 2;
        } else if (currentChar == '#') {
            meaning.type = Types.BOND;
            meaning.intValue = 3;
        } else if (currentChar == '(') {
            meaning.type = Types.STARTLAYER;
        } else if (currentChar == ')') {
            meaning.type = Types.ENDLAYER;
        } else if (Character.isUpperCase(currentChar)) {
            meaning.type = Types.ELEMENT;
            meaning.stringValue = Character.toString(currentChar);
        } else if (Character.isDigit(currentChar)) {
            meaning.type = Types.REFERENCE;
            meaning.intValue = Integer.parseInt(Character.toString(currentChar));
        }

        return meaning;
    }

    private int getBondOrder() {
        int order = this.bondOrder;
        this.bondOrder = 1;
        return order;
    }

    private void setLastAtom(Atom atom, int layer) {
        if (atoms.size() > layer) {
            atoms.remove(layer);
        }

        atoms.add(layer, atom);
    }

    private Atom getLastAtom(int layer) {
        if (atoms.size() > layer) {
            return atoms.get(layer);
        } else {
            return null;
        }
    }

    private void clearLastAtom(int layer) {
        atoms.remove(layer);
    }

    private void setAtomReference(Atom atom, int index) {
        atomReferences.set(index, atom);
    }

    private Atom getAtomReference(int index) {
        if (atomReferences.size() > index) {
            return atomReferences.get(index);
        } else {
            return null;
        }
    }
}
