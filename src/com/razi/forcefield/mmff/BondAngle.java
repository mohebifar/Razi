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

/**
 *
 * @author Mohamad Mohebifar <mohebifar.ir>
 */
public class BondAngle {
    public static double getDegrees(Atom a, Atom b, Atom c) {
        // TODO: Correct Angle
        
        return 109.48;
    }

    public static double getRadians(Atom a, Atom b, Atom c) {
        return getDegrees(a, b, c) * Math.PI/180;
    }
}
