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
        walk(molecule.get(0));
    }

    private void walk(Atom A) throws Exception {
//        if (isWalked(A)) {
//            return;
//        } else {
//            atoms.add(A);
//        }
//
//        Atom B = getBestBAtom(A);
//
//        if (B == null) {
//            System.out.println(getBAtoms(A).size());
//            // The atom can be anywhere all over the space
//            // We choose [0, 0, 0]
//        } else {
//            double length = BondLength.getLength(A, B);
//            
//            ArrayList<Atom> Cs = getCAtoms(A, B);
//            filterNotWalked(Cs);
//            if (Cs.size() == 0) {
//                // The atom can be on a sphere that is as distant as r from the first atom.
//                // We choose [0, 0, r]
//                
//                A.getPosition().x = B.getPosition().x;
//                A.getPosition().y = B.getPosition().y;
//                A.getPosition().z = B.getPosition().z + length;
//            } else {
//                
//            }
//        }
//        
//        for (Atom atom : getBAtoms(A)) {
//            walk(atom);
//        }
//        
//        else if(Bs.size() > 0) {
//            
//        } else if(countWalked() == 2) {
//            // The atom can be on a circumference
//            // We Choose cb + ba
//        }
    }

    private boolean isWalked(Atom atom) {
        return atoms.contains(atom);
    }

    private int countWalked() {
        return atoms.size();
    }
}
