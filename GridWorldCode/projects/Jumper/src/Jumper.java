/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */


import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import info.gridworld.actor.Bug;


/**
 * A <code>Jumper</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Bug
{
    /**
     * Constructs a Jumper.
     */
    public Jumper()
    {
        //setColor(Color.RED);
    }

    /**
     * Constructs a bug of a given color.
     * @param bugColor the color for this bug
     */
    public Jumper(Color jumperColor)
    {
        setColor(jumperColor);
    }

    /**
     * Moves the bug forward
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            removeSelfFromGrid();
        } else {
            Location nnext = next.getAdjacentLocation(getDirection());
            if (gr.isValid(nnext))
                moveTo(nnext);
            else
                removeSelfFromGrid();
        }
    }

    /**
     * Tests whether this Jumper can move forward into a location that is empty
     * @return true if this Jumper can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        } else {
            Location nnext = next.getAdjacentLocation(getDirection());
            if (!gr.isValid(nnext))
                return false;
            // OK to move into empty location
            // Not ok to move onto any other actor or flower
            // return (neighbor == null) || (neighbor instanceof Flower);
            Actor neighbor = gr.get(nnext);
            return neighbor == null;
        }
    }
}
