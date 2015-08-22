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
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private SparseGridNode[] sparseArrays;
    private int sparseCols;
    private int sparseRows;

    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        sparseCols = cols;
        sparseRows = rows;
        sparseArrays = new SparseGridNode[rows];
    }

    public int getNumRows()
    {
        return sparseRows;
    }

    public int getNumCols()
    {
        return sparseCols;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
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
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }

        SparseGridNode node = sparseArrays[loc.getRow()];
        while (node != null)
        {
            if (node.getCol() == loc.getCol()) {
                return (E)(node.getOccupied());
            }
            node = node.getNext();
        }
        return null;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        // Add the object to the grid.
        E oldOccupant = remove(loc);
        SparseGridNode node = sparseArrays[loc.getRow()];
        sparseArrays[loc.getRow()] = new SparseGridNode(obj, loc.getCol(), node);
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }

        // Remove the object from the grid.
        E r = null;
        SparseGridNode node = sparseArrays[loc.getRow()];

        if (node == null) {
            return r;
        } else if (node.getCol() == loc.getCol()) {
            r = (E)(node.getOccupied());
            sparseArrays[loc.getRow()] = node.getNext();
            return r;
        }
        while (node != null && node.getNext() != null) {
            if (node.getNext().getCol() == loc.getCol()) {
                r = (E)(node.getNext().getOccupied());
                node.setNext(node.getNext().getNext());
                break;
            } else {
                node = node.getNext();
            }
        }
        return r;
    }
}
