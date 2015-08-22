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

package info.gridworld.grid;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    //to-do
    public class SparseGridNode
    {
        private Object occupant;
        private int col;
        private SparseGridNode next;

        public SparseGridNode getNext()
        {
            return next;
        }

        public void setNext(SparseGridNode obj)
        {
            next = obj;
        }

        public int getCol()
        {
            return col;
        }

        public void setCol(int c)
        {
            col = c;
        }

        public Object getOccupied()
        {
            return occupant;
        }

        public void setOccupied(Object obj)
        {
            occupant = obj;
        }
    }

    private SparseGridNode[] sparseArrays;
    private int sparseCols;

    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        sparseCols = cols;
        sparseArrays = new SparseGridNode[rows];
    }

    public int getNumRows()
    {
        return sparseArrays.length;
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
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        int col = loc.getCol();
        SparseGridNode node = sparseArrays[loc.getRow()];
        while (node != null && node.getCol() != col)
        {
            node = node.getNext();
        }
        //return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
        return (E) (node == null? null : node.getOccupied());
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        SparseGridNode node = sparseArrays[loc.getRow()];
        while (node != null && node.getNext() != null) {
            node = node.getNext();
        }

        SparseGridNode curNode = new SparseGridNode();
        curNode.setOccupied(obj);
        curNode.setCol(loc.getCol());

        if (node != null) {
            node.setNext(curNode);
        } else {
            sparseArrays[loc.getRow()] = curNode;
        }
        return oldOccupant;
    }
}
