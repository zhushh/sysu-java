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

import info.gridworld.actor.Bug;

/**
 * A <code>DancingBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
    private int steps;
    private int arrayLength;
    private int[] array;

    public DancingBug(int[] arr)
    {
        steps = 0;
        arrayLength = arr.length;
        array = new int[arrayLength];
        System.arraycopy(arr, 0, array, 0, arr.length);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        for (int i = 0; i < array[steps]; i++) {
            turn();
        }
        while (!canMove()) {
            turn();
        }
        move();
        steps++;
        if (steps >= arrayLength) {
            steps -= arrayLength;
        }
    }
}