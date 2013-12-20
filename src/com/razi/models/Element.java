package com.razi.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */

/**
 *
 * @author Mohamad
 */
public class Element {
    private String name;
    private String symbol;
    private int atomicNumber;
    
    public Element setName(String name) {
        this.name = name;
        return this;
    }
    
    public Element setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }
    
    public Element setAtomicNumber(int number) {
        this.atomicNumber = number;
        return this;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getAtomicNumber() {
        return this.atomicNumber;
    }
    
    public static boolean isValidSymbol(String symbol) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]?$");
        Matcher match = pattern.matcher(symbol);
        if(match.find()) {
            return true;
        } else {
            return false;
        }
    }
}
