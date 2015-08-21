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

/**
 * A <code>RockHound</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class RockHound extends Critter
{
    private int courage;
    private static final double DARKENING_FACTOR = 0.05;

    public RockHound(int c)
    {
        courage = c;
    }

    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int row = getRow() - 2;
        int col = getCol() - 2;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (row != getRow() || col != getCol()) {
                    Actor ac = getGrid().get(new Location(row, col));
                    if (ac != null) {
                        actors.add(ac);
                    }
                }
                col++;
            }
            row++;
        }
        return actors;
    }

    public void processActors(ArrayList<Actor> actors)
    {
        if (courage <= actors.size()) {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

            setColor(new Color(red, green, blue));
        } else {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 + DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR));

            setColor(new Color(red, green, blue));
        }
    }
}