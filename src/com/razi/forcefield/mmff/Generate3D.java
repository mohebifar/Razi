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
package com.razi.forcefield.mmff;

import com.razi.models.Atom;
import com.razi.models.AtomSet;
import com.razi.models.BondSet;
import com.razi.models.Molecule;
import com.vividsolutions.jts.math.Vector3D;

/**
 *
 * @author Mohamad Mohebifar <mohebifar.ir>
 */
public class Generate3D {

    final private Molecule molecule;
    private AtomSet atoms = new AtomSet();
    private BondSet bonds = new BondSet();

    public Generate3D(Molecule molecule) {
        this.molecule = molecule;
    }

    public void process() throws Exception {
        walk(molecule.get(0));
    }

    private void walk(Atom A) throws Exception {
        if (isWalked(A)) {
            return;
        } else {
            atoms.add(A);
        }

        AtomSet neighbours = A.getNeighbours();
        AtomSet Bs = neighbours.intersection(atoms);

        if (Bs.size() == 0) {
            A.getPosition().x = 0;
            A.getPosition().y = 0;
            A.getPosition().z = 0;
        } else {
            Atom B = getMaxNeighboured(Bs);
            double length = BondLength.getLength(A, B);

            AtomSet Cs = B.getNeighbours();
            Cs = Cs.intersection(atoms);
            Cs.remove(A);

            if (Cs.size() == 0) {
                // The atom can be on a sphere that is as distant as r from the first atom.
                // We choose [0, 0, r]

                A.getPosition().x = B.getPosition().x;
                A.getPosition().y = B.getPosition().y;
                A.getPosition().z = B.getPosition().z + length;
            } else if (Cs.size() == 1) {
                Atom C1 = Cs.get(0);
                double angle = BondAngle.getRadians(A, B, C1);
                A.getPosition().x = B.getPosition().x + length * Math.sin(angle);
                A.getPosition().y = B.getPosition().y;
                A.getPosition().z = B.getPosition().z + length * Math.cos(angle);
            } else if (Cs.size() == 2) {
                Atom C1 = Cs.get(0);
                Atom C2 = Cs.get(1);
                Vector3D BC1 = new Vector3D(B.getPosition(), C1.getPosition());
                Vector3D BC2 = new Vector3D(B.getPosition(), C2.getPosition());
                double phiC1 = Math.atan(BC1.getY()/BC1.getX());
                double phiC2 = Math.atan(BC2.getY()/BC2.getX());
                
                
                double angle = BondAngle.getRadians(A, B, C2);
                
                A.getPosition().x = B.getPosition().x + length * Math.sin(angle);
                A.getPosition().y = B.getPosition().y;
                A.getPosition().z = B.getPosition().z + length * Math.cos(angle);
            }
        }

        for (Atom atom : neighbours) {
            walk(atom);
        }
    }

    private Atom getMaxNeighboured(AtomSet atomSet) throws Exception {
        int maxCount = 0;
        Atom maxAtom = null;
        for (Atom atom : atomSet) {
            AtomSet neighbours = atom.getNeighbours();
            neighbours = neighbours.intersection(this.atoms);

            if (neighbours.size() >= maxCount) {
                maxAtom = atom;
            }
        }

        return maxAtom;
    }

    private boolean isWalked(Atom atom) {
        return atoms.contains(atom);
    }

    private int countWalked() {
        return atoms.size();
    }

    private void setWalked(Atom atom) {
        atoms.add(atom);
    }
}
