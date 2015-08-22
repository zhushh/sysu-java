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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class KingCrab extends CrabCritter
{
    public void processActors(ArrayList<Actor> actors)
    {
        // to-do
        int myrow;
        int mycol;
        for (Actor actor : actors)
        {
            myrow = 2 * actor.getLocation().getRow() - getLocation().getRow();
            mycol = 2 * actor.getLocation().getCol() - getLocation().getCol();
            Location loc = new Location(myrow, mycol);
            if (getGrid().isValid(loc) && getGrid().get(loc) == null)
            {
                actor.moveTo(loc);
            } else {
                actor.removeSelfFromGrid();
            }
        }
    }
}