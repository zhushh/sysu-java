/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>UnboundedGrid2</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    public static final int INITROWS = 16;
    public static final int INITCOLS = 16;
    private Object[][] occupantArray; // the array storing the grid elements

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public UnboundedGrid2()
    {
        occupantArray = new Object[INITROWS][INITCOLS];
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < occupantArray.length; r++)
        {
            for (int c = 0; c < occupantArray[0].length; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    theLocations.add(loc);
                }
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc)) {
            throw new NullPointerException("Location " + loc + " is not valid!");
        }
        if (loc.getRow() >= occupantArray.length) {
            return null;
        }
        if (loc.getCol() >= occupantArray[0].length) {
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
            throw new NullPointerException("Location " + loc + " is not valid!");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);

        if (loc.getRow() >= occupantArray.length || loc.getCol() >= occupantArray[0].length) {
            int rows = occupantArray.length;
            int cols = occupantArray[0].length;
            while (loc.getRow() >= rows || loc.getCol() >= cols) {
                rows <<= 1;
                cols <<= 1;
            }
            Object[][] newArrays = new Object[rows][cols];
            for (int r = 0; r < occupantArray.length; r++) {
                for (int c = 0; c < occupantArray[0].length; c++) {
                    newArrays[r][c] = occupantArray[r][c];
                }
            }
            occupantArray = newArrays;
        }
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new NullPointerException("Location " + loc + " is not valid!");
        }

        // Remove the object from the grid.
        E r = get(loc);
        if (loc.getRow() < occupantArray.length || loc.getCol() < occupantArray[0].length) {
            occupantArray[loc.getRow()][loc.getCol()] = null;
        }
        return r;
    }
}
