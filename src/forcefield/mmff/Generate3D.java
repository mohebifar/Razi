/*
 * Copyright (C) 2014 Mohamad Mohebifar <mohebifar.ir>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package forcefield.mmff;

import com.razi.models.Atom;
import com.razi.models.Bond;
import com.razi.models.Molecule;
import java.util.ArrayList;

/**
 *
 * @author Mohamad Mohebifar <mohebifar.ir>
 */
public class Generate3D {

    private Molecule molecule;
    private ArrayList<Atom> atoms = new ArrayList<Atom>();
    private ArrayList<Bond> bonds = new ArrayList<Bond>();

    public Generate3D(Molecule molecule) {
        this.molecule = molecule;
    }

    public void process() throws Exception {
        walk(molecule.getAtoms().get(0));
    }

    private void walk(Atom a) throws Exception {
        if(countWalked() == 0) {
            // The atom can be anywhere all over the space
            // We choose [0, 0, 0]
            
        } else if(countWalked() == 1) {
            // The atom can be on a sphere that is as distant as r from the first atom.
            // We choose [0, 0, r]
            
        } else if(countWalked() == 2) {
            // The atom can be on a circumference
            // We Choose cb + ba
        }

        // First Atom
        for (Bond bond : a.getBonds()) {
            Atom b = bond.getAtomByPartner(a);
            ArrayList<Atom> cs = getCAtoms(a, b);
            
            if (! isWalked(b)) {
                atoms.add(b);
                double length = BondLength.getLength(a, b);
                b.getPosition().z = length;
                walk(b);
            }
        }
    }

    private ArrayList<Atom> getCAtoms(Atom a, Atom b) {
        ArrayList<Atom> cAtoms = new ArrayList<>();

        for (Bond bond : b.getBonds()) {
            for (Atom c : atoms) {
                if (!c.equals(a)) {
                    cAtoms.add(c);
                }
            }
        }

        return cAtoms;
    }
    
    private ArrayList<Atom> getNotWalkedCAtoms(Atom a, Atom b) {
        ArrayList<Atom> cAtoms = new ArrayList<>();
        for (Atom atom : getCAtoms(a, b)) {
            if(! isWalked(atom)) {
                cAtoms.add(atom);
            }
        }
        
        return cAtoms;
    }
    
    private boolean isWalked(Atom atom) {
        return atoms.contains(atom);
    }
    
    private int countWalked() {
        return atoms.size();
    }
}
