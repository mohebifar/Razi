/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.razi.formats.smiles;

import com.razi.formats.ReaderInterface;
import com.razi.models.Element;
import com.razi.models.Molecule;
import java.io.File;

/**
 *
 * @author Mohamad Mohebifar
 */
public class Reader implements ReaderInterface {

    private String smiles;
    private Molecule molecule;

    protected static enum Types {

        ATOM, BOND, REFERENCE, STARTLAYER, ENDLAYER
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
    public void process() {
        int index = 0;
        System.out.println(smiles);
        System.out.println(smiles.length());
        while (index < smiles.length()) {
            Meaning meaning = getMeaning(index);
            System.out.println(index + ": " + meaning.type.toString());

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
            meaning.type = Types.ATOM;
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
            meaning.type = Types.ATOM;
            meaning.stringValue = Character.toString(currentChar);
        } else if (Character.isDigit(currentChar)) {
            meaning.type = Types.REFERENCE;
            meaning.intValue = Integer.parseInt(Character.toString(currentChar));
        }

        return meaning;
    }
}